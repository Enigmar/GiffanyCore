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

import de.linzn.leegianOS.internal.objectDatabase.clients.SkillClient;
import de.linzn.leegianOS.internal.objectDatabase.skillType.ParentSkill;
import de.linzn.leegianOS.internal.objectDatabase.skillType.SubSkill;

public interface ISkill {

    void setEnv(SkillClient skillClient, ParentSkill parentSkill, SubSkill subSkill);

}
