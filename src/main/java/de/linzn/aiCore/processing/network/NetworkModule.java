package de.linzn.aiCore.processing.network;

import de.linzn.aiCore.App;
import de.linzn.aiCore.processing.network.readIn.*;
import de.linzn.aiCore.processing.network.template.SocketTemplate;
import de.linzn.javaSocket.server.EnigmarSockserver;

public class NetworkModule {
    public EnigmarSockserver eSockserver;
    public SocketTemplate eSocktemplate;
    private App app;

    public NetworkModule(App app) {
        App.logger("Loading NetworkModule");
        this.app = app;
        this.eSocktemplate = new SocketTemplate();
        this.eSockserver = new EnigmarSockserver();
        this.eSockserver.setCoreMask(this.eSocktemplate);
        this.eSockserver.setPort(this.app.aiSettings.socketPort);
        this.eSockserver.setHostname(this.app.aiSettings.socketHost);
        this.eSockserver.setLogLevel(2);
        this.eSockserver.setup();
        this.registerEvents();
        this.createNetwork();
    }

    private void registerEvents() {
        this.eSockserver.registerEventListener(new AuthenticateEvent(this.app));
        this.eSockserver.registerEventListener(new DisconnectEvent(this.app));
        this.eSockserver.registerEventListener(new DefaultStream(this.app));
        this.eSockserver.registerEventListener(new TextInsertStream(this.app));
        this.eSockserver.registerEventListener(new ObjectInsertStream(this.app));
        this.eSockserver.registerEventListener(new RequestStream(this.app));
    }

    public void createNetwork() {
        this.eSockserver.start();
    }

    public void deleteNetwork() {
        this.eSockserver.stop();
    }

}
