package de.linzn.viki.internal.voice;

import de.linzn.viki.App;
import de.linzn.viki.internal.ifaces.RequestOwner;
import de.linzn.viki.network.template.Channel;
import de.linzn.vikiSpeechApi.VikiSpeechAPI;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class VoiceManagement {
    private String prefix = this.getClass().getSimpleName() + "->";
    private String textVoice;
    private byte[] bytes;
    private RequestOwner requestOwner;

    public VoiceManagement(RequestOwner requestOwner, String textVoice) {
        App.logger(prefix + "creating Instance ");
        this.textVoice = textVoice;
        this.requestOwner = requestOwner;
    }

    public void createVoice() {
        App.logger(prefix + "createVoice-->" + "get voice stream");
        this.bytes = VikiSpeechAPI.requestVoiceStream(this.textVoice);
    }


    public void sendVoice() {
        App.logger(prefix + "sendVoice-->" + "build stream");
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        DataOutputStream out = null;
        try {
            out = App.appInstance.networkProc.sockServer.initialChannel(byteOut);
            App.logger(prefix + "sendVoice-->" + "put bytearray: " + this.bytes.length);
            out.write(this.bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        App.logger(prefix + "sendVoice-->" + "send to client");
        App.appInstance.networkProc.sockServer.getClient(this.requestOwner.clientUUID).writeRemote(Channel.voiceChannel, byteOut, true);
    }
}
