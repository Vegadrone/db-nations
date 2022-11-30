package org.generation.italy;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
	private static final String url = "jdbc:mysql://localhost:3306/nations";
	private static final String user = "root";
	private static final String password = "root";
	
	public static void main(String[] args) {

			
			try (Connection con = DriverManager.getConnection(url, user, password)) {
				
				final String sql ="SELECT countries.country_id, countries.name, regions.name, continents.name "
									+ "FROM countries "
									+ "JOIN regions "
									+	"ON countries.region_id  = regions.region_id "
									+ "JOIN continents "
									+	"ON regions.continent_id = continents.continent_id "
									+ "ORDER by countries.name ";
				
				try (PreparedStatement ps = con.prepareStatement(sql)) {
					try (ResultSet rs = ps.executeQuery()) {
						
						while(rs.next()) {
							
							final int id = rs.getInt(1);
							final String country = rs.getString(2);
							final String region = rs.getString(3);
							final String continent = rs.getString(4);
							
							System.out.println(
								id + " - " 
								+ country + " - " 
								+ region + " - " 
								+ continent
							);
						}
					}
				} 
				
			} catch (Exception e) {
				
				System.err.println("ERROR: " + e.getMessage());
			}
		}
	}
