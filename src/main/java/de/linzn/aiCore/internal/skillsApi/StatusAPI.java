package de.linzn.aiCore.internal.skillsApi;

import de.linzn.aiCore.App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class StatusAPI {
    public App app;
    private float[] coreTemp;


    public StatusAPI(App app) {
        this.app = app;
        this.coreTemp = new float[13];
        Runnable runTask = () -> parseStatus();
        App.appInstance.heartbeat.runRepeatTaskAsynchronous(runTask, 2000, 1000 * 30);


    }

    private void parseStatus() {
        try {
            getCoreTemperatur();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void getCoreTemperatur() throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("sensors | grep -A 0 'id' | cut -c18-22 && sensors | grep -A 0 'Core' | cut -c18-22");
        p.waitFor();
        BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = "";
        int i = 0;

        while ((line = b.readLine()) != null) {
            System.out.println("test");
            float temp = Float.parseFloat(line);
            coreTemp[i] = temp;
            i++;
        }

        System.out.println("Global temp: " + coreTemp[0]);
        System.out.println("Core 0 temp: " + coreTemp[1]);
        System.out.println("Core 1 temp: " + coreTemp[2]);
        System.out.println("Core 2 temp: " + coreTemp[3]);
        System.out.println("Core 3 temp: " + coreTemp[4]);
    }

}
