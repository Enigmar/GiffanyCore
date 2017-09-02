package de.linzn.aiCore.internal.skills;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.IExecutedSkill;
import de.linzn.aiCore.internal.Reflector;
import de.linzn.aiCore.internal.container.ClientContainer;

public class Weather implements IExecutedSkill {
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
        }
    }


    public void today() {
        System.out.println("Asking for weather today");
    }

    public void oneDay() {
        System.out.println("Asking for weather next day");
    }

    public void twoDay() {
        System.out.println("Asking for weather two day");
    }

}
