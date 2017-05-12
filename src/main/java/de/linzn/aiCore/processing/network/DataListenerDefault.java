package de.linzn.aiCore.processing.network;

import java.io.DataInputStream;
import java.io.IOException;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.ClientContainer;
import de.linzn.aiCore.internal.ClientType;
import de.linzn.javaSocket.server.events.SocketDataEvent;
import de.linzn.javaSocket.server.interfaces.IDataListener;

public class DataListenerDefault implements IDataListener {

	private App app;

	public DataListenerDefault(App app) {
		this.app = app;
	}

	@Override
	public String getChannel() {
		// TODO Auto-generated method stub
		return this.app.networkProc.defaultChannel;
	}

	@Override
	public void onDataRecieve(SocketDataEvent event) {
		// TODO Auto-generated method stub
		DataInputStream in = this.app.networkProc.eSockserver.readByteArray(event.getStreamBytes());
		String values = null;
		try {
			values = in.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClientContainer clientContainer = new ClientContainer(event.getMessenger().getClientUUID(), ClientType.DEFAULT);

		this.app.inputProc.receiveInput(clientContainer, values);

	}

}
