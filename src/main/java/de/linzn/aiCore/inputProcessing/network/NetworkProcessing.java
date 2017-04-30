package de.linzn.aiCore.inputProcessing.network;

import de.linzn.aiCore.App;
import de.linzn.javaSocket.server.EnigmarSockserver;

public class NetworkProcessing {
	private App app;
	public EnigmarSockserver eSockserver;
	public SocketTemplate eSocktemplate;

	// Channels
	public String defaultChannel = "defaultTransfer";

	public NetworkProcessing(App app) {
		App.logger("Loading NetworkProcessing module.");
		this.app = app;
		this.eSocktemplate = new SocketTemplate();
		this.eSockserver = new EnigmarSockserver();
		this.eSockserver.setCoreMask(this.eSocktemplate);
		this.eSockserver.setPort(this.app.aiSettings.socketPort);
		this.eSockserver.setHostname(this.app.aiSettings.socketHost);
		this.eSockserver.setup();
		this.eSockserver.registerEventListener(new TypeListenerConnect(this.app));
		this.eSockserver.registerEventListener(new TypeListenerDisconnect(this.app));
		this.eSockserver.registerEventListener(new DataListenerDefault(this.app));
		this.createNetwork();
	}

	public void createNetwork() {
		this.eSockserver.start();
	}

	public void deleteNetwork() {
		this.eSockserver.stop();
	}

}
