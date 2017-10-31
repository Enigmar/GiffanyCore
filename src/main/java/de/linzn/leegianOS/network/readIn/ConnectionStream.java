package de.linzn.leegianOS.network.readIn;

import de.linzn.jSocket.core.ConnectionListener;
import de.linzn.leegianOS.LeegianOSApp;
import de.linzn.leegianOS.internal.ifaces.SkillClient;

import java.util.UUID;

public class ConnectionStream implements ConnectionListener {

    private LeegianOSApp leegianOSApp;

    public ConnectionStream(LeegianOSApp leegianOSApp) {
        this.leegianOSApp = leegianOSApp;
    }


    @Override
    public void onConnectEvent(final UUID uuid) {
        LeegianOSApp.logger("Register new communication device: " + uuid);
        SkillClient skillClient = new SkillClient(uuid);
        this.leegianOSApp.skillClientList.put(skillClient.clientUUID, skillClient);
        skillClient.sendOSData();
    }

    @Override
    public void onDisconnectEvent(final UUID uuid) {
        LeegianOSApp.logger("Unregister communication device: " + uuid);
        this.leegianOSApp.skillClientList.remove(uuid);
    }
}
