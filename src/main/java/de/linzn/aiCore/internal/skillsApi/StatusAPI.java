package de.linzn.aiCore.internal.skillsApi;

import de.linzn.aiCore.App;
import de.linzn.aiCore.processing.network.writeBack.SendNotification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class StatusAPI {
    public App app;
    private float[] coreTemp;


    public StatusAPI(App app) {
        this.app = app;
        this.coreTemp = new float[5];
        Runnable runTask = () -> parseStatus();
        App.appInstance.heartbeat.runRepeatTaskAsynchronous(runTask, 2000, 1000 * 30);


    }

    private void parseStatus() {
        try {
            getCoreTemperatur();
            if (!checkValideValues()) {
                new SendNotification().sendNotification("CoreTemp Critical: " + Arrays.toString(this.coreTemp));
                App.logger("CoreTemp Critical:" + Arrays.toString(this.coreTemp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }




    /* Temperatures of the VIKI system*/

    private boolean checkValideValues() {
        boolean tempOk = true;
        for (int i = 0; i < 5; i++) {
            if (this.coreTemp[i] >= 65) {
                tempOk = false;
            }
        }
        return tempOk;
    }

    private void getCoreTemperatur() throws IOException, InterruptedException {
        App.logger("Getting core temperatures");
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
    }

}
