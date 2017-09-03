package de.linzn.viki.beta.ifaces;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Map;

public class SubSkill {

    public int subskill_id;
    public String trigger;
    public String[] inputArray;
    public ParentSkill parentskill;
    public String java_class;
    public String java_method;
    public Map serial_data;

    public SubSkill(int subskill_id, String trigger, String[] inputArray, ParentSkill parentskill, String java_class, String java_method, Map serial_data) {
        this.subskill_id = subskill_id;
        this.trigger = trigger;
        this.inputArray = ArrayUtils.removeElement(inputArray, trigger);
        this.parentskill = parentskill;
        this.java_class = java_class;
        this.java_method = java_method;
        this.serial_data = serial_data;
    }
}
