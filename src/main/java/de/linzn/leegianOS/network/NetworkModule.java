/*
 * Copyright (C) 2017. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.leegianOS.network;

import de.linzn.jSocket.server.JServer;
import de.linzn.leegianOS.LeegianOSApp;
import de.linzn.leegianOS.network.readIn.ConnectionStream;
import de.linzn.leegianOS.network.readIn.DefaultStream;
import de.linzn.leegianOS.network.readIn.TerminalStream;
import de.linzn.leegianOS.network.template.Channel;

public class NetworkModule {
    public JServer jServer;

    private LeegianOSApp leegianOSApp;

    public NetworkModule(LeegianOSApp leegianOSApp) {
        LeegianOSApp.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
        this.leegianOSApp = leegianOSApp;
        this.jServer = new JServer(this.leegianOSApp.leegianOSConfiguration.socketHost, this.leegianOSApp.leegianOSConfiguration.socketPort);

        this.registerEvents();
        this.createNetwork();
    }

    private void registerEvents() {
        this.jServer.registerIncomingDataListener(Channel.terminalChannel, new TerminalStream(this.leegianOSApp));
        this.jServer.registerIncomingDataListener(Channel.defaultChannel, new DefaultStream(this.leegianOSApp));
        this.jServer.registerConnectionListener(new ConnectionStream(this.leegianOSApp));
    }

    public void createNetwork() {
        this.jServer.openServer();
    }

    public void deleteNetwork() {
        this.jServer.closeServer();
    }

}
