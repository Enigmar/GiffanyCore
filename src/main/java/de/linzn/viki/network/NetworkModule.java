package de.linzn.viki.network;

import de.linzn.javaSocket.server.EnigmarSockserver;
import de.linzn.viki.App;
import de.linzn.viki.network.readIn.AuthenticateEvent;
import de.linzn.viki.network.readIn.DefaultStream;
import de.linzn.viki.network.readIn.DisconnectEvent;
import de.linzn.viki.network.readIn.TerminalStream;
import de.linzn.viki.network.template.SocketTemplate;

public class NetworkModule {
    public EnigmarSockserver sockServer;
    public SocketTemplate sockTemplate;
    private App app;

    public NetworkModule(App app) {
        App.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
        this.app = app;
        this.sockTemplate = new SocketTemplate();
        this.sockServer = new EnigmarSockserver();
        this.sockServer.setCoreMask(this.sockTemplate);
        this.sockServer.setPort(this.app.vikiConfiguration.socketPort);
        this.sockServer.setHostname(this.app.vikiConfiguration.socketHost);
        this.sockServer.setLogLevel(2);
        this.sockServer.setup();
        this.registerEvents();
        this.createNetwork();
    }

    private void registerEvents() {
        this.sockServer.registerEventListener(new AuthenticateEvent(this.app));
        this.sockServer.registerEventListener(new DisconnectEvent(this.app));
        this.sockServer.registerEventListener(new DefaultStream(this.app));
        this.sockServer.registerEventListener(new TerminalStream(this.app));
    }

    public void createNetwork() {
        this.sockServer.start();
    }

    public void deleteNetwork() {
        this.sockServer.stop();
    }

}
