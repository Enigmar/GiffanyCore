package skills;

import de.linzn.leegianOS.internal.ifaces.ISkillTemplate;
import de.linzn.leegianOS.internal.ifaces.ParentSkill;
import de.linzn.leegianOS.internal.ifaces.SkillClient;
import de.linzn.leegianOS.internal.ifaces.SubSkill;

public class DefaultTemplate implements ISkillTemplate {
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
        this.skillClient.sendResponseToClient(true, "Some thing went wrong. Default skill should never use!", false);
    }

}
