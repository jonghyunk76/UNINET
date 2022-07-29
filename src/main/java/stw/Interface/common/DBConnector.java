package stw.Interface.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;


/**
 * ASF??Commons-DBCPë¥??´ìš©?´ì„œ DB Connection Pool??ê´?¦¬?œë‹¤.<br>
 * DBë³„ë¡œ êµ¬ë¶„?ë? ì§? •?´ì„œ Connection Pool??Map???ì„±????
 * ?”ì²­???ˆì„ ?Œë§ˆ??Map?ì„œ DB Connection???œê³µ?œë‹¤.
 */
public class DBConnector
{

	private final static Map<String, BasicDataSource> mapDS = new HashMap<String, BasicDataSource>();

	/**
	 * DB Connection??ê°? ¸?¨ë‹¤.<br>
	 * <br>
	 * DB êµ¬ë¶„?ë? ?¸ìˆ˜ë¡?ë°›ì•„??ë¨¼ì? Map???´ë‹¹ CPê°??ˆëŠ”ì§??•ì¸?´ì„œ
	 * ?ˆìœ¼ë©?ê¸°ì¡´??Connection???œê³µ?˜ê³  ?†ìœ¼ë©?CPë¥??ˆë¡œ ?ì„±?´ì„œ
	 * Map??ì¶”ê?????Connection???œê³µ?œë‹¤.<br>
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
	 * Connection Pool??ê´?¦¬?˜ëŠ” DataSource ê°ì²´ë¥??ì„±?œë‹¤.<br>
	 *
	 * @param DB DB ?œìŠ¤???´ë¦„
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
	 * Connection??ê°? ¸?¤ê¸° ???„ì˜ Connection Pool???íƒœë¥??•ì¸?œë‹¤.
	 *
	 * @param DB DB ?œìŠ¤???´ë¦„
	 * @param DS ?íƒœë¥??•ì¸??DataSource ê°ì²´
	 * @param flag ????êµ¬ë¶„??
	 * @see getConnection
	 */
	private static void checkPoolState(String DB, BasicDataSource DS) {
		int nActive = DS.getNumActive();
		int nIdle   = DS.getNumIdle();
	}

	/**
	 * Connection Pool??Connection??ë°˜í™˜?œë‹¤.<br>
	 * <br>
	 * Connection??ë°˜í™˜?˜ë©´ ?´ë‹¹ Connection??Idle ?íƒœê°??˜ë©°
	 * ?˜ì¤‘???¤ì‹œ ?”ì²­???¤ë©´ Active ?íƒœê°??˜ì–´ ?¬í™œ?©ëœ??
	 * @param con Connection ê°ì²´
	 * @exception SQLException
	 * @see close
	 */
	public static void close(Connection con) throws SQLException {
		// Connection.close() ?˜ë©´ Connection??Pool??ë°˜í™˜?˜ì–´??Idle ?íƒœê°??˜ë‚˜ con ë³?ˆ˜ê°?null???˜ì????ŠëŠ”??
		// Connection.close()??ì¡°ê±´: con.toString() == "NULL", con.hashCode() == 0
		if ( !(con == null || con.hashCode() == 0) ) {
			int hashCode = con.hashCode();
			con.close();
		}
	}

	/**
	 * Statement ê°ì²´ë¥??«ëŠ”??<br>
	 * <br>
	 * Statement ê°ì²´ë¥??«ìœ¼ë©?? ë‹¹???ì›??ì¦‰ì‹œ System??ë°˜í™˜?˜ë?ë¡??¬ìš© ?„ì—??ë°˜ë“œ???«ëŠ”??
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
	 * ?´ë‹¹ transaction??COMMIT ?œë‹¤.<br>
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
	 * ?´ë‹¹ transaction??ROLLBACK ?œë‹¤.<br>
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
