package de.linzn.aiCore.inputProcessing.network;

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

	}

}
