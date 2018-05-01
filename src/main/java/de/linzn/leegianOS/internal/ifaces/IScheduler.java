package de.linzn.leegianOS.internal.ifaces;


import org.json.JSONObject;

import java.util.UUID;

public interface IScheduler {

    void loadScheduler();

    void terminateScheduler();

    UUID schedulerUUID();

    void addAnswerData(JSONObject data);

}
