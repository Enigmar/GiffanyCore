package de.linzn.viki.internal.voice;

import com.darkprograms.speech.synthesiser.Synthesiser;
import de.linzn.viki.App;
import de.linzn.viki.internal.ifaces.CodecUtils;
import de.linzn.viki.internal.ifaces.RequestOwner;
import de.linzn.viki.network.template.Channel;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
        App.logger(prefix + "createVoice-->" + "prepare");
        String language = "de-de";
        Synthesiser synthesiser = new Synthesiser(language);
        try {
            InputStream unbufferedIn = synthesiser.getMP3Data(textVoice);
            App.logger(prefix + "createVoice-->" + "get voice stream");
            this.bytes = CodecUtils.inputstreamToBytes(unbufferedIn);
            App.logger(prefix + "createVoice-->" + "Bytes: " + this.bytes.length);
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
            return;
        }
    }

    public void sendVoice() {
        App.logger(prefix + "sendVoice-->" + "build stream");
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        DataOutputStream out = null;
        try {
            out = App.appInstance.networkProc.sockServer.initialChannel(byteOut, Channel.voiceChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            App.logger(prefix + "sendVoice-->" + "put bytearray: " + this.bytes.length);
            for (byte byt : this.bytes) {
                out.writeByte(byt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        App.logger(prefix + "sendVoice-->" + "send to client");
        App.appInstance.networkProc.sockServer.getClient(this.requestOwner.clientUUID).writeRemote(byteOut);
    }
}
