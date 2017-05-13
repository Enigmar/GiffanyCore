package de.linzn.aiCore.database.access;

import de.linzn.aiCore.App;
import de.linzn.aiCore.database.MySQLDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSettings {
    public String aicore_name;
    public long aicore_created;
    private MySQLDatabase mysqlsb;

    public DBSettings(MySQLDatabase mysqlsb) {
        this.mysqlsb = mysqlsb;
    }

    public boolean loadSettings() {
        Connection con = this.mysqlsb.getConnection();
        try {
            Statement st = con.createStatement();
            String sql = ("SELECT * FROM aicore_setting;");
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("key").equalsIgnoreCase("aicore_name")) {
                    aicore_name = rs.getString("value");
                    App.logger("Setting aicore_name: " + aicore_name);
                } else if (rs.getString("key").equalsIgnoreCase("aicore_created")) {
                    aicore_created = rs.getLong("value");
                    App.logger("Setting aicore_created: " + aicore_created);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
