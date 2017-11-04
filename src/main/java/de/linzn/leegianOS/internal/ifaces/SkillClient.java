package de.linzn.leegianOS.internal.ifaces;


import de.linzn.leegianOS.LeegianOSApp;
import de.linzn.leegianOS.internal.voice.VoiceManagement;
import de.linzn.leegianOS.network.template.Channel;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
        LeegianOSApp.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
    }

    public SkillClient() {
        this.clientUUID = new UUID(0, 0);
        this.isNetworkclient = false;
        LeegianOSApp.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
    }

    public void sendResponseToClient(boolean useVoice, String notification, boolean needResponse) {
        if (this.isNetworkclient) {
            if (useVoice) {
                VoiceManagement voice = new VoiceManagement(this, notification, needResponse);
                voice.createVoice();
                voice.sendVoice();
            } else {

            }
            // write some thing
        } else {
            System.out.println("Response: " + notification);
        }
    }

    public void sendOSData() {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteOut);
        try {
            out.writeUTF(LeegianOSApp.leegianOSAppInstance.getVersion());
        } catch (IOException e) {
            e.printStackTrace();
        }

        LeegianOSApp.leegianOSAppInstance.networkProc.jServer.getClient(this.clientUUID).writeOutput(Channel.leegianOSData, byteOut.toByteArray());
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
