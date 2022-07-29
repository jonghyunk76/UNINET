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
 * ASF�� Commons-DBCP�� �̿��ؼ� DB Connection Pool�� �����Ѵ�.<br>
 * <br>
 * DB���� �����ڸ� �����ؼ� Connection Pool�� Map�� ������ ��
 * ��û�� ���� ������ Map���� DB Connection�� �����Ѵ�.
 */
public class DataSource
{

	private final static Map<String, BasicDataSource> mapDS = new HashMap<String, BasicDataSource>();

	/**
	 * DB Connection�� �����´�.<br>
	 * <br>
	 * DB �����ڸ� �μ��� �޾Ƽ� ���� Map�� �ش� CP�� �ִ��� Ȯ���ؼ�
	 * ������ ������ Connection�� �����ϰ� ������ CP�� ���� �����ؼ�
	 * Map�� �߰��� �� Connection�� �����Ѵ�.<br>
	 * AutoCommit �Ӽ��� �����ϹǷ� �ݵ�� ���� COMMIT/ROLLBACK�� �����ؾ� �Ѵ�.
	 *
	 * @return Connection ��ü
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

		checkPoolState(DB, DS, "��");

		Connection con = DS.getConnection();
		con.setAutoCommit(false);
		Utils.getLogger().debug(String.format("Connection (%s): HashCode=[%d]", DB, con.hashCode()));

		checkPoolState(DB, DS, "��");

		return con;
	}

	
	/**
	 * Connection Pool�� �����ϴ� DataSource ��ü�� �����Ѵ�.<br>
	 * <br>
	 * ȯ�漳�� ������ DB ���������� Connection Pool�� �����Ѵ�.
	 * ��й�ȣ�� ��ȣȭ�Ǿ� �����Ƿ� ��ȣȭ�� �Ŀ� ����.
	 *
	 * @param DB DB �ý��� �̸�
	 * @return DataSource ��ü
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

		// ��й�ȣ ��ȣȭ
		//password = AES.decrypt(password);

		BasicDataSource DS = new BasicDataSource();

		DS.setDriverClassName(driverClassName);
		DS.setUrl(url);
		DS.setUsername(username);
		DS.setPassword(password);		// ��ȣȭ�� ��й�ȣ
		DS.setMaxActive(Integer.parseInt(maxActive));
		DS.setInitialSize(Integer.parseInt(initialSize));
		DS.setMinIdle(Integer.parseInt(minIdle));
		DS.setMaxWait(Long.parseLong(maxWait));
		//DS.setValidationQuery("SELECT 1 FROM DUAL");
		DS.setPoolPreparedStatements(true);

		StringBuilder sb = new StringBuilder(String.format("DataSource [%s] ����%n%n", DB))
				.append(String.format("     url=[%s]%n", url     ))
				.append(String.format("username=[%s]%n", username))
				.append(String.format("password=[%s]%n", password));

		Utils.getLogger().info(sb.toString());

		return DS;
	}

	/**
	 * Connection�� �������� ��/���� Connection Pool�� ���¸� Ȯ���Ѵ�.
	 *
	 * @param DB DB �ý��� �̸�
	 * @param DS ���¸� Ȯ���� DataSource ��ü
	 * @param flag ��/�� ������
	 * @see getConnection
	 */
	private static void checkPoolState(String DB, BasicDataSource DS, String flag) {

		int nActive = DS.getNumActive();
		int nIdle   = DS.getNumIdle();

		Utils.getLogger().debug(String.format("DataSource (%s) %s: nActive=[%d], nIdle=[%d]", DB, flag, nActive, nIdle));
	}

	/**
	 * Connection Pool�� Connection�� ��ȯ�Ѵ�.<br>
	 * <br>
	 * Connection�� ��ȯ�Ǹ� �ش� Connection�� Idle ���°� �Ǹ�
	 * ���߿� �ٽ� ��û�� ���� Active ���°� �Ǿ� ��Ȱ��ȴ�.
	 * @param con Connection ��ü
	 * @exception SQLException
	 * @see close
	 */
	public static void close(Connection con)
			throws SQLException {

		// Connection.close() �Ǹ� Connection�� Pool�� ��ȯ�Ǿ Idle ���°� �ǳ� con ������ null�� ������ �ʴ´�.
		// Connection.close()�� ����: con.toString() == "NULL", con.hashCode() == 0
		if ( !(con == null || con.hashCode() == 0) ) {
			int hashCode = con.hashCode();
			con.close();
			Utils.getLogger().info(String.format("Connection (%d) Closed", hashCode));
		}
	}

	/**
	 * Statement ��ü�� �ݴ´�.<br>
	 * <br>
	 * Statement ��ü�� ������ �Ҵ�� �ڿ��� ��� System�� ��ȯ�ǹǷ� ��� �Ŀ��� �ݵ�� �ݴ´�.
	 *
	 * @param stmt Statement ��ü
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
	 * �ش� transaction�� COMMIT �Ѵ�.<br>
	 *
	 * @param con Connection ��ü
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
	 * �ش� transaction�� ROLLBACK �Ѵ�.<br>
	 *
	 * @param con Connection ��ü
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
