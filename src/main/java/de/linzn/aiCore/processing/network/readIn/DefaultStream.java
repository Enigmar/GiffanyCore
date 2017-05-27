package de.linzn.aiCore.processing.network.readIn;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.ClientType;
import de.linzn.aiCore.internal.container.ClientContainer;
import de.linzn.aiCore.processing.network.template.Channel;
import de.linzn.javaSocket.server.events.SocketDataEvent;
import de.linzn.javaSocket.server.interfaces.IDataListener;

import java.io.DataInputStream;
import java.io.IOException;

public class DefaultStream implements IDataListener {

    private App app;

    public DefaultStream(App app) {
        this.app = app;
    }

    @Override
    public String getChannel() {
        // TODO Auto-generated method stub
        return Channel.defaultChannel;
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
        ClientContainer clientContainer = new ClientContainer(event.getMessenger().clientUUID, ClientType.DEFAULT);

        this.app.inputProc.provideInput(clientContainer, values);

    }

}
