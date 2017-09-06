package de.linzn.viki.network;

import de.linzn.javaSocket.server.EnigmarSockserver;
import de.linzn.viki.App;
import de.linzn.viki.network.readIn.AuthenticateEvent;
import de.linzn.viki.network.readIn.DefaultStream;
import de.linzn.viki.network.readIn.DisconnectEvent;
import de.linzn.viki.network.template.SocketTemplate;

public class NetworkModule {
    public EnigmarSockserver eSockserver;
    public SocketTemplate eSocktemplate;
    private App app;

    public NetworkModule(App app) {
        App.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
        this.app = app;
        this.eSocktemplate = new SocketTemplate();
        this.eSockserver = new EnigmarSockserver();
        this.eSockserver.setCoreMask(this.eSocktemplate);
        this.eSockserver.setPort(this.app.vikiConfiguration.socketPort);
        this.eSockserver.setHostname(this.app.vikiConfiguration.socketHost);
        this.eSockserver.setLogLevel(2);
        this.eSockserver.setup();
        this.registerEvents();
        this.createNetwork();
    }

    private void registerEvents() {
        this.eSockserver.registerEventListener(new AuthenticateEvent(this.app));
        this.eSockserver.registerEventListener(new DisconnectEvent(this.app));
        this.eSockserver.registerEventListener(new DefaultStream(this.app));
    }

    public void createNetwork() {
        this.eSockserver.start();
    }

    public void deleteNetwork() {
        this.eSockserver.stop();
    }

}
