package de.linzn.viki.internal.data;

import de.linzn.viki.App;
import de.linzn.viki.database.DatabaseModule;
import de.linzn.viki.internal.ifaces.CodecUtils;
import de.linzn.viki.internal.ifaces.ParentSkill;
import de.linzn.viki.internal.ifaces.SubSkill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;


public class GetSubSkill {
    /* Variables */
    private DatabaseModule mysqldb;
    private String[] input;
    private ParentSkill parentskill;

    /* Create class instance */
    public GetSubSkill(ParentSkill parentskill) {
        this.input = parentskill.inputArray;
        this.mysqldb = App.appInstance.mysqlData;
        this.parentskill = parentskill;
    }

    /* Select the data from the mysql database for the subSkill objectclass*/
    public SubSkill getSkill() {
        SubSkill subSkill = null;
        try {
            Connection con = this.mysqldb.getConnection();
            Statement st = con.createStatement();

            for (String inputSplit : this.input) {
                String sqlquerry = ("SELECT `content_group_id`, `content` FROM `speech_content` WHERE `content` = '" + inputSplit + "'");
                ResultSet rs = st.executeQuery(sqlquerry);
                if (rs.next()) {
                    int content_group_id = rs.getInt("content_group_id");
                    String trigger = rs.getString("content");
                    sqlquerry = ("SELECT `subskill_id` FROM `subskills_assignment` WHERE `content_group_id` = '" + content_group_id + "' AND `parentskill_id` = '" + this.parentskill.parentskill_id + "'");
                    rs = st.executeQuery(sqlquerry);
                    if (rs.next()) {
                        int subskillID = rs.getInt("subskill_id");
                        sqlquerry = ("SELECT * FROM `subskills` WHERE `subskill_id` = '" + subskillID + "'");
                        rs = st.executeQuery(sqlquerry);
                        if (rs.next()) {
                            int subskill_id = rs.getInt("subskill_id");
                            String java_class = rs.getString("java_class");
                            String java_function = rs.getString("java_method");
                            String serial_data = rs.getString("serial_data");

                            Map serial_map = CodecUtils.stringToMap(serial_data);
                            subSkill = new SubSkill(subskill_id, trigger, this.input, this.parentskill, java_class, java_function, serial_map);
                        }
                    }
                }
            }
            // Release connection.
            this.mysqldb.releaseConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subSkill;
    }

}