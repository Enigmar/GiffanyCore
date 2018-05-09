/*
 * Copyright (C) 2018. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 */

package de.linzn.leegianOS.internal.objectDatabase.skillType;

import de.linzn.leegianOS.LeegianOSApp;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Map;

public class SecondarySkill {

    public int subskill_id;
    public String trigger;
    public String[] inputArray;
    public PrimarySkill parentskill;
    public String java_class;
    public String java_method;
    public Map serial_data;

    public SecondarySkill(int subskill_id, String trigger, String[] inputArray, PrimarySkill parentskill, String java_class, String java_method, Map serial_data) {
        LeegianOSApp.logger(this.getClass().getSimpleName() + "->" + "creating Instance ", false);
        this.subskill_id = subskill_id;
        this.trigger = trigger;
        this.inputArray = ArrayUtils.removeElement(inputArray, trigger);
        this.parentskill = parentskill;
        this.java_class = java_class;
        this.java_method = java_method;
        this.serial_data = serial_data;
    }
}
