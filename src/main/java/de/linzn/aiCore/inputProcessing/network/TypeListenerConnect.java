package de.linzn.aiCore.inputProcessing.network;

import de.linzn.javaSocket.server.events.SocketTypeEvent;
import de.linzn.javaSocket.server.interfaces.EventTypes;
import de.linzn.javaSocket.server.interfaces.ITypeListener;

public class TypeListenerConnect implements ITypeListener{

	@Override
	public EventTypes getType() {
		// TODO Auto-generated method stub
		return EventTypes.CONNECT;
	}

	@Override
	public void onTypeEvent(SocketTypeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
