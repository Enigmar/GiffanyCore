package de.linzn.viki.network.readIn;

import de.linzn.jSocket.core.IncomingDataListener;
import de.linzn.viki.App;
import de.linzn.viki.internal.ifaces.RequestOwner;
import de.linzn.viki.internal.processor.SkillProcessor;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.UUID;

public class TerminalStream implements IncomingDataListener {

    private App app;

    public TerminalStream(App app) {
        this.app = app;
    }

    @Override
    public void onEvent(String channel, UUID uuid, byte[] bytes) {
        // TODO Auto-generated method stub
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
        String values = null;
        try {
            values = in.readUTF();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestOwner requestOwner = new RequestOwner(uuid);
        SkillProcessor skillProcessor = new SkillProcessor(requestOwner, values);
        skillProcessor.processing();

    }

}
