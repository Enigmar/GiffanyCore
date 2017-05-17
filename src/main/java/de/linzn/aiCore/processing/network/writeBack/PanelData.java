package de.linzn.aiCore.processing.network.writeBack;


import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.ClientType;
import de.linzn.aiCore.internal.container.ClientContainer;
import de.linzn.aiCore.internal.container.PanelContainer;
import de.linzn.aiCore.processing.network.template.Channel;
import de.linzn.javaSocket.server.run.ConnectedClient;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PanelData {
    public void sendDataToPanel(ClientContainer clientContainer, PanelContainer panelContainer) {
        if (clientContainer.clientType == ClientType.CONTROL) {
            ConnectedClient connectedClient = App.appInstance.networkProc.eSockserver.getClient(clientContainer.clientUUID);
            if (connectedClient != null) {
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = App.appInstance.networkProc.eSockserver.initialChannel(b,
                        Channel.requestDataTransfer);
                try {
                    out.writeFloat(panelContainer.temperature);
                    out.writeLong(panelContainer.date);
                    out.writeLong(panelContainer.refreshDate);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                App.appInstance.networkProc.eSockserver.sentToClient(connectedClient, b);
            }
        }
    }
}
