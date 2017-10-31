package de.linzn.leegianOS.network.readIn;

import de.linzn.jSocket.core.ConnectionListener;
import de.linzn.leegianOS.App;
import de.linzn.leegianOS.internal.ifaces.SkillClient;

import java.util.UUID;

public class ConnectionStream implements ConnectionListener {

    private App app;

    public ConnectionStream(App app) {
        this.app = app;
    }


    @Override
    public void onConnectEvent(final UUID uuid) {
        App.logger("Register new communication device: " + uuid);
        SkillClient skillClient = new SkillClient(uuid);
        this.app.skillClientList.put(skillClient.clientUUID, skillClient);
    }

    @Override
    public void onDisconnectEvent(final UUID uuid) {
        App.logger("Unregister communication device: " + uuid);
        this.app.skillClientList.remove(uuid);
    }
}
