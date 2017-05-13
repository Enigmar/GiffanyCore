package de.linzn.aiCore.database;

import de.linzn.aiCore.App;
import de.linzn.aiCore.database.access.*;
import de.linzn.aiCore.database.insert.DBInsert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabase {
    // Define variables
    private App app;
    private String url;
    private String username;
    private String password;
    private Connection connection;
    public DBSettings dbsetting;
    public DBKeyword dbkeyword;
    public DBObject dbobject;
    public DBResult dbresult;
    public DBSentence dbsentence;
    public DBInsert dbinsert;

    // the class
    public MySQLDatabase(App app) {
        App.logger("Loading MySQLDatabase module.");
        this.app = app;
        this.url = "jdbc:mysql://" + this.app.aiSettings.sqlHostName + ":" + this.app.aiSettings.sqlPort + "/"
                + this.app.aiSettings.sqlDatabaseName;
        this.username = this.app.aiSettings.sqlUserName;
        this.password = this.app.aiSettings.sqlPassword;
        this.dbsetting = new DBSettings(this);
        this.dbkeyword = new DBKeyword(this);
        this.dbobject = new DBObject(this);
        this.dbresult = new DBResult(this);
        this.dbsentence = new DBSentence(this);
        this.dbinsert = new DBInsert(this);
        updateConnection();
        this.dbsetting.loadSettings();
    }

    // Get the active sql connection
    public Connection getConnection() {
        return updateConnection();
    }

    // Check and refresh the sql connection
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
