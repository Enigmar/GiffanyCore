package de.linzn.aiCore.processing.network.writeBack;


import de.linzn.aiCore.App;
import de.linzn.aiCore.processing.network.template.Channel;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SendNotification {
    public void sendNotification(String notification) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = App.appInstance.networkProc.eSockserver.initialChannel(b,
                Channel.notificationTransfer);
        try {
            out.writeUTF(notification);
        } catch (IOException e) {
            e.printStackTrace();
        }
        App.appInstance.networkProc.eSockserver.sentToAllClients(b);
    }
}
