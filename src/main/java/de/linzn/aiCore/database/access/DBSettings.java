package de.linzn.aiCore.database.access;

import de.linzn.aiCore.App;
import de.linzn.aiCore.database.DatabaseModule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSettings {
    private DatabaseModule mysqldb;

    public DBSettings(DatabaseModule mysqlsb) {
        this.mysqldb = mysqlsb;
    }

    public Object getSetting(String settingKey) {
        App.logger("Try to load setting: " + settingKey);
        Object object = null;
        Connection con = this.mysqldb.getConnection();
        try {
            Statement st = con.createStatement();
            String sql = ("SELECT * FROM aicore_setting WHERE keyset = '" + settingKey + "';");
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                App.logger("Receive setting: " + settingKey);
                object = rs.getObject("value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return object;
    }
}
