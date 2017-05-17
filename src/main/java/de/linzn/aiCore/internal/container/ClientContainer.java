package de.linzn.aiCore.internal.container;


import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.ClientType;
import de.linzn.aiCore.processing.network.template.Channel;
import de.linzn.javaSocket.server.run.ConnectedClient;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ClientContainer {
    public UUID clientUUID;
    public ClientType clientType;

    public ClientContainer(UUID clientUUID, ClientType clientType) {
        this.clientUUID = clientUUID;
        this.clientType = clientType;
    }

    public void sendResult(String result) {
        if (this.clientType == ClientType.TERMINAL) {
            if (this.clientUUID == App.appInstance.terminalProc.clientUUID) {
                System.out.println("Result: " + result);
            }
        } else {
            ConnectedClient connectedClient = App.appInstance.networkProc.eSockserver.getClient(this.clientUUID);
            if (connectedClient != null) {
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = App.appInstance.networkProc.eSockserver.initialChannel(b,
                        Channel.resultChannel);

                try {
                    out.writeUTF(result);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                App.appInstance.networkProc.eSockserver.sentToClient(connectedClient, b);
            }

        }
    }
}
