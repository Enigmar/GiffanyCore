package de.linzn.aiCore.internal.skillsApi;

import de.linzn.aiCore.App;
import de.linzn.cbn.api.ch7466ce.CBNApi;

public class NetworkDevicesApi {
    public App app;

    public NetworkDevicesApi(App app) {
        this.app = app;
        initialDefaults();
    }

    private void initialDefaults(){
        App.appInstance.heartbeat.runDailyTimedTaskAsynchronous( () -> cbnModemRestart(), 3, 30);
    }


    public boolean cbnModemRestart() {
        String cbnHost = (String) this.app.mysqlData.dbsetting.getSetting("cbn_ip");
        String cbnUsername = (String) this.app.mysqlData.dbsetting.getSetting("cbn_username");
        String cbnPassword = (String) this.app.mysqlData.dbsetting.getSetting("cbn_password");
        CBNApi api = new CBNApi(cbnHost, cbnUsername, cbnPassword);
        try {
            api.restartCBN();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean tplinkN750Restart(){
        return false;
    }

    public boolean tplinkWA901NDRestart(){
        return false;
    }

    public boolean tplinkSG105ERestart(){
        return false;
    }

    public boolean tplinkSG108ERestart(){
        return false;
    }

}
