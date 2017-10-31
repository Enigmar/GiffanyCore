package de.linzn.leegianOS.network;

import de.linzn.jSocket.server.JServer;
import de.linzn.leegianOS.App;
import de.linzn.leegianOS.network.readIn.ConnectionStream;
import de.linzn.leegianOS.network.readIn.DefaultStream;
import de.linzn.leegianOS.network.readIn.TerminalStream;
import de.linzn.leegianOS.network.template.Channel;

public class NetworkModule {
    public JServer jServer;

    private App app;

    public NetworkModule(App app) {
        App.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
        this.app = app;
        this.jServer = new JServer(this.app.leegianOSConfiguration.socketHost, this.app.leegianOSConfiguration.socketPort);

        this.registerEvents();
        this.createNetwork();
    }

    private void registerEvents() {
        this.jServer.registerIncomingDataListener(Channel.terminalChannel, new TerminalStream(this.app));
        this.jServer.registerIncomingDataListener(Channel.defaultChannel, new DefaultStream(this.app));
        this.jServer.registerConnectionListener(new ConnectionStream(this.app));
    }

    public void createNetwork() {
        this.jServer.openServer();
    }

    public void deleteNetwork() {
        this.jServer.closeServer();
    }

}
