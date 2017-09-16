package de.linzn.viki.network.readIn;

import de.linzn.javaSocket.core.events.SocketTypeEvent;
import de.linzn.javaSocket.core.interfaces.EventTypes;
import de.linzn.javaSocket.core.interfaces.SocketTypeListener;
import de.linzn.viki.App;
import de.linzn.viki.internal.ifaces.RequestOwner;

public class AuthenticateEvent implements SocketTypeListener {

    private App app;

    public AuthenticateEvent(App app) {
        this.app = app;
    }

    @Override
    public EventTypes getType() {
        // TODO Auto-generated method stub
        return EventTypes.AUTHENTICATE;
    }

    @Override
    public void onTypeEvent(SocketTypeEvent event) {
        App.logger("New authenticated remoteClient: " + event.getSocketClient().getUUID());
        RequestOwner requestOwner = new RequestOwner(event.getSocketClient().getUUID());

        Runnable run = new Runnable() {
            @Override
            public void run() {
                requestOwner.sendNotification("Hallo. Du hast dich Authentifiziert");
            }
        };
        this.app.heartbeat.runTaskAsynchronous(run);
    }

}
