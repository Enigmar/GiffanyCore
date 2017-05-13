package de.linzn.aiCore.processing.network;

import de.linzn.aiCore.App;
import de.linzn.aiCore.processing.network.listener.*;
import de.linzn.javaSocket.server.EnigmarSockserver;

public class NetworkProcessing {
    private App app;
    public EnigmarSockserver eSockserver;
    public SocketTemplate eSocktemplate;

    // Channels
    public String defaultChannel = "defaultTransfer";
    public String resultChannel = "resultTransfer";
    public String textInsertChannel = "textInsertChannel";
    public String objectInsertChannel = "objectInsertChannel";
    public String requestDataTransfer = "requestDataTransfer";

    public NetworkProcessing(App app) {
        App.logger("Loading NetworkProcessing module.");
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
        this.eSockserver.registerEventListener(new ConnectEvent(this.app));
        this.eSockserver.registerEventListener(new DisconnectEvent(this.app));
        this.eSockserver.registerEventListener(new DefaultStream(this.app));
        this.eSockserver.registerEventListener(new TestInsertStream(this.app));
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
