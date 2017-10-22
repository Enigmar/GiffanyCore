package skills;

import de.linzn.viki.internal.ifaces.ISkillTemplate;
import de.linzn.viki.internal.ifaces.ParentSkill;
import de.linzn.viki.internal.ifaces.SkillClient;
import de.linzn.viki.internal.ifaces.SubSkill;

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

}
