package de.linzn.viki.beta.skillTemplates;

import de.linzn.viki.App;
import de.linzn.viki.beta.ifaces.ISkillTemplate;
import de.linzn.viki.beta.ifaces.ParentSkill;
import de.linzn.viki.beta.ifaces.SubSkill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ComputerTemplate implements ISkillTemplate {
    private ParentSkill parentSkill;
    private SubSkill subSkill;

    @Override
    public void setEnv(ParentSkill parentSkill, SubSkill subSkill) {
        this.subSkill = subSkill;
        this.parentSkill = parentSkill;
    }

    /*
        public boolean startComputer(String device_id) {
            // Need "apt-get install etherwake" packet installed
            try {
                String mac = (String) App.appInstance.mysqlData.dbsetting.getSetting(device_id + "_mac");
                App.logger("Send wakeonlan packet to " + mac);
                Runtime.getRuntime().exec("etherwake " + mac).waitFor(1000, TimeUnit.MILLISECONDS);
                return true;
            } catch (IOException | InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }

        public boolean restartUnix(String device_id) {
            // Need deposit ssh key first
            String ip = (String) App.appInstance.mysqlData.dbsetting.getSetting(device_id + "_host");
            int port = Integer.parseInt((String) App.appInstance.mysqlData.dbsetting.getSetting(device_id + "_port"));
            String user = (String) App.appInstance.mysqlData.dbsetting.getSetting(device_id + "_user");
            try {
                App.logger("Send restart signal to " + ip);
                Runtime.getRuntime().exec("ssh " + user + "@" + ip + " -p " + port + " 'reboot'").waitFor(1000, TimeUnit.MILLISECONDS);
                return true;
            } catch (IOException | InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }

        public boolean shutdownUnix(String device_id) {
            // Need deposit ssh key first
            String ip = (String) App.appInstance.mysqlData.dbsetting.getSetting(device_id + "_host");
            int port = Integer.parseInt((String) App.appInstance.mysqlData.dbsetting.getSetting(device_id + "_port"));
            String user = (String) App.appInstance.mysqlData.dbsetting.getSetting(device_id + "_user");
            try {
                App.logger("Send shutdown signal to " + ip);
                Runtime.getRuntime().exec("ssh " + user + "@" + ip + " -p " + port + " 'shutdown -h now'").waitFor(1000, TimeUnit.MILLISECONDS);
                return true;
            } catch (IOException | InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }

    */
    public void getSystemTemperature() {
        try {
            App.logger("Getting core temperatures");
            Float[] coreTemp = {};
            String[] cmd = {
                    "/bin/sh",
                    "-c",
                    "sensors | grep -A 0 'id' | cut -c18-22 && sensors | grep -A 0 'Core' | cut -c18-22"
            };
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            int i = 0;

            while ((line = b.readLine()) != null) {
                float temp = Float.parseFloat(line);
                coreTemp[i] = temp;
                i++;
            }
            System.out.println("Core Temp: " + coreTemp.toString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
