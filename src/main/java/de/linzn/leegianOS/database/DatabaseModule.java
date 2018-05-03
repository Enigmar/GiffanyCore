/*
 * Copyright (C) 2018. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 */

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
        this.url = "jdbc:mysql://" + this.leegianOSApp.appConfiguration.sqlHostName + ":" + this.leegianOSApp.appConfiguration.sqlPort + "/"
                + this.leegianOSApp.appConfiguration.sqlDatabaseName;
        this.username = this.leegianOSApp.appConfiguration.sqlUserName;
        this.password = this.leegianOSApp.appConfiguration.sqlPassword;
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
