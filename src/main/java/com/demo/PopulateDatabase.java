package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class PopulateDatabase {

	private static final String MASTER_URL = "jdbc:mysql://localhost:3306/replication_db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "rootpass";

	public static void main(String[] args) {
		try (final Connection connection = DriverManager.getConnection(MASTER_URL, USERNAME, PASSWORD);
				final Statement statement = connection.createStatement()) {

			System.out.println("Starting writing data to database...");

			final String createTableQuery = "CREATE TABLE IF NOT EXISTS test (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), name2 VARCHAR(255));";
			statement.executeUpdate(createTableQuery);

			while (true) {
				final String insertDataQuery = "INSERT INTO test (name, name2) VALUES ('%s', '%s');".formatted(UUID.randomUUID(), UUID.randomUUID());
				System.out.printf("Performing insert operation: %s%n", insertDataQuery);
				statement.executeUpdate(insertDataQuery);
				Thread.sleep(Duration.of(5, ChronoUnit.SECONDS));
			}

		} catch (final InterruptedException | SQLException e ) {
			e.printStackTrace();
		}
	}
}