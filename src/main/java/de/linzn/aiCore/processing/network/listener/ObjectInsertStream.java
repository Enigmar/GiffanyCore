package de.linzn.aiCore.processing.network.listener;

import de.linzn.aiCore.App;
import de.linzn.javaSocket.server.events.SocketDataEvent;
import de.linzn.javaSocket.server.interfaces.IDataListener;

import java.io.DataInputStream;
import java.io.IOException;

public class ObjectInsertStream implements IDataListener {

    private App app;

    public ObjectInsertStream(App app) {
        this.app = app;
    }

    @Override
    public String getChannel() {
        // TODO Auto-generated method stub
        return this.app.networkProc.objectInsertChannel;
    }

    @Override
    public void onDataRecieve(SocketDataEvent event) {
        // TODO Auto-generated method stub
        DataInputStream in = this.app.networkProc.eSockserver.readByteArray(event.getStreamBytes());
        String objectName = null;
        String objectClass = null;
        String keywordName = null;
        String keywordFunction = null;
        String objectResult = null;

        try {
            objectName = in.readUTF();
            objectClass = in.readUTF();
            keywordName = in.readUTF();
            keywordFunction = in.readUTF();
            objectResult = in.readUTF();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.app.inputProc.receiveInsertObject(objectName, objectClass, keywordName, keywordFunction, objectResult);

    }

}
