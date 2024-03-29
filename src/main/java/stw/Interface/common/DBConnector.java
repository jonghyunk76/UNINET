package stw.Interface.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;


/**
 * ASF??Commons-DBCPë¥??´ì©?´ì DB Connection Pool??ê´?¦¬?ë¤.<br>
 * DBë³ë¡ êµ¬ë¶?ë? ì§? ?´ì Connection Pool??Map???ì±????
 * ?ì²­???ì ?ë§??Map?ì DB Connection???ê³µ?ë¤.
 */
public class DBConnector
{

	private final static Map<String, BasicDataSource> mapDS = new HashMap<String, BasicDataSource>();

	/**
	 * DB Connection??ê°? ¸?¨ë¤.<br>
	 * <br>
	 * DB êµ¬ë¶?ë? ?¸ìë¡?ë°ì??ë¨¼ì? Map???´ë¹ CPê°??ëì§??ì¸?´ì
	 * ?ì¼ë©?ê¸°ì¡´??Connection???ê³µ?ê³  ?ì¼ë©?CPë¥??ë¡ ?ì±?´ì
	 * Map??ì¶ê?????Connection???ê³µ?ë¤.<br>
	 *
	 * @return Connection ê°ì²´
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
	 * Connection Pool??ê´?¦¬?ë DataSource ê°ì²´ë¥??ì±?ë¤.<br>
	 *
	 * @param DB DB ?ì¤???´ë¦
	 * @return DataSource ê°ì²´
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
	 * Connection??ê°? ¸?¤ê¸° ???ì Connection Pool???íë¥??ì¸?ë¤.
	 *
	 * @param DB DB ?ì¤???´ë¦
	 * @param DS ?íë¥??ì¸??DataSource ê°ì²´
	 * @param flag ????êµ¬ë¶??
	 * @see getConnection
	 */
	private static void checkPoolState(String DB, BasicDataSource DS) {
		int nActive = DS.getNumActive();
		int nIdle   = DS.getNumIdle();
	}

	/**
	 * Connection Pool??Connection??ë°í?ë¤.<br>
	 * <br>
	 * Connection??ë°í?ë©´ ?´ë¹ Connection??Idle ?íê°??ë©°
	 * ?ì¤???¤ì ?ì²­???¤ë©´ Active ?íê°??ì´ ?¬í?©ë??
	 * @param con Connection ê°ì²´
	 * @exception SQLException
	 * @see close
	 */
	public static void close(Connection con) throws SQLException {
		// Connection.close() ?ë©´ Connection??Pool??ë°í?ì´??Idle ?íê°??ë con ë³?ê°?null???ì????ë??
		// Connection.close()??ì¡°ê±´: con.toString() == "NULL", con.hashCode() == 0
		if ( !(con == null || con.hashCode() == 0) ) {
			int hashCode = con.hashCode();
			con.close();
		}
	}

	/**
	 * Statement ê°ì²´ë¥??«ë??<br>
	 * <br>
	 * Statement ê°ì²´ë¥??«ì¼ë©?? ë¹???ì??ì¦ì System??ë°í?ë?ë¡??¬ì© ?ì??ë°ë???«ë??
	 *
	 * @param stmt Statement ê°ì²´
	 * @exception SQLException
	 * @see close
	 */
	public static void close(Statement stmt) throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
	}

	/**
	 * ?´ë¹ transaction??COMMIT ?ë¤.<br>
	 *
	 * @param con Connection ê°ì²´
	 * @exception SQLException
	 * @see rollback
	 */
	public static void commit(Connection con) throws SQLException {
		if ( !(con == null || con.isClosed()) ) {
			con.commit();
		}
	}

	/**
	 * ?´ë¹ transaction??ROLLBACK ?ë¤.<br>
	 *
	 * @param con Connection ê°ì²´
	 * @exception SQLException
	 * @see commit
	 */
	public static void rollback(Connection con)	throws SQLException {
		if ( !(con == null || con.isClosed()) ) {
			con.rollback();
		}
	}
}
