package de.linzn.aiCore.database;

import de.linzn.aiCore.App;
import de.linzn.aiCore.database.access.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseModule {
    public DBSettings dbsetting;
    // Define variables
    private App app;
    private String url;
    private String username;
    private String password;

    // the class
    public DatabaseModule(App app) {
        App.logger("Loading DatabaseModule ");
        this.app = app;
        this.url = "jdbc:mysql://" + this.app.aiSettings.sqlHostName + ":" + this.app.aiSettings.sqlPort + "/"
                + this.app.aiSettings.sqlDatabaseName;
        this.username = this.app.aiSettings.sqlUserName;
        this.password = this.app.aiSettings.sqlPassword;
        this.dbsetting = new DBSettings(this);
        this.releaseConnection(getConnection());
    }


    public Connection getConnection() {
        try {
            return DriverManager.getConnection(this.url, this.username, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void releaseConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
