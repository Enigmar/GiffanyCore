package de.linzn.giffanyCore.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import de.linzn.giffanyCore.App;

public class MySQLDatabase {
	private App app;
	private String url;
	private String username;
	private String password;
	private Connection connection;
	
	
	public MySQLDatabase(App app){
		this.app = app;
		updateConnection();
	}
	
	
	public Connection getConnection(){
		return updateConnection();
	}
	
	private Connection updateConnection(){

		try {
			if (this.connection == null || this.connection.isClosed()){
				this.connection = DriverManager.getConnection(this.url, this.username, this.password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.connection;
	}

}
