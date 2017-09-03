package de.linzn.viki.beta.ifaces;

import org.apache.commons.lang3.ArrayUtils;

public class ParentSkill {

    public int parentskill_id;
    public boolean standalone;
    public String trigger;
    public String[] inputArray;
    public String java_class;
    public String java_method;
    public String serial_data;

    public ParentSkill(int parentskill_id, boolean standalone, String trigger, String[] inputArray, String java_class, String java_method, String serial_data) {
        this.parentskill_id = parentskill_id;
        this.standalone = standalone;
        this.trigger = trigger;
        this.inputArray = ArrayUtils.removeElement(inputArray, trigger);
        this.java_class = java_class;
        this.java_method = java_method;
        this.serial_data = serial_data;
    }
}
