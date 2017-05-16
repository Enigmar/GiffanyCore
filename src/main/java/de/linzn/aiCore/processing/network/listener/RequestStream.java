package de.linzn.aiCore.processing.network.listener;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.ClientType;
import de.linzn.aiCore.internal.container.ClientContainer;
import de.linzn.aiCore.internal.requests.Panel;
import de.linzn.javaSocket.server.events.SocketDataEvent;
import de.linzn.javaSocket.server.interfaces.IDataListener;

import java.io.DataInputStream;
import java.io.IOException;

public class RequestStream implements IDataListener {

    private App app;

    public RequestStream(App app) {
        this.app = app;
    }

    @Override
    public String getChannel() {
        // TODO Auto-generated method stub
        return this.app.networkProc.requestDataTransfer;
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
        if (values.equalsIgnoreCase("get")) {
            ClientContainer clientContainer = new ClientContainer(event.getMessenger().getClientUUID(), ClientType.CONTROL);
            App.logger("New Request for data refresh by " + clientContainer.clientUUID);
            new Panel(clientContainer).sendUpdates();
        }


    }

}
