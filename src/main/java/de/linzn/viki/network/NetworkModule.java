package de.linzn.viki.network;

import de.linzn.jSocket.server.JServer;
import de.linzn.viki.App;
import de.linzn.viki.network.readIn.ConnectionStream;
import de.linzn.viki.network.readIn.DefaultStream;
import de.linzn.viki.network.readIn.TerminalStream;
import de.linzn.viki.network.template.Channel;

public class NetworkModule {
    public JServer jServer;

    private App app;

    public NetworkModule(App app) {
        App.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
        this.app = app;
        this.jServer = new JServer(this.app.vikiConfiguration.socketHost, this.app.vikiConfiguration.socketPort);

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
