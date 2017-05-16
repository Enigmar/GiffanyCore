package de.linzn.aiCore.internal.skillsApi;

import de.linzn.aiCore.App;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PowerApi {
    public App app;

    public PowerApi(App app) {
        this.app = app;
    }

    public void wakeOnLan(String mac) {
        // Need "apt-get install etherwake" packet installed
        try {
            Runtime.getRuntime().exec("etherwake " + mac).waitFor(1000, TimeUnit.MILLISECONDS);
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void restartUniversal(String ip, int port, String user) {
        // Need deposit ssh key first
        try {
            Runtime.getRuntime().exec("ssh " + user + "@" + ip + " -p " + port + " 'reboot'").waitFor(1000, TimeUnit.MILLISECONDS);
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void shutdownUniversal(String ip, int port, String user) {
        // Need deposit ssh key first
        try {
            Runtime.getRuntime().exec("ssh " + user + "@" + ip + " -p " + port + " 'shutdown -h now'").waitFor(1000, TimeUnit.MILLISECONDS);
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
