package de.linzn.aiCore.internal.skillsApi;

import de.linzn.aiCore.App;
import de.linzn.cbn.api.ch7466ce.CBNApi;

public class ModemApi {
    public App app;

    public ModemApi(App app) {
        this.app = app;
    }


    public boolean restartCBNModem() {
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

}
