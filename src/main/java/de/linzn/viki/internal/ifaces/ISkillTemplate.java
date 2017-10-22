package de.linzn.viki.internal.ifaces;

public interface ISkillTemplate {

    public void setEnv(SkillClient skillClient, ParentSkill parentSkill, SubSkill subSkill);

    public void newClientResponse(String[] newParameter);


}
