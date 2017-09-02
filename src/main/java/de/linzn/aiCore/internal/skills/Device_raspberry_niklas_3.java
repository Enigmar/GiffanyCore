package de.linzn.aiCore.internal.skills;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.IExecutedSkill;
import de.linzn.aiCore.internal.Reflector;
import de.linzn.aiCore.internal.container.ClientContainer;
import de.linzn.aiCore.processing.network.writeBack.SendNotification;

public class Device_raspberry_niklas_3 implements IExecutedSkill {
    private ClientContainer clientContainer;
    private ObjectContainer objectcontainer;
    private KeywordContainer keywordContainer;

    @Override
    public void initial(ClientContainer clientCon, ObjectContainer objectCon, KeywordContainer keywordCon) {
        this.clientContainer = clientCon;
        this.objectcontainer = objectCon;
        this.keywordContainer = keywordCon;
    }

    @Override
    public void executeSkill(String function) {
        new Reflector().functionRunner(this, function);
    }

    @Override
    public void executeResult() {
        ResultContainer resultCon = App.appInstance.mysqlData.dbresult.getResultByObjects(objectcontainer, keywordContainer);
        if (resultCon == null) {
            App.logger("No ResultContainer found");
        } else {
            this.clientContainer.sendResult(resultCon.result);
            new SendNotification().sendNotification(resultCon.result);
        }
    }

    public void restart() {
        if (App.appInstance.skillApi.powerControl.restartUnix("device_raspberry_niklas_3")) {
            this.executeResult();
        }
    }


}
