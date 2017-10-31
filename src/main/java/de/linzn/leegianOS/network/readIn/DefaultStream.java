package de.linzn.leegianOS.network.readIn;

import de.linzn.jSocket.core.IncomingDataListener;
import de.linzn.leegianOS.LeegianOSApp;
import de.linzn.leegianOS.internal.ifaces.SkillClient;
import de.linzn.leegianOS.internal.processor.SkillProcessor;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.UUID;

public class DefaultStream implements IncomingDataListener {

    private LeegianOSApp leegianOSApp;

    public DefaultStream(LeegianOSApp leegianOSApp) {
        this.leegianOSApp = leegianOSApp;
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
            SkillClient skillClient = leegianOSApp.skillClientList.get(uuid);
            SkillProcessor skillProcessor = new SkillProcessor(skillClient, finalValues);
            skillProcessor.processing();
        };
        this.leegianOSApp.heartbeat.runTaskAsynchronous(runnable);


    }

}
