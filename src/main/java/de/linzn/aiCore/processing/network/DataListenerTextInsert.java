package de.linzn.aiCore.processing.network;

import java.io.DataInputStream;
import java.io.IOException;

import de.linzn.aiCore.App;
import de.linzn.javaSocket.server.events.SocketDataEvent;
import de.linzn.javaSocket.server.interfaces.IDataListener;

public class DataListenerTextInsert implements IDataListener {

	private App app;

	public DataListenerTextInsert(App app) {
		this.app = app;
	}

	@Override
	public String getChannel() {
		// TODO Auto-generated method stub
		return this.app.networkProc.textInsertChannel;
	}

	@Override
	public void onDataRecieve(SocketDataEvent event) {
		// TODO Auto-generated method stub
		DataInputStream in = this.app.networkProc.eSockserver.readByteArray(event.getStreamBytes());
		String textSentence = null;
		String textResult = null;
		try {
			textSentence = in.readUTF();
			textResult = in.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.app.inputProc.receiveInsertText(textSentence, textResult);
	}

}
