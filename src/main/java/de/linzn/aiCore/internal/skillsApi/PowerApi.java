package de.linzn.aiCore.internal.skillsApi;

import de.linzn.aiCore.App;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PowerApi {
    public App app;

    public PowerApi(App app) {
        this.app = app;
    }

    public void wakeOnLan(String device_id) {
        // Need "apt-get install etherwake" packet installed
        try {
            String mac = (String) App.appInstance.mysqlData.dbsetting.getSetting(device_id + "_mac");
            App.logger("Send wakeonlan packet to " + mac);
            Runtime.getRuntime().exec("etherwake " + mac).waitFor(1000, TimeUnit.MILLISECONDS);
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void restartUnix(String device_id) {
        // Need deposit ssh key first
        String ip = (String) App.appInstance.mysqlData.dbsetting.getSetting(device_id + "_host");
        int port = (int) App.appInstance.mysqlData.dbsetting.getSetting(device_id + "_port");
        String user = (String) App.appInstance.mysqlData.dbsetting.getSetting(device_id + "_user");


        try {
            App.logger("Send restart signal to " + ip);
            Runtime.getRuntime().exec("ssh " + user + "@" + ip + " -p " + port + " 'reboot'").waitFor(1000, TimeUnit.MILLISECONDS);
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void shutdownUnix(String device_id) {
        // Need deposit ssh key first
        String ip = (String) App.appInstance.mysqlData.dbsetting.getSetting(device_id + "_host");
        int port = (int) App.appInstance.mysqlData.dbsetting.getSetting(device_id + "_port");
        String user = (String) App.appInstance.mysqlData.dbsetting.getSetting(device_id + "_user");

        try {
            App.logger("Send shutdown signal to " + ip);
            Runtime.getRuntime().exec("ssh " + user + "@" + ip + " -p " + port + " 'shutdown -h now'").waitFor(1000, TimeUnit.MILLISECONDS);
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
