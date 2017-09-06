package de.linzn.viki.internal.ifaces;

import de.linzn.javaSocket.server.run.RemoteClient;
import de.linzn.viki.App;

import java.util.UUID;

public class RequestOwner {
    public UUID clientUUID;
    public boolean isNetworkclient;

    public RequestOwner(UUID clientUUID) {
        this.clientUUID = clientUUID;
        this.isNetworkclient = true;
        App.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
    }

    public RequestOwner() {
        this.clientUUID = new UUID(0, 0);
        this.isNetworkclient = false;
        App.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
    }

    public void sendNotification(String notification) {
        if (this.isNetworkclient) {
            RemoteClient rClient = App.appInstance.networkProc.eSockserver.getClient(this.clientUUID);
            // write some thing
        } else {
            System.out.println("VIKI: " + notification);
        }
    }
}
