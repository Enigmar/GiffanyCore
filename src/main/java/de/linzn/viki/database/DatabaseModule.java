package de.linzn.viki.database;

import de.linzn.viki.App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseModule {
    // Define variables
    private App app;
    private String url;
    private String username;
    private String password;

    /* Create class instance */
    public DatabaseModule(App app) {
        App.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
        this.app = app;
        this.url = "jdbc:mysql://" + this.app.vikiConfiguration.sqlHostName + ":" + this.app.vikiConfiguration.sqlPort + "/"
                + this.app.vikiConfiguration.sqlDatabaseName;
        this.username = this.app.vikiConfiguration.sqlUserName;
        this.password = this.app.vikiConfiguration.sqlPassword;
        this.releaseConnection(getConnection());
    }


    /* Return a new mysql connection */
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(this.url, this.username, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* Clear an mysql connection*/
    public void releaseConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}