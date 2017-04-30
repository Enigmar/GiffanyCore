package de.linzn.aiCore.inputProcessing.network;

import java.io.DataInputStream;
import java.io.IOException;

import de.linzn.aiCore.App;
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
		String[] values = new String[2];
		try {
			values[0] = in.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.app.inputProc.receiveInput(values);

	}

}
