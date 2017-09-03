package de.linzn.viki.beta.skillTemplates;

import de.linzn.viki.App;
import de.linzn.viki.beta.ifaces.ISkillTemplate;
import de.linzn.viki.beta.ifaces.ParentSkill;
import de.linzn.viki.beta.ifaces.SubSkill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class ComputerTemplate implements ISkillTemplate {
    private ParentSkill parentSkill;
    private SubSkill subSkill;

    @Override
    public void setEnv(ParentSkill parentSkill, SubSkill subSkill) {
        this.subSkill = subSkill;
        this.parentSkill = parentSkill;
    }


    public boolean startComputer() {
            // Need "apt-get install etherwake" packet installed
            try {
                String pcName = (String) this.subSkill.serial_data.get("systemName");
                String mac = (String) this.subSkill.serial_data.get("macAddress");
                App.logger("Send wakeonlan packet to " + pcName + " with mac " + mac);
                Runtime.getRuntime().exec("etherwake " + mac).waitFor(1000, TimeUnit.MILLISECONDS);
                return true;
            } catch (IOException | InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }

    public boolean restartUnix() {
        // Need sshpass installed
        String systemName = (String) this.subSkill.serial_data.get("systemName");
        String ip = (String) (String) this.subSkill.serial_data.get("hostName");
        int port = Integer.parseInt((String) this.subSkill.serial_data.get("portNumber"));
        String user = (String) this.subSkill.serial_data.get("systemUser");
        String password = (String) this.subSkill.serial_data.get("systemPassword");
            try {
                App.logger("Send restart signal to " + systemName + " with hostName " + ip);
                Runtime.getRuntime().exec("sshpass -p '" + password + "' ssh " + user + "@" + ip + " -p " + port + " 'reboot'").waitFor(1000, TimeUnit.MILLISECONDS);
                return true;
            } catch (IOException | InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }

    public boolean shutdownUnix() {
        // Need sshpass installed
        String systemName = (String) this.subSkill.serial_data.get("systemName");
        String ip = (String) (String) this.subSkill.serial_data.get("hostName");
        int port = Integer.parseInt((String) this.subSkill.serial_data.get("portNumber"));
        String user = (String) this.subSkill.serial_data.get("systemUser");
        String password = (String) this.subSkill.serial_data.get("systemPassword");
            try {
                App.logger("Send shutdown signal to " + systemName + " with hostName " + ip);
                Runtime.getRuntime().exec("sshpass -p '" + password + "' ssh " + user + "@" + ip + " -p " + port + " 'shutdown -h now'").waitFor(1000, TimeUnit.MILLISECONDS);
                return true;
            } catch (IOException | InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }


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
