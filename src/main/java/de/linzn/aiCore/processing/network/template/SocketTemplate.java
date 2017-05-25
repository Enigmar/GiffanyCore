package de.linzn.aiCore.processing.network.template;

import de.linzn.aiCore.App;
import de.linzn.javaSocket.server.interfaces.IMask;

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

}
