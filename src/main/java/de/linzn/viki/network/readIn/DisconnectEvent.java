package de.linzn.viki.network.readIn;

import de.linzn.javaSocket.server.events.SocketTypeEvent;
import de.linzn.javaSocket.server.interfaces.EventTypes;
import de.linzn.javaSocket.server.interfaces.ITypeListener;
import de.linzn.viki.App;

public class DisconnectEvent implements ITypeListener {

    private App app;

    public DisconnectEvent(App app) {
        this.app = app;
    }

    @Override
    public EventTypes getType() {
        // TODO Auto-generated method stub
        return EventTypes.DISCONNECT;
    }

    @Override
    public void onTypeEvent(SocketTypeEvent event) {
        // TODO Auto-generated method stub
        if (event.getMessenger().isAuthenticated.get()) {
            App.logger("Authenticated remoteClient closed connection : " + event.getMessenger().clientUUID);
        }
    }

}
