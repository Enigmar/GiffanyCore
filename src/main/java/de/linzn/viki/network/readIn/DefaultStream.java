package de.linzn.viki.network.readIn;

import de.linzn.javaSocket.server.events.SocketDataEvent;
import de.linzn.javaSocket.server.interfaces.IDataListener;
import de.linzn.viki.App;
import de.linzn.viki.internal.ifaces.RequestOwner;
import de.linzn.viki.internal.processor.SkillProcessor;
import de.linzn.viki.network.template.Channel;

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
        RequestOwner requestOwner = new RequestOwner(event.getMessenger().clientUUID);
        SkillProcessor skillProcessor = new SkillProcessor(requestOwner, values);
        skillProcessor.processing();

    }

}
