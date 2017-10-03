package de.linzn.viki.network.readIn;

import de.linzn.javaSocket.core.events.DataInputEvent;
import de.linzn.javaSocket.core.interfaces.DataInputListener;
import de.linzn.viki.App;
import de.linzn.viki.internal.ifaces.RequestOwner;
import de.linzn.viki.internal.processor.SkillProcessor;
import de.linzn.viki.network.template.Channel;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class TerminalStream implements DataInputListener {

    private App app;

    public TerminalStream(App app) {
        this.app = app;
    }

    @Override
    public String getChannel() {
        // TODO Auto-generated method stub
        return Channel.terminalChannel;
    }

    @Override
    public void onDataRecieve(DataInputEvent dataInputEvent) {
        // TODO Auto-generated method stub
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(dataInputEvent.getStreamBytes()));
        String values = null;
        try {
            values = in.readUTF();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestOwner requestOwner = new RequestOwner(dataInputEvent.getSocketClient().getUUID());
        SkillProcessor skillProcessor = new SkillProcessor(requestOwner, values);
        skillProcessor.processing();

    }

}
