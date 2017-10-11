package de.linzn.viki.network.readIn;

import de.linzn.jSocket.core.ConnectionListener;
import de.linzn.viki.App;

import java.util.UUID;

public class ConnectionStream implements ConnectionListener {

    private App app;

    public ConnectionStream(App app) {
        this.app = app;
    }


    @Override
    public void onConnectEvent(UUID uuid) {
        App.logger("Register new communication device: " + uuid);
    }

    @Override
    public void onDisconnectEvent(UUID uuid) {
        App.logger("Unregister communication device: " + uuid);
    }
}
