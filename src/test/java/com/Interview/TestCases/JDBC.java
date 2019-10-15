package com.Interview.TestCases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBC {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// Object of connection from the DataBase

		Connection conn = null;

		// Object of statement. Is is user to create a statement to execute the query

		Statement stmt = null;

		// Object of Resultset => It maintains a cursor that points to the current row
		// in the result set

		ResultSet resultSet = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "rootpasswordgiven");

			// Execute a query
			stmt = conn.createStatement();

			resultSet = stmt.executeQuery("select * from trail");
			
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));

			}

		}

		finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception e) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
		
		}

	}

}
