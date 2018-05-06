/*
 * Copyright (C) 2018. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 */
package schedulers;

import de.linzn.leegianOS.internal.interfaces.IScheduler;
import de.linzn.leegianOS.internal.objectDatabase.TimeData;
import org.json.JSONObject;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class DefaultScheduler implements IScheduler {

    private boolean alive = false;

    @Override
    public void scheduler() {

    }

    @Override
    public UUID schedulerUUID() {
        return UUID.fromString("9add02a7-da9f-4e82-ad14-aeca93826916");
    }

    @Override
    public void addAnswerData(JSONObject data) {

    }

    @Override
    public void loopback() {

    }

    @Override
    public TimeData scheduler_timer() {
        return new TimeData(1, 10, TimeUnit.SECONDS);
    }

    @Override
    public boolean is_alive() {
        return this.alive;
    }

    @Override
    public void set_alive(boolean set) {
        this.alive = set;
    }

}
