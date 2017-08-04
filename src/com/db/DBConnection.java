package com.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection c=null;
	public static Connection getConnection()
	{
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			c=DriverManager.getConnection("jdbc:mysql://localhost:3306/hibtestdb","root","root");
			
			return c;
			
		}catch(Exception e){
			e.printStackTrace();
			return c;
		}
	}
}
