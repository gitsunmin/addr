package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBCon {
	private static final String URL;
	private static final String USER;
	private static final String PASSWORD;
	private static final String CLASS_NAME;
	private static Connection con=null;
	static {
		InputStream is = DBCon.class.getResourceAsStream("/config/db.properties");
		Properties prop = new Properties();
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		URL = prop.getProperty("url");
		USER = prop.getProperty("user");
		PASSWORD = prop.getProperty("password");
		CLASS_NAME = prop.getProperty("classname");
	}
	
	
	public static Connection getCon() {
		if(con==null) {
			try {
				Class.forName(CLASS_NAME);
				con=DriverManager.getConnection(URL,USER,PASSWORD);
				return con;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
	
	public static void conClose() {
		if(con!=null) {
			try {
				if(!con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		con=null;
	}
	
	public static void main(String[] args) {
		DBCon.getCon();
		DBCon.conClose();
	}
	
}
