/*
 * Copyright (C) 2018. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 */

package skills;

import de.linzn.leegianOS.internal.interfaces.ISkill;
import de.linzn.leegianOS.internal.objectDatabase.clients.SkillClient;
import de.linzn.leegianOS.internal.objectDatabase.skillType.ParentSkill;
import de.linzn.leegianOS.internal.objectDatabase.skillType.SubSkill;
import org.json.JSONObject;

public class DefaultSkill implements ISkill {
    private SkillClient skillClient;
    private ParentSkill parentSkill;
    private SubSkill subSkill;
    private String prefix = this.getClass().getSimpleName() + "->";

    @Override
    public void setEnv(SkillClient skillClient, ParentSkill parentSkill, SubSkill subSkill) {
        this.skillClient = skillClient;
        this.subSkill = subSkill;
        this.parentSkill = parentSkill;
    }

    public void defaultSkill() {

        JSONObject item1 = new JSONObject();
        item1.put("needResponse", false);

        JSONObject item2 = new JSONObject();
        item2.put("notificationText", "Some thing went wrong. Default skill should never use!");

        JSONObject main = new JSONObject();
        main.put("dataValues", item1);
        main.put("textValues", item2);
        this.skillClient.sendResponse(main);
    }

}
