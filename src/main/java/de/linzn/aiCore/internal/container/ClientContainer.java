package de.linzn.aiCore.internal.container;


import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.ProcessType;
import de.linzn.aiCore.processing.network.template.Channel;
import de.linzn.javaSocket.server.run.RemoteClient;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ClientContainer {
    public UUID clientUUID;
    public ProcessType clientType;

    public ClientContainer(UUID clientUUID, ProcessType clientType) {
        this.clientUUID = clientUUID;
        this.clientType = clientType;
    }

    public void sendResult(String result) {
        if (this.clientType == ProcessType.TERMINAL) {
            if (this.clientUUID == App.appInstance.terminalProc.clientUUID) {
                System.out.println("Result: " + result);
            }
        } else {
            RemoteClient connectedClient = App.appInstance.networkProc.eSockserver.getClient(this.clientUUID);
            try {
                if (connectedClient != null) {
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = App.appInstance.networkProc.eSockserver.initialChannel(b,
                            Channel.resultChannel);

                    out.writeUTF(result);
                    App.appInstance.networkProc.eSockserver.sentToClient(connectedClient, b);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
