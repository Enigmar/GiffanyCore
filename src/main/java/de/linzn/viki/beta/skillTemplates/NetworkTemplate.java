package de.linzn.viki.beta.skillTemplates;

import de.linzn.cbn.api.ch7466ce.CBNApi;
import de.linzn.viki.beta.ifaces.ISkillTemplate;
import de.linzn.viki.beta.ifaces.ParentSkill;
import de.linzn.viki.beta.ifaces.SubSkill;

public class NetworkTemplate implements ISkillTemplate {
    private ParentSkill parentSkill;
    private SubSkill subSkill;

    @Override
    public void setEnv(ParentSkill parentSkill, SubSkill subSkill) {
        this.subSkill = subSkill;
        this.parentSkill = parentSkill;
    }

    public boolean cbnModemRestart() {
        String cbnHost = (String) this.subSkill.serial_data.get("hostName");
        String cbnUsername = (String) this.subSkill.serial_data.get("systemUser");
        String cbnPassword = (String) this.subSkill.serial_data.get("systemPassword");
        CBNApi api = new CBNApi(cbnHost, cbnUsername, cbnPassword);
        try {
            api.restartCBN();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
