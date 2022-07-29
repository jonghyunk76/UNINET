package stw.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;

import stw.eai.common.ConfigPropServerInfo;
import stw.eai.common.Utils;
import stw.encryption.DES;

/**
 * ASF의 Commons-DBCP를 이용해서 DB Connection Pool을 관리한다.<br>
 * <br>
 * DB별로 구분자를 지정해서 Connection Pool의 Map을 생성한 후
 * 요청이 있을 때마다 Map에서 DB Connection을 제공한다.
 */
public class DataSource
{

	private final static Map<String, BasicDataSource> mapDS = new HashMap<String, BasicDataSource>();

	/**
	 * DB Connection을 가져온다.<br>
	 * <br>
	 * DB 구분자를 인수로 받아서 먼저 Map에 해당 CP가 있는지 확인해서
	 * 있으면 기존의 Connection을 제공하고 없으면 CP를 새로 생성해서
	 * Map에 추가한 후 Connection을 제공한다.<br>
	 * AutoCommit 속성은 해제하므로 반드시 직접 COMMIT/ROLLBACK을 실행해야 한다.
	 *
	 * @return Connection 객체
	 * @exception SQLException
	 * @see createDataSource
	 */
	public synchronized static Connection getConnection(String DB) throws Exception, SQLException {

		BasicDataSource DS = null;

		if (mapDS.containsKey(DB)) {
			DS = mapDS.get(DB);
		}
		else {
			DS = createDataSource(DB);
			mapDS.put(DB, DS);
		}

		checkPoolState(DB, DS, "전");

		Connection con = DS.getConnection();
		con.setAutoCommit(false);
		Utils.getLogger().debug(String.format("Connection (%s): HashCode=[%d]", DB, con.hashCode()));

		checkPoolState(DB, DS, "후");

		return con;
	}

	
	/**
	 * Connection Pool을 관리하는 DataSource 객체를 생성한다.<br>
	 * <br>
	 * 환경설정 파일의 DB 설정값으로 Connection Pool을 생성한다.
	 * 비밀번호는 암호화되어 있으므로 복호화한 후에 쓴다.
	 *
	 * @param DB DB 시스템 이름
	 * @return DataSource 객체
	 * @see getConnection
	 */
	private static BasicDataSource createDataSource(String DB) throws Exception {
		ConfigPropServerInfo confProp = new ConfigPropServerInfo();

		String driverClassName = confProp.getProperty(DB, "driver"     );
		String url             = new DES().decryption(confProp.getProperty(DB, "url"		));
		String username        = new DES().decryption(confProp.getProperty(DB, "user"       ));
		String password        = new DES().decryption(confProp.getProperty(DB, "password"   ));
		String maxActive       = confProp.getProperty(DB, "MaxActive"  );
		String initialSize     = confProp.getProperty(DB, "InitialSize");
		String minIdle         = confProp.getProperty(DB, "MinIdle"    );
		String maxWait         = confProp.getProperty(DB, "MaxWait"    );

		// 비밀번호 복호화
		//password = AES.decrypt(password);

		BasicDataSource DS = new BasicDataSource();

		DS.setDriverClassName(driverClassName);
		DS.setUrl(url);
		DS.setUsername(username);
		DS.setPassword(password);		// 복호화된 비밀번호
		DS.setMaxActive(Integer.parseInt(maxActive));
		DS.setInitialSize(Integer.parseInt(initialSize));
		DS.setMinIdle(Integer.parseInt(minIdle));
		DS.setMaxWait(Long.parseLong(maxWait));
		//DS.setValidationQuery("SELECT 1 FROM DUAL");
		DS.setPoolPreparedStatements(true);

		StringBuilder sb = new StringBuilder(String.format("DataSource [%s] 생성%n%n", DB))
				.append(String.format("     url=[%s]%n", url     ))
				.append(String.format("username=[%s]%n", username))
				.append(String.format("password=[%s]%n", password));

		Utils.getLogger().info(sb.toString());

		return DS;
	}

	/**
	 * Connection을 가져오기 전/후의 Connection Pool의 상태를 확인한다.
	 *
	 * @param DB DB 시스템 이름
	 * @param DS 상태를 확인할 DataSource 객체
	 * @param flag 전/후 구분자
	 * @see getConnection
	 */
	private static void checkPoolState(String DB, BasicDataSource DS, String flag) {

		int nActive = DS.getNumActive();
		int nIdle   = DS.getNumIdle();

		Utils.getLogger().debug(String.format("DataSource (%s) %s: nActive=[%d], nIdle=[%d]", DB, flag, nActive, nIdle));
	}

	/**
	 * Connection Pool에 Connection을 반환한다.<br>
	 * <br>
	 * Connection을 반환되면 해당 Connection은 Idle 상태가 되며
	 * 나중에 다시 요청이 오면 Active 상태가 되어 재활용된다.
	 * @param con Connection 객체
	 * @exception SQLException
	 * @see close
	 */
	public static void close(Connection con)
			throws SQLException {

		// Connection.close() 되면 Connection은 Pool에 반환되어서 Idle 상태가 되나 con 변수가 null이 되지는 않는다.
		// Connection.close()된 조건: con.toString() == "NULL", con.hashCode() == 0
		if ( !(con == null || con.hashCode() == 0) ) {
			int hashCode = con.hashCode();
			con.close();
			Utils.getLogger().info(String.format("Connection (%d) Closed", hashCode));
		}
	}

	/**
	 * Statement 객체를 닫는다.<br>
	 * <br>
	 * Statement 객체를 닫으면 할당된 자원이 즉시 System에 반환되므로 사용 후에는 반드시 닫는다.
	 *
	 * @param stmt Statement 객체
	 * @exception SQLException
	 * @see close
	 */
	public static void close(Statement stmt)
			throws SQLException {

		if (stmt != null) {
			stmt.close();
		}
	}

	/**
	 * 해당 transaction을 COMMIT 한다.<br>
	 *
	 * @param con Connection 객체
	 * @exception SQLException
	 * @see rollback
	 */
	public static void commit(Connection con)
			throws SQLException {

		if ( !(con == null || con.isClosed()) ) {
			con.commit();
			Utils.getLogger().info(String.format("Connection (%d) COMMIT", con.hashCode()));
		}
	}

	/**
	 * 해당 transaction을 ROLLBACK 한다.<br>
	 *
	 * @param con Connection 객체
	 * @exception SQLException
	 * @see commit
	 */
	public static void rollback(Connection con)
			throws SQLException {

		if ( !(con == null || con.isClosed()) ) {
			con.rollback();
			Utils.getLogger().info(String.format("Connection (%d) ROLLBACK", con.hashCode()));
		}
	}
}
