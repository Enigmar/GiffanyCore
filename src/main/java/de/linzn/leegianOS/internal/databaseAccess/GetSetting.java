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
import de.linzn.leegianOS.internal.objectDatabase.OBJSetting;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetSetting {
    /* Variables */
    private DatabaseModule mysqldb;
    private String settingName;

    /* Create class instance */
    public GetSetting(String settingName) {
        this.settingName = settingName;
        this.mysqldb = LeegianOSApp.leegianOSAppInstance.mysqlData;
    }

    public OBJSetting getSetting() {
        OBJSetting objSetting = null;
        LeegianOSApp.logger(this.getClass().getSimpleName() + "--> accessing setting-->" + settingName);
        try {
            Connection con = this.mysqldb.getConnection();
            Statement st = con.createStatement();
            String sqlquery = ("SELECT `jsonData` FROM `obj_settings` WHERE `setting_name` = '" + settingName + "'");
            ResultSet rs = st.executeQuery(sqlquery);
            if (rs.next()) {
                String jsonData = rs.getString("jsonData");
                objSetting = new OBJSetting(settingName);
                objSetting.setDataObject(jsonData);
            }
            this.mysqldb.releaseConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objSetting;
    }

    public void setSetting(OBJSetting objSetting) {

        try {
            Connection con = this.mysqldb.getConnection();
            Statement st = con.createStatement();
            String sqlquery = ("SELECT `jsonData` FROM `obj_settings` WHERE `setting_name` = '" + settingName + "'");
            ResultSet rs = st.executeQuery(sqlquery);
            if (!rs.next()) {
                sqlquery = ("INSERT INTO `obj_settings` (setting_name, jsonData) values ('" + objSetting.name + "', '" + objSetting.getJSONString() + "')");
                st.executeUpdate(sqlquery);
            }
            this.mysqldb.releaseConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
