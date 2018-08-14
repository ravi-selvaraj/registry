package com.idhayangal.registry.worker;

import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import java.util.logging.*;

public class DBConnectionPool {
	static final Logger LOG = Logger.getLogger(DBConnectionPool.class.getName());

	private static BasicDataSource connectionPool;
	
	public static final String default_dbhost = "XXXXX";
	public static final String default_dbport = "XXXXX";
	public static final String default_user = "XXXXX";
	public static final String default_pass = "XXXXX";
	public static final String default_db = "XXXXX";

	private static String load_env(String key, String default_val)
	{
		String szValue = System.getenv(key);
		if (null == szValue)
			szValue = default_val;
		return szValue;
	}

	public static boolean Initialize() {
		try
		{
			String dbHost = load_env("DBHOST", default_dbhost);
			String dbPort = load_env("DBPORT", default_dbport);
			String db = load_env("DB", default_db);
			String dbUser = load_env("DBUSER", default_user);
			String dbPass = load_env("DBPASS", default_pass);
			
			
			String dbUrl = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + db;
			connectionPool = new BasicDataSource();
	
			connectionPool.setUsername(dbUser);
			connectionPool.setPassword(dbPass);
			
			connectionPool.setDriverClassName("org.postgresql.Driver");
			connectionPool.setUrl(dbUrl);
			connectionPool.setInitialSize(5);
		}
		catch (Exception e)
		{
			LOG.log(Level.SEVERE, e.getMessage(), e);
			return false;
		}
		return true;
	}
	
	public static Connection getConnection()
	{
		try {
			return connectionPool.getConnection();
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
	}
	
	
}
