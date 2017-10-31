package de.linzn.leegianOS.database;

import de.linzn.leegianOS.LeegianOSApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseModule {
    // Define variables
    private LeegianOSApp leegianOSApp;
    private String url;
    private String username;
    private String password;

    /* Create class instance */
    public DatabaseModule(LeegianOSApp leegianOSApp) {
        LeegianOSApp.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
        this.leegianOSApp = leegianOSApp;
        this.url = "jdbc:mysql://" + this.leegianOSApp.leegianOSConfiguration.sqlHostName + ":" + this.leegianOSApp.leegianOSConfiguration.sqlPort + "/"
                + this.leegianOSApp.leegianOSConfiguration.sqlDatabaseName;
        this.username = this.leegianOSApp.leegianOSConfiguration.sqlUserName;
        this.password = this.leegianOSApp.leegianOSConfiguration.sqlPassword;
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
