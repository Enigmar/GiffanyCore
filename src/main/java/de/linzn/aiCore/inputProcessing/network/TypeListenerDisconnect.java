package de.linzn.aiCore.inputProcessing.network;

import de.linzn.aiCore.App;
import de.linzn.javaSocket.server.events.SocketTypeEvent;
import de.linzn.javaSocket.server.interfaces.EventTypes;
import de.linzn.javaSocket.server.interfaces.ITypeListener;

public class TypeListenerDisconnect implements ITypeListener {

	private App app;

	public TypeListenerDisconnect(App app) {
		this.app = app;
	}

	@Override
	public EventTypes getType() {
		// TODO Auto-generated method stub
		return EventTypes.DISCONNECT;
	}

	@Override
	public void onTypeEvent(SocketTypeEvent event) {
		// TODO Auto-generated method stub
		App.logger("A Client disconnect: " + event.getMessenger().getClientUUID());
	}

}
