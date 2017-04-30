package de.linzn.aiCore.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import de.linzn.aiCore.App;

public class MySQLDatabase {
	private App app;
	private String url;
	private String username;
	private String password;
	private Connection connection;

	public MySQLDatabase(App app) {
		App.logger("Loading MySQLDatabase module.");
		this.app = app;
		this.url = "jdbc:mysql://" + this.app.aiSettings.sqlHostName + ":" + this.app.aiSettings.sqlPort + "/"
				+ this.app.aiSettings.sqlDatabaseName;
		this.username = this.app.aiSettings.sqlUserName;
		this.password = this.app.aiSettings.sqlPassword;
		updateConnection();
	}

	public Connection getConnection() {
		return updateConnection();
	}

	private Connection updateConnection() {

		try {
			if (this.connection == null || this.connection.isClosed()) {
				this.connection = DriverManager.getConnection(this.url, this.username, this.password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.connection;
	}

}
