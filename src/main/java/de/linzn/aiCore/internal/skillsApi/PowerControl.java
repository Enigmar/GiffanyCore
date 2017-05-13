package de.linzn.aiCore.internal.skillsApi;

import java.io.IOException;

public class PowerControl {

    public void wakeOnLan(String mac) {
        // Need "apt-get install etherwake" packet installed
        try {
            Runtime.getRuntime().exec("etherwake " + mac).waitFor();
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void restartUniversal(String ip, int port, String user) {
        // Need deposit ssh key first
        try {
            Runtime.getRuntime().exec("ssh " + user + "@" + ip + " -p " + port + " 'reboot'").waitFor();
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
