/*
 * Copyright (C) 2018. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 */

package de.linzn.leegianOS.configuration;

import de.linzn.leegianOS.LeegianOSApp;

import java.io.*;
import java.util.Properties;

public class AppConfiguration {

    /* Variables */
    public String socketHost;
    public int socketPort;
    public String sqlHostName;
    public int sqlPort;
    public String sqlDatabaseName;
    public String sqlUserName;
    public String sqlPassword;
    private LeegianOSApp leegianOSApp;
    private String fileName = "leegianOS.properties";

    /* Create class instance */
    public AppConfiguration(LeegianOSApp leegianOSApp) {
        LeegianOSApp.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
        this.leegianOSApp = leegianOSApp;
        this.init();

    }

    /* Create folders and files*/
    public void init() {
        File skillDir = new File("skills");
        if (!skillDir.exists()) {
            skillDir.mkdir();
        }

        skillDir = new File("schedulers");
        if (!skillDir.exists()) {
            skillDir.mkdir();
        }

        File file = new File(this.fileName);
        if (!file.exists()) {
            create();
        }
        this.load();

    }

    /* Setup properies file with values */
    public void create() {

        Properties prop = new Properties();
        OutputStream output;

        try {

            output = new FileOutputStream(this.fileName);
            // set the properties value
            prop.setProperty("socketHost", "0.0.0.0");
            prop.setProperty("socketPort", "11102");

            prop.setProperty("sqlHostname", "127.0.0.1");
            prop.setProperty("sqlPort", "3306");
            prop.setProperty("sqlDatabaseName", "legianOS_db");
            prop.setProperty("sqlUserName", "leegianOS");
            prop.setProperty("sqlPassword", "123asdfaas");

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /* Load the file in the memory */
    public void load() {

        Properties prop = new Properties();
        InputStream input;

        try {
            input = new FileInputStream(this.fileName);

            prop.load(input);

            this.socketHost = prop.getProperty("socketHost");
            this.socketPort = Integer.parseInt(prop.getProperty("socketPort"));

            this.sqlHostName = prop.getProperty("sqlHostname");
            this.sqlPort = Integer.parseInt(prop.getProperty("sqlPort"));
            this.sqlDatabaseName = prop.getProperty("sqlDatabaseName");
            this.sqlUserName = prop.getProperty("sqlUserName");
            this.sqlPassword = prop.getProperty("sqlPassword");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
