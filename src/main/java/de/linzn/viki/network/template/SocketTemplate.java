package de.linzn.viki.network.template;


import de.linzn.javaSocket.core.interfaces.IMask;
import de.linzn.viki.App;

import java.util.UUID;

public class SocketTemplate implements IMask {

    @Override
    public void schedulerAsync(Runnable runnable) {
        // TODO Auto-generated method stub
        App.appInstance.heartbeat.runTaskAsynchronous(runnable);

    }

    @Override
    public void schedulerSync(Runnable runnable) {
        // TODO Auto-generated method stub
        App.appInstance.heartbeat.runTaskSynchronous(runnable);
    }

    @Override
    public void debugInfo(String string) {
        // TODO Auto-generated method stub
        App.logger(string);

    }

    @Override
    public UUID clientUUID() {
        return null;
    }

}
