package de.linzn.viki.skillTemplates;

import de.linzn.viki.App;
import de.linzn.viki.internal.ifaces.ISkillTemplate;
import de.linzn.viki.internal.ifaces.ParentSkill;
import de.linzn.viki.internal.ifaces.RequestOwner;
import de.linzn.viki.internal.ifaces.SubSkill;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class VIKITemplate implements ISkillTemplate {
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

    public void reboot() {
        try {
            App.logger(prefix + "reboot-->viki ");
            this.requestOwner.sendNotification((String) this.subSkill.serial_data.get("begin"));
            Runtime.getRuntime().exec("service viki restart").waitFor(1000, TimeUnit.MILLISECONDS);

        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            this.requestOwner.sendNotification((String) this.subSkill.serial_data.get("failed"));
            System.err.println();
        }
    }

    public void stop() {

        try {
            App.logger(prefix + "stop-->viki ");
            Runtime.getRuntime().exec("service viki stop").waitFor(1000, TimeUnit.MILLISECONDS);

        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
