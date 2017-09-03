package de.linzn.viki.beta.data;

import de.linzn.viki.App;
import de.linzn.viki.beta.ifaces.ParentSkill;
import de.linzn.viki.beta.ifaces.SubSkill;
import de.linzn.viki.database.DatabaseModule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GetSubSkill {
    private DatabaseModule mysqldb;
    private String[] input;
    private ParentSkill parentskill;

    public GetSubSkill(ParentSkill parentskill) {
        this.input = parentskill.inputArray;
        this.mysqldb = App.appInstance.mysqlData;
        this.parentskill = parentskill;
    }

    public SubSkill getSkill() {

        try {
            Connection con = this.mysqldb.getConnection();
            Statement st = con.createStatement();

            for (String inputSplit : this.input) {
                System.out.println("Test1 = " + inputSplit + this.parentskill.parentskill_id);
                // Do some thing
                String sqlquerry = ("SELECT `subskill_id`, `trigger` FROM `subskills_named` WHERE `trigger` = '" + inputSplit + "' AND `parentskill_id` = '" + this.parentskill.parentskill_id + "'");
                System.out.println("Test1.1");
                ResultSet rs = st.executeQuery(sqlquerry);
                System.out.println("Test2");
                if (rs.next()) {
                    System.out.println("Test3");
                    int subskillID = rs.getInt("subskill_id");
                    String trigger = rs.getString("trigger");
                    sqlquerry = ("SELECT * FROM `subskills` WHERE `subskill_id` = '" + subskillID + "'");
                    rs = st.executeQuery(sqlquerry);
                    if (rs.next()) {
                        System.out.println("Test4");
                        int subskill_id = rs.getInt("subskill_id");
                        String java_class = rs.getString("java_class");
                        String java_function = rs.getString("java_method");
                        String serial_data = rs.getString("serial_data");
                        return new SubSkill(subskill_id, trigger, this.input, this.parentskill, java_class, java_function, serial_data);
                    }
                }
            }
            // Release connection.
            this.mysqldb.releaseConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}