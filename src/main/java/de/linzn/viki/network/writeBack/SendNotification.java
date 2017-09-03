package de.linzn.viki.network.writeBack;


import de.linzn.viki.App;
import de.linzn.viki.network.template.Channel;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SendNotification {
    public void sendNotification(String notification) {

        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = App.appInstance.networkProc.eSockserver.initialChannel(b,
                    Channel.notificationTransfer);
            out.writeUTF(notification);
            App.appInstance.networkProc.eSockserver.sentToAllClients(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
