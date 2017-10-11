package skills;

import de.linzn.viki.App;
import de.linzn.viki.internal.ifaces.ISkillTemplate;
import de.linzn.viki.internal.ifaces.ParentSkill;
import de.linzn.viki.internal.ifaces.RequestOwner;
import de.linzn.viki.internal.ifaces.SubSkill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class DefaultTemplate implements ISkillTemplate {
    private RequestOwner requestOwner;
    private ParentSkill parentSkill;
    private SubSkill subSkill;
    private String prefix = this.getClass().getSimpleName() + "->";

    @Override
    public void setEnv(RequestOwner requestOwner, ParentSkill parentSkill, SubSkill subSkill) {
        this.requestOwner = requestOwner;
        this.subSkill = subSkill;
        this.parentSkill = parentSkill;
    }


}
