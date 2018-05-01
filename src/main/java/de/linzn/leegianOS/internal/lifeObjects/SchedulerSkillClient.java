package de.linzn.leegianOS.internal.lifeObjects;

import de.linzn.leegianOS.LeegianOSApp;
import de.linzn.leegianOS.internal.ifaces.IScheduler;
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
