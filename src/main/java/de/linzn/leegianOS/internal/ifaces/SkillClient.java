package de.linzn.leegianOS.internal.ifaces;


import de.linzn.leegianOS.App;
import de.linzn.leegianOS.internal.voice.VoiceManagement;

import java.util.UUID;

public class SkillClient {
    public UUID clientUUID;
    public boolean isNetworkclient;
    private boolean waitingForResponse = false;
    private ISkillTemplate waitInSkill = null;
    private String[] responseInput;

    public SkillClient(UUID clientUUID) {
        this.clientUUID = clientUUID;
        this.isNetworkclient = true;
        App.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
    }

    public SkillClient() {
        this.clientUUID = new UUID(0, 0);
        this.isNetworkclient = false;
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

    public void newClientResponse(String[] input) {
        this.responseInput = input;
        this.waitingForResponse = false;
    }

    public boolean isWaitingForResponse() {
        return this.waitingForResponse;
    }

    public String[] waitingSkillForResponse(ISkillTemplate iSkillTemplate, int timeInSec) {
        this.waitingForResponse = true;
        this.waitInSkill = iSkillTemplate;
        String[] response = null;
        for (int i = 0; i < timeInSec * 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!this.waitingForResponse) {
                response = this.responseInput;
                break;
            }
        }
        this.responseInput = null;
        this.waitInSkill = null;
        this.waitingForResponse = false;
        return response;
    }
}
