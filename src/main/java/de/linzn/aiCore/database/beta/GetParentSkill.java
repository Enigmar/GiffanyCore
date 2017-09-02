package de.linzn.aiCore.database.beta;

import de.linzn.aiCore.App;
import de.linzn.aiCore.database.DatabaseModule;
import org.apache.commons.lang3.ArrayUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetParentSkill {
    private DatabaseModule mysqldb;
    private String[] input;

    public GetParentSkill(String[] input) {
        this.input = input;
        this.mysqldb = App.appInstance.mysqlData;
    }

    public ParentSkill getSkill() {

        try {
            Connection con = this.mysqldb.getConnection();
            Statement st = con.createStatement();

            for (String inputSplit : this.input) {
                // Do some thing
                String sqlquerry = ("SELECT parentskill_id, trigger FROM parentskills_named WHERE trigger ='" + inputSplit + "'");
                ResultSet rs = st.executeQuery(sqlquerry);
                if (rs.next()) {
                    int parentskillID = rs.getInt("parentskill_id");
                    String trigger = rs.getString("trigger");
                    sqlquerry = ("SELECT * FROM parentskills WHERE parentskill_id ='" + parentskillID + "'");
                    rs = st.executeQuery(sqlquerry);
                    if (rs.next()) {
                        int parentskill_id = rs.getInt("parentskill_id");
                        boolean standalone = rs.getBoolean("standalone");
                        String java_class = rs.getString("java_class");
                        String java_function = rs.getString("java_method");
                        String serial_data = rs.getString("serial_data");
                        return new ParentSkill(parentskill_id, standalone, trigger, this.input, java_class, java_function, serial_data);
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
