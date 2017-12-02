/*
 * Copyright (C) 2017. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.leegianOS.internal.lifeObjects;


import de.linzn.leegianOS.LeegianOSApp;
import de.linzn.leegianOS.internal.ifaces.ISkill;
import de.linzn.leegianOS.network.template.Channel;
import de.linzn.vikiSpeechApi.VikiSpeechAPI;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public class SkillClient {
    public UUID clientUUID;
    private boolean isSocketClient;
    private boolean waitingForResponse = false;
    private ISkill waitInSkill = null;
    private String[] responseInput;

    public SkillClient(UUID clientUUID) {
        this.clientUUID = clientUUID;
        this.isSocketClient = true;
        LeegianOSApp.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
    }

    public SkillClient() {
        this.clientUUID = new UUID(0, 0);
        this.isSocketClient = false;
        LeegianOSApp.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
    }

    public void sendResponse(boolean needResponse, String notificationText) {
        if (this.isSocketClient) {
            byte[] voiceBytes = new VikiSpeechAPI().requestVoiceStream(notificationText);
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(byteOut);
            try {
                out.writeBoolean(needResponse);
                out.writeUTF(notificationText);
                out.write(voiceBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            LeegianOSApp.leegianOSAppInstance.networkProc.jServer.getClient(this.clientUUID).writeOutput(Channel.voiceChannel, byteOut.toByteArray());
        } else {
            System.out.println("Response: " + notificationText);
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

    public String[] waitingSkillForResponse(ISkill iSkill, int timeInSec) {
        this.waitingForResponse = true;
        this.waitInSkill = iSkill;
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
