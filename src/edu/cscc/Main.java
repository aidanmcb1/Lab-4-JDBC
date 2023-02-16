package edu.cscc;

import java.sql.*;

public class Main {

	final static String user = System.getenv("userenv");
	final static String pass = System.getenv("passenv");
	final static String port = System.getenv("portenv");
	final static String host = System.getenv("hostenv");
	final static String database = System.getenv("databaseenv");

	final static String connectionURL = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + database + ";user="
			+ user + ";password=" + pass + ";encrypt=true;TrustServerCertificate=true";

	public static final void main(String[] args) {

		try (Connection conn = DriverManager.getConnection(connectionURL); Statement stmt = conn.createStatement();) {
			String sql = "select CompanyName, Address, City, Region, PostalCode, Country from Customers";
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println();
			
			while (rs.next()) {
				String companyName = rs.getString("CompanyName");
				String address = rs.getString("Address");
				String city = rs.getString("City");
				String region = rs.getString("Region");
				String postal = rs.getString("PostalCode");
				String country = rs.getString("Country");
				
				if (region == null) {
					region = "n/a";
				}
				
				StringBuilder entry = new StringBuilder();
				entry.append(companyName);
				entry.append(" ");
				entry.append(address);
				entry.append(" ");
				entry.append(city);
				entry.append(" ");
				entry.append(region);
				entry.append(" ");
				entry.append(postal);
				entry.append(" ");
				entry.append(country);
				
				System.out.println(entry);
			}
			rs.close();
			
			
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}
}
