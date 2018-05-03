/*
 * Copyright (C) 2018. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 */

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
