package de.linzn.viki.configuration;

import de.linzn.viki.App;

import java.io.*;
import java.util.Properties;

public class VikiConfiguration {
    /* Variables */
    public String socketHost;
    public int socketPort;
    public String sqlHostName;
    public int sqlPort;
    public String sqlDatabaseName;
    public String sqlUserName;
    public String sqlPassword;
    private App app;
    private String fileName = "viki.properties";

    /* Create class instance */
    public VikiConfiguration(App app) {
        App.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
        this.app = app;
        this.init();

    }

    /* Create folders and files*/
    public void init() {
        File skillDir = new File("skills");
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
        OutputStream output = null;

        try {

            output = new FileOutputStream(this.fileName);
            // set the properties value
            prop.setProperty("socketHost", "0.0.0.0");
            prop.setProperty("socketPort", "11102");

            prop.setProperty("sqlHostname", "127.0.0.1");
            prop.setProperty("sqlPort", "3306");
            prop.setProperty("sqlDatabaseName", "viki_db");
            prop.setProperty("sqlUserName", "viki");
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
        InputStream input = null;

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
