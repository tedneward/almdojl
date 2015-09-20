package com.microsoft.example;

import java.sql.*;
import javax.sql.*;
import com.microsoft.example.models.*;

/**
 * A set of static methods designed to make it easier to access the
 * data living in the associated MySQL database. This is a basically
 * designed to be a simpler approach than Hibernate or JPA, by using
 * straight JDBC and hiding it.
 */
public class DataAccess
{
	// Some database-specific details we'll need
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/alm";
	private static final String DB_USER = "user";
	private static final String DB_PASS = "password";
	
	private static Connection theConnection;
	static {
		try {
			// Bootstrap driver into JVM
			Class.forName(DB_DRIVER);
			theConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		}
		catch (Exception ex) {
			// Eh.... just give up
			System.exit(-1);
		}
	}
	
	private static PreparedStatement LOGIN;
	private static PreparedStatement FARES;
	static {
		try {
			LOGIN = theConnection.prepareStatement("SELECT * FROM employees WHERE username=? AND password=?");
			FARES = theConnection.prepareStatement("SELECT * FROM fares WHERE emp_id=?");
		}
		catch (SQLException sqlEx) {
			// Eh.... just give up
			sqlEx.printStackTrace();
			System.exit(-1);
		}
	}

	public static boolean loginSuccessful(String employeeEmail, String employeePassword) {
		try {
			LOGIN.clearParameters();

			LOGIN.setString(1, employeeEmail);
			LOGIN.setString(2, employeePassword);
			
			try (ResultSet rs = LOGIN.executeQuery()) {
				if (rs.next())
					return true;
				else
					return false;
			}
		}
		catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
			return false;
		}
	}
	
	public static ResultSet employeeFares(int empID) {
		try {
			FARES.clearParameters();
	
			FARES.setInt(1, empID);
			
			return FARES.executeQuery();
		}
		catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
			return null;
		}
	}
	public static ResultSet employeeFares(Employee emp) {
		return employeeFares(emp.getID());
	}
}