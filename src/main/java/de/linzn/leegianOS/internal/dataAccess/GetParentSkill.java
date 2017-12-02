/*
 * Copyright (C) 2017. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.leegianOS.internal.dataAccess;

import de.linzn.leegianOS.LeegianOSApp;
import de.linzn.leegianOS.database.DatabaseModule;
import de.linzn.leegianOS.internal.lifeObjects.ParentSkill;
import de.linzn.leegianOS.internal.utils.ConvertUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class GetParentSkill {
    /* Variables */
    private DatabaseModule mysqldb;
    private String[] input;

    /* Create class instance */
    public GetParentSkill(String[] input) {
        this.input = input;
        this.mysqldb = LeegianOSApp.leegianOSAppInstance.mysqlData;
    }

    /* Select the dataAccess from the mysql database for the parentSkill objectclass*/
    public ParentSkill getSkill() {
        ParentSkill parentSkill = null;
        try {
            Connection con = this.mysqldb.getConnection();
            Statement st = con.createStatement();

            for (String inputSplit : this.input) {
                String sqlquerry = ("SELECT `word_group_id`, `synonym` FROM `speech_synonyms` WHERE `synonym` = '" + inputSplit + "'");
                ResultSet rs = st.executeQuery(sqlquerry);
                if (rs.next()) {
                    int word_group_id = rs.getInt("word_group_id");
                    String synonym = rs.getString("synonym");
                    sqlquerry = ("SELECT `parentskill_id` FROM `parentskills_assignment` WHERE `word_group_id` = '" + word_group_id + "'");
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

                            Map serial_map = ConvertUtils.stringToMap(serial_data);
                            parentSkill = new ParentSkill(parentskill_id, standalone, synonym, this.input, java_class, java_function, serial_map);
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
