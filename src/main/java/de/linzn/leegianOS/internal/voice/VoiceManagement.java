package de.linzn.leegianOS.internal.voice;

import de.linzn.leegianOS.LeegianOSApp;
import de.linzn.leegianOS.internal.ifaces.SkillClient;
import de.linzn.leegianOS.network.template.Channel;
import de.linzn.vikiSpeechApi.VikiSpeechAPI;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class VoiceManagement {
    private String prefix = this.getClass().getSimpleName() + "->";
    private String textVoice;
    private byte[] bytes;
    private SkillClient skillClient;

    public VoiceManagement(SkillClient skillClient, String textVoice) {
        LeegianOSApp.logger(prefix + "creating Instance ");
        this.textVoice = textVoice;
        this.skillClient = skillClient;
    }

    public void createVoice() {
        LeegianOSApp.logger(prefix + "createVoice-->" + "get voice stream");
        this.bytes = new VikiSpeechAPI().requestVoiceStream(this.textVoice);
    }


    public void sendVoice() {
        LeegianOSApp.logger(prefix + "sendVoice-->" + "build stream");
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteOut);
        try {
            LeegianOSApp.logger(prefix + "sendVoice-->" + "put bytearray: " + this.bytes.length);
            out.write(this.bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LeegianOSApp.logger(prefix + "sendVoice-->" + "send to client");

        LeegianOSApp.leegianOSAppInstance.networkProc.jServer.getClient(this.skillClient.clientUUID).writeOutput(Channel.voiceChannel, byteOut.toByteArray());
    }
}
