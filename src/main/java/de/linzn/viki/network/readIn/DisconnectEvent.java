package de.linzn.viki.network.readIn;

import de.linzn.javaSocket.core.events.SocketTypeEvent;
import de.linzn.javaSocket.core.interfaces.EventTypes;
import de.linzn.javaSocket.core.interfaces.SocketTypeListener;
import de.linzn.viki.App;

public class DisconnectEvent implements SocketTypeListener {

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
    public void onTypeEvent(SocketTypeEvent socketTypeEvent) {
        // TODO Auto-generated method stub
        if (socketTypeEvent.getSocketClient().isAuthenticated()) {
            App.logger("SocketClient closed connection : " + socketTypeEvent.getSocketClient().getUUID());
        }
    }

}
