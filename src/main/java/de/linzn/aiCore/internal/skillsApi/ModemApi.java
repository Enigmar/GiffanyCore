package de.linzn.aiCore.internal.skillsApi;

import de.linzn.aiCore.App;
import de.linzn.cbn.api.ch7466ce.CBNApi;

public class ModemApi {
    public App app;

    public ModemApi(App app) {
        this.app = app;
    }


    public void restartCBNModem() {
        String cbnHost = (String) this.app.mysqlData.dbsetting.getSetting("cbn_ip");
        String cbnUsername = (String) this.app.mysqlData.dbsetting.getSetting("cbn_username");
        String cbnPassword = (String) this.app.mysqlData.dbsetting.getSetting("cbn_password");
        CBNApi api = new CBNApi(cbnHost, cbnUsername, cbnPassword);
        api.restartCBN();
    }

}
