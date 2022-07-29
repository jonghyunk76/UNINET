package stw.Interface.common;


public enum DBSource {
	
	SIEBEL ("oracle.jdbc.driver.OracleDriver",
		    "jdbc:oracle:thin:@55.200.10.144:1527:CRMOLTPD",
		    "badmin",
		    "badmin$12"),
		    
	SMS    ("com.microsoft.sqlserver.jdbc.SQLServerDriver",
		    "jdbc:sqlserver://55.10.10.11:1433;DatabaseName=stw_SMS_0009",
		    "stw_SMS_0009",
		    "stw*sms0009"),
		    
	MYSQL  ("com.mysql.jdbc.Driver",
		    "jdbc:mysql://ip:port/DatabaseName",
		    "id",
		    "pass");
	
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	private DBSource(String driverClassName, String url, String username, String password) {
		this.driverClassName = driverClassName;
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public String getDriverClassName() {
		return driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
