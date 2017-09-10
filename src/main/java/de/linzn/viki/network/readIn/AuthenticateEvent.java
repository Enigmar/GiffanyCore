package de.linzn.viki.network.readIn;

import de.linzn.javaSocket.server.events.SocketTypeEvent;
import de.linzn.javaSocket.server.interfaces.EventTypes;
import de.linzn.javaSocket.server.interfaces.ITypeListener;
import de.linzn.viki.App;
import de.linzn.viki.internal.ifaces.RequestOwner;

public class AuthenticateEvent implements ITypeListener {

    private App app;

    public AuthenticateEvent(App app) {
        this.app = app;
    }

    @Override
    public EventTypes getType() {
        // TODO Auto-generated method stub
        return EventTypes.AUTHENTICATESUCCESS;
    }

    @Override
    public void onTypeEvent(SocketTypeEvent event) {
        App.logger("New authenticated remoteClient: " + event.getRemoteClient().clientUUID);
        RequestOwner requestOwner = new RequestOwner(event.getRemoteClient().clientUUID);

        Runnable run = new Runnable() {
            @Override
            public void run() {
                requestOwner.sendNotification("Hallo und Willkommen. Ich hoffe dass du nun verbunden bist.");
            }
        };
        this.app.heartbeat.runDelayedTaskAsynchronous(run, 5000);
    }

}
