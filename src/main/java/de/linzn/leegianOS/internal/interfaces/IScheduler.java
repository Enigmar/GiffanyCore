/*
 * Copyright (C) 2018. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 */

package de.linzn.leegianOS.internal.interfaces;


import de.linzn.leegianOS.internal.objectDatabase.TimeData;
import org.json.JSONObject;

import java.util.UUID;

public interface IScheduler {

    void scheduler();

    UUID schedulerUUID();

    void addAnswerData(JSONObject data);

    void loopback();

    TimeData scheduler_timer();

    boolean is_alive();

    void set_alive(boolean set);

}
