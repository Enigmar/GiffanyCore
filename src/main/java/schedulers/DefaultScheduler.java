/*
 * Copyright (C) 2017. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 *
 */
package schedulers;

import de.linzn.leegianOS.internal.ifaces.IScheduler;
import org.json.JSONObject;

import java.util.UUID;


public class DefaultScheduler implements IScheduler {
    @Override
    public void loadScheduler() {

    }

    @Override
    public void terminateScheduler() {

    }

    @Override
    public UUID schedulerUUID() {
        return UUID.fromString("9add02a7-da9f-4e82-ad14-aeca93826916");
    }

    @Override
    public void addAnswerData(JSONObject data) {

    }

}
