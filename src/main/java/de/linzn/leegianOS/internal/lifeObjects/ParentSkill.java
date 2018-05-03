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

import de.linzn.leegianOS.LeegianOSApp;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Map;

public class ParentSkill {

    public int parentskill_id;
    public boolean standalone;
    public String trigger;
    public String[] inputArray;
    public String java_class;
    public String java_method;
    public Map serial_data;

    public ParentSkill(int parentskill_id, boolean standalone, String trigger, String[] inputArray, String java_class, String java_method, Map serial_data) {
        LeegianOSApp.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
        this.parentskill_id = parentskill_id;
        this.standalone = standalone;
        this.trigger = trigger;
        this.inputArray = ArrayUtils.removeElement(inputArray, trigger);
        this.java_class = java_class;
        this.java_method = java_method;
        this.serial_data = serial_data;
    }
}
