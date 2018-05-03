/*
 * Copyright (C) 2018. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 */

package de.linzn.leegianOS.network.readIn;

import de.linzn.jSocket.core.ConnectionListener;
import de.linzn.leegianOS.LeegianOSApp;
import de.linzn.leegianOS.internal.lifeObjects.SkillClient;

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
