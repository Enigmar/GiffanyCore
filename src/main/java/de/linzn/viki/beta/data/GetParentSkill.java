package de.linzn.viki.beta.data;

import de.linzn.viki.App;
import de.linzn.viki.beta.ifaces.CodecUtils;
import de.linzn.viki.beta.ifaces.ParentSkill;
import de.linzn.viki.database.DatabaseModule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class GetParentSkill {
    private DatabaseModule mysqldb;
    private String[] input;

    public GetParentSkill(String[] input) {
        this.input = input;
        this.mysqldb = App.appInstance.mysqlData;
    }

    public ParentSkill getSkill() {
        ParentSkill parentSkill = null;
        try {
            Connection con = this.mysqldb.getConnection();
            Statement st = con.createStatement();

            for (String inputSplit : this.input) {
                String sqlquerry = ("SELECT `content_id`, `content` FROM `speech_content` WHERE `content` = '" + inputSplit + "'");
                ResultSet rs = st.executeQuery(sqlquerry);
                if (rs.next()) {
                    int content_id = rs.getInt("content_id");
                    String trigger = rs.getString("content");
                    // Do some thing
                    sqlquerry = ("SELECT `parentskill_id` FROM `parentskills_assignment` WHERE `content_id` = '" + content_id + "'");
                    rs = st.executeQuery(sqlquerry);
                    if (rs.next()) {
                        int parentskillID = rs.getInt("parentskill_id");
                        sqlquerry = ("SELECT * FROM `parentskills` WHERE `parentskill_id` = '" + parentskillID + "'");
                        rs = st.executeQuery(sqlquerry);
                        if (rs.next()) {
                            int parentskill_id = rs.getInt("parentskill_id");
                            boolean standalone = rs.getBoolean("standalone");
                            String java_class = rs.getString("java_class");
                            String java_function = rs.getString("java_method");
                            String serial_data = rs.getString("serial_data");

                            Map serial_map = CodecUtils.stringToMap(serial_data);
                            parentSkill = new ParentSkill(parentskill_id, standalone, trigger, this.input, java_class, java_function, serial_map);
                        }
                    }
                }
            }
            // Release connection.
            this.mysqldb.releaseConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parentSkill;
    }

}
