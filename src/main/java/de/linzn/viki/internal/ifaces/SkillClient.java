package de.linzn.viki.internal.ifaces;


import de.linzn.viki.App;
import de.linzn.viki.internal.voice.VoiceManagement;

import java.util.UUID;

public class SkillClient {
    public UUID clientUUID;
    public boolean isNetworkclient;
    private boolean waitingForInput;
    private ISkillTemplate waitingIn;

    public SkillClient(UUID clientUUID) {
        this.clientUUID = clientUUID;
        this.isNetworkclient = true;
        this.waitingForInput = false;
        this.waitingIn = null;
        App.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
    }

    public SkillClient() {
        this.clientUUID = new UUID(0, 0);
        this.isNetworkclient = false;
        this.waitingForInput = false;
        this.waitingIn = null;
        App.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
    }

    public void sendResponseToClient(boolean useVoice, String notification) {
        if (this.isNetworkclient) {
            if (useVoice) {
                VoiceManagement voice = new VoiceManagement(this, notification);
                voice.createVoice();
                voice.sendVoice();
            } else {

            }
            // write some thing
        } else {
            System.out.println("Response: " + notification);
        }
    }

    public void setResponseWait(boolean waiting) {
        this.waitingForInput = waiting;
    }

    public boolean isResponseWaiting() {
        return this.waitingForInput;
    }

    public ISkillTemplate getResponseSkill() {
        return waitingIn;
    }

    public void setResponseSkill(ISkillTemplate iSkillTemplate) {
        this.waitingIn = iSkillTemplate;
    }
}
