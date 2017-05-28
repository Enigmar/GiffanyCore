package de.linzn.aiCore.processing.network.writeBack;


import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.ProcessType;
import de.linzn.aiCore.internal.container.ClientContainer;
import de.linzn.aiCore.internal.container.PanelContainer;
import de.linzn.aiCore.processing.network.template.Channel;
import de.linzn.javaSocket.server.interfaces.ClientType;
import de.linzn.javaSocket.server.run.RemoteClient;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PanelData {
    public void sendDataToPanel(ClientContainer clientContainer, PanelContainer panelContainer) {
        if (clientContainer.clientType == ProcessType.NETSERVER && App.appInstance.networkProc.eSockserver.getClient(clientContainer.clientUUID).clientType == ClientType.DASHBOARD) {
            RemoteClient connectedClient = App.appInstance.networkProc.eSockserver.getClient(clientContainer.clientUUID);
            try {
                if (connectedClient != null) {
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = App.appInstance.networkProc.eSockserver.initialChannel(b,
                            Channel.requestDataTransfer);

                    out.writeFloat(panelContainer.temperature);
                    out.writeLong(panelContainer.date);
                    out.writeLong(panelContainer.refreshDate);

                    App.appInstance.networkProc.eSockserver.sentToClient(connectedClient, b);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
