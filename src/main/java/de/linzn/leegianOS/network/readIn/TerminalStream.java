package de.linzn.leegianOS.network.readIn;

import de.linzn.jSocket.core.IncomingDataListener;
import de.linzn.leegianOS.App;
import de.linzn.leegianOS.internal.ifaces.SkillClient;
import de.linzn.leegianOS.internal.processor.SkillProcessor;

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
        final String finalValues = values;
        Runnable runnable = () -> {
            SkillClient skillClient = app.skillClientList.get(uuid);
            SkillProcessor skillProcessor = new SkillProcessor(skillClient, finalValues);
            skillProcessor.processing();
        };
        this.app.heartbeat.runTaskAsynchronous(runnable);


    }

}
