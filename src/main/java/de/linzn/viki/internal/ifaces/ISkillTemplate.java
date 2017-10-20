package de.linzn.viki.internal.ifaces;

public interface ISkillTemplate {

    public void setEnv(RequestOwner requestOwner, ParentSkill parentSkill, SubSkill subSkill);

    public void addResponseParameter(String[] newParameter);


}
