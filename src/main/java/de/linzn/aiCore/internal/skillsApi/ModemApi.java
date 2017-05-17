package de.linzn.aiCore.internal.skillsApi;

import de.linzn.aiCore.App;
import de.linzn.cbn.api.ch7466ce.CBNApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ModemApi {
    public App app;

    public ModemApi(App app) {
        this.app = app;
    }

  public void restartCBNModem(){
        CBNApi api = new CBNApi("10.40.0.1", "admin", "test123");
        api.restartCBN();
  }

}
