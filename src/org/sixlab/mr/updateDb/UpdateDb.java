package org.sixlab.mr.updateDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateDb {
	
	private static Connection connSqlite() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:modify.db");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	private static Connection connSqlite1() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:bemodify.db");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	private static void closeConn(Connection conn, Statement stmt, ResultSet rt) {
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rt != null) {
			try {
				rt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void update() {
		String id = "";
		String name = "";
		String inDate = "";
		
		String country = "";
		String year = "";
		String director = "";
		String actor = "";
		String remark = "";
		
		String oldMark = "";
		
		String sqlOld = "SELECT * FROM saw";
		
		Connection conn = connSqlite();
		Statement stmt = null;
		ResultSet rt=null;
		
		Connection conn1 = connSqlite1();
		Statement stmt1 = null;
		ResultSet rt1=null;
		try {
			 stmt = conn.createStatement();
			 rt = stmt.executeQuery(sqlOld);
			 
			 stmt1 = conn1.createStatement();
			
			
			while (rt.next()) {
				String insertSQL = "INSERT INTO saw(Name,Country,Year,Director,Actor,Remark,InDate) VALUES('";
				id = rt.getString(1);
				name = rt.getString(2);
				oldMark = rt.getString(3);
				inDate = rt.getString(4);
				if (null != oldMark & !oldMark.equals("")) {
					country = oldMark.substring(0, 2);
					year = oldMark.substring(3, 7);
					director = oldMark.substring(8);
				}
				insertSQL += name;
				insertSQL += "','";
				insertSQL += country;
				insertSQL += "','";
				insertSQL += year;
				insertSQL += "','";
				insertSQL += director;
				insertSQL += "','";
				insertSQL += actor;
				insertSQL += "','";
				insertSQL += remark;
				insertSQL += "','";
				insertSQL += inDate;
				insertSQL += "')";
				stmt1.execute(insertSQL);
				if (Integer.parseInt(id)%100==0) {
					System.out.println(id+" has done");
				}
				id = "";
				name = "";
				inDate = "";
				
				country = "";
				year = "";
				director = "";
				actor = "";
				remark = "";
				
				oldMark = "";
			}
		} catch (SQLException e) {
			System.out.println(id+":"+name);
			e.printStackTrace();
		}finally{
			closeConn(conn, stmt, rt);
			closeConn(conn1, stmt1, rt1);
		}
	}
	
	public static void main(String[] args) {
		update();
	}
}
