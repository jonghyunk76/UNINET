package stw.Interface.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;


/**
 * ASF??Commons-DBCP�??�용?�서 DB Connection Pool??�?��?�다.<br>
 * DB별로 구분?��? �?��?�서 Connection Pool??Map???�성????
 * ?�청???�을 ?�마??Map?�서 DB Connection???�공?�다.
 */
public class DBConnector
{

	private final static Map<String, BasicDataSource> mapDS = new HashMap<String, BasicDataSource>();

	/**
	 * DB Connection??�?��?�다.<br>
	 * <br>
	 * DB 구분?��? ?�수�?받아??먼�? Map???�당 CP�??�는�??�인?�서
	 * ?�으�?기존??Connection???�공?�고 ?�으�?CP�??�로 ?�성?�서
	 * Map??추�?????Connection???�공?�다.<br>
	 *
	 * @return Connection 객체
	 * @exception SQLException
	 * @see createDataSource
	 */
	public synchronized static Connection getConnection(DBSource DB) throws SQLException {
		BasicDataSource DS = null;
		String dbName = DB.name();
		if (mapDS.containsKey(dbName)) {
			DS = mapDS.get(dbName);
		} else {
			DS = createDataSource(DB);
			mapDS.put(dbName, DS);
		}
		Connection con = DS.getConnection();
		con.setAutoCommit(false);

		return con;
	}
	
	/**
	 * Connection Pool??�?��?�는 DataSource 객체�??�성?�다.<br>
	 *
	 * @param DB DB ?�스???�름
	 * @return DataSource 객체
	 * @see getConnection
	 */
	private static BasicDataSource createDataSource(DBSource info) {
		String driverClassName = info.getDriverClassName();
		String url             = info.getUrl();
		String username        = info.getUsername();
		String password        = info.getPassword();
		String maxActive       = "3";
		String initialSize     = "1";
		String minIdle         = "1";

		BasicDataSource DS = new BasicDataSource();
		DS.setDriverClassName(driverClassName);
		DS.setUrl(url);
		DS.setUsername(username);
		DS.setPassword(password);
		DS.setMaxActive(Integer.parseInt(maxActive));
		DS.setInitialSize(Integer.parseInt(initialSize));
		DS.setMinIdle(Integer.parseInt(minIdle));
		DS.setPoolPreparedStatements(true);
		
		return DS;
	}

	/**
	 * Connection??�?��?�기 ???�의 Connection Pool???�태�??�인?�다.
	 *
	 * @param DB DB ?�스???�름
	 * @param DS ?�태�??�인??DataSource 객체
	 * @param flag ????구분??
	 * @see getConnection
	 */
	private static void checkPoolState(String DB, BasicDataSource DS) {
		int nActive = DS.getNumActive();
		int nIdle   = DS.getNumIdle();
	}

	/**
	 * Connection Pool??Connection??반환?�다.<br>
	 * <br>
	 * Connection??반환?�면 ?�당 Connection??Idle ?�태�??�며
	 * ?�중???�시 ?�청???�면 Active ?�태�??�어 ?�활?�된??
	 * @param con Connection 객체
	 * @exception SQLException
	 * @see close
	 */
	public static void close(Connection con) throws SQLException {
		// Connection.close() ?�면 Connection??Pool??반환?�어??Idle ?�태�??�나 con �?���?null???��????�는??
		// Connection.close()??조건: con.toString() == "NULL", con.hashCode() == 0
		if ( !(con == null || con.hashCode() == 0) ) {
			int hashCode = con.hashCode();
			con.close();
		}
	}

	/**
	 * Statement 객체�??�는??<br>
	 * <br>
	 * Statement 객체�??�으�??�당???�원??즉시 System??반환?��?�??�용 ?�에??반드???�는??
	 *
	 * @param stmt Statement 객체
	 * @exception SQLException
	 * @see close
	 */
	public static void close(Statement stmt) throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
	}

	/**
	 * ?�당 transaction??COMMIT ?�다.<br>
	 *
	 * @param con Connection 객체
	 * @exception SQLException
	 * @see rollback
	 */
	public static void commit(Connection con) throws SQLException {
		if ( !(con == null || con.isClosed()) ) {
			con.commit();
		}
	}

	/**
	 * ?�당 transaction??ROLLBACK ?�다.<br>
	 *
	 * @param con Connection 객체
	 * @exception SQLException
	 * @see commit
	 */
	public static void rollback(Connection con)	throws SQLException {
		if ( !(con == null || con.isClosed()) ) {
			con.rollback();
		}
	}
}
