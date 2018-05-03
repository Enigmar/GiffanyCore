/*
 * Copyright (C) 2018. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 */

package de.linzn.leegianOS.internal.databaseAccess;

import de.linzn.leegianOS.LeegianOSApp;
import de.linzn.leegianOS.database.DatabaseModule;
import de.linzn.leegianOS.internal.objectDatabase.skillType.ParentSkill;
import de.linzn.leegianOS.internal.objectDatabase.skillType.SubSkill;
import de.linzn.leegianOS.internal.utils.SerialUtils;

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
        this.mysqldb = LeegianOSApp.leegianOSAppInstance.mysqlData;
        this.parentskill = parentskill;
    }

    /* Select the databaseAccess from the mysql database for the subSkill objectclass*/
    public SubSkill getSkill() {
        SubSkill subSkill = null;
        try {
            Connection con = this.mysqldb.getConnection();
            Statement st = con.createStatement();

            for (String inputSplit : this.input) {
                String sqlquerry = ("SELECT `word_group_id`, `synonym` FROM `speech_synonyms` WHERE `synonym` = '" + inputSplit + "'");
                ResultSet rs = st.executeQuery(sqlquerry);
                if (rs.next()) {
                    int word_group_id = rs.getInt("word_group_id");
                    String synonym = rs.getString("synonym");
                    sqlquerry = ("SELECT `subskill_id` FROM `subskills_assignment` WHERE `word_group_id` = '" + word_group_id + "' AND `parentskill_id` = '" + this.parentskill.parentskill_id + "'");
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

                            Map serial_map = SerialUtils.stringToMap(serial_data);
                            subSkill = new SubSkill(subskill_id, synonym, this.input, this.parentskill, java_class, java_function, serial_map);
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