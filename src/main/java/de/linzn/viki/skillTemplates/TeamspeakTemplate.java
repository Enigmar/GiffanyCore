package de.linzn.viki.skillTemplates;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.linzn.viki.App;
import de.linzn.viki.internal.ifaces.ISkillTemplate;
import de.linzn.viki.internal.ifaces.ParentSkill;
import de.linzn.viki.internal.ifaces.RequestOwner;
import de.linzn.viki.internal.ifaces.SubSkill;

import java.util.Random;


public class TeamspeakTemplate implements ISkillTemplate {
    private RequestOwner requestOwner;
    private ParentSkill parentSkill;
    private SubSkill subSkill;
    private String prefix = this.getClass().getSimpleName() + "->";
    private TS3Api api;
    private TS3Query query;
    private TS3Config config;


    @Override
    public void setEnv(RequestOwner requestOwner, ParentSkill parentSkill, SubSkill subSkill) {
        this.requestOwner = requestOwner;
        this.subSkill = subSkill;
        this.parentSkill = parentSkill;
    }

    public void clientKick() {
        this.setupConnection();
        final Client selectedClient = selectClient();

        if (selectedClient != null) {
            App.logger(prefix + "clientKick-->" + "kick client " + selectedClient.getNickname());
            this.requestOwner.sendNotification(((String) this.subSkill.serial_data.get("success")).replace("${name}", selectedClient.getNickname()));
            this.api.kickClientFromServer("Wurde auf Skillanfrage entfernt!", selectedClient);
        } else {
            App.logger(prefix + "clientKick-->" + "no client found");
            this.requestOwner.sendNotification((String) this.subSkill.serial_data.get("failed"));
        }
        this.closeConnection();

    }


    private Client selectClient() {
        App.logger(prefix + "selectClient-->" + "select client from array");
        for (Client client : api.getClients()) {
            if (!client.isServerQueryClient()) {
                for (String name : this.subSkill.inputArray) {
                    if (client.getNickname().toLowerCase().matches(".*" + name.toLowerCase() + ".*")) {
                        App.logger(prefix + "selectClient-->" + "found client " + client.getNickname());
                        return client;
                    }
                }
            }
        }
        return null;
    }

    private void setupConnection() {
        App.logger(prefix + "setupConnection-->" + "create connection");
        config = new TS3Config();
        config.setHost((String) this.subSkill.serial_data.get("hostName"));

        query = new TS3Query(config);
        query.connect();

        api = query.getApi();
        api.login((String) this.subSkill.serial_data.get("systemUser"), (String) this.subSkill.serial_data.get("systemPassword"));
        api.selectVirtualServerById(Integer.valueOf((String) this.subSkill.serial_data.get("id")));
        if (!api.setNickname((String) this.subSkill.serial_data.get("systemName"))) {
            api.setNickname((String) this.subSkill.serial_data.get("systemName") + "-" + (new Random().nextInt(30) + 1));
        }
    }

    private void closeConnection() {
        App.logger(prefix + "closeConnection-->" + "close connection");
        this.query.exit();
    }


}
