/*
 * Copyright (C) 2018. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 */

package de.linzn.leegianOS.internal.objectDatabase.clients;

import de.linzn.leegianOS.LeegianOSApp;
import de.linzn.leegianOS.internal.interfaces.IScheduler;
import org.json.JSONObject;

import java.util.UUID;

public class SchedulerSkillClient extends SkillClient {

    public SchedulerSkillClient(UUID schedulerUUID) {
        super(schedulerUUID);
    }

    private IScheduler getScheduler() {
        return LeegianOSApp.leegianOSAppInstance.schedulerProcessor.schedulersList.get(this.clientUUID);
    }

    void scheduleOutput(JSONObject data) {
        getScheduler().addAnswerData(data);
    }
}
