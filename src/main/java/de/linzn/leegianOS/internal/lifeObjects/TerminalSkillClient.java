package de.linzn.leegianOS.internal.lifeObjects;

import org.json.JSONObject;

import java.util.UUID;

public class TerminalSkillClient extends SkillClient {

    public TerminalSkillClient() {
        super(new UUID(0, 0));
    }

    public void printOutput(JSONObject data) {
        boolean needResponse = data.getJSONObject("dataValues").getBoolean("needResponse");
        String notificationText = data.getJSONObject("textValues").getString("notificationText");

        System.out.println("TerminalOutput: " + notificationText);
    }
}
