package de.linzn.viki.beta.skillTemplates;

import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.linzn.viki.beta.ifaces.ISkillTemplate;
import de.linzn.viki.beta.ifaces.ParentSkill;
import de.linzn.viki.beta.ifaces.SubSkill;


public class TeamspeakTemplate implements ISkillTemplate {
    private ParentSkill parentSkill;
    private SubSkill subSkill;
    private TS3ApiAsync api;
    private TS3Query query;
    private TS3Config config;

    @Override
    public void setEnv(ParentSkill parentSkill, SubSkill subSkill) {
        this.subSkill = subSkill;
        this.parentSkill = parentSkill;
    }

    public void clientKick() {
        setupConnection();
        Client selectedClient = selectClient();


        if (selectedClient != null) {
            this.api.kickClientFromServer("Viki kick", selectedClient);
        }
    }


    private Client selectClient() {
        try {
            for (Client client : api.getClients().get()) {
                for (String name : this.subSkill.inputArray) {
                    if (client.getNickname().toLowerCase().matches(".*" + name.toLowerCase() + ".*")) {
                        return client;
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setupConnection() {
        config = new TS3Config();
        config.setHost((String) this.subSkill.serial_data.get("hostName"));

        query = new TS3Query(config);
        query.connect();

        api = query.getAsyncApi();
        api.login((String) this.subSkill.serial_data.get("systemUser"), (String) this.subSkill.serial_data.get("systemPassword"));
        api.selectVirtualServerById(Integer.valueOf((String) this.subSkill.serial_data.get("id")));
        api.setNickname((String) this.subSkill.serial_data.get("systemName"));
    }

    private void closeConnection() {
        this.query.exit();
    }


}
