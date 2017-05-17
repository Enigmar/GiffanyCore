package de.linzn.aiCore.processing.readInMain;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.container.KeywordContainer;
import de.linzn.aiCore.internal.container.ObjectContainer;
import de.linzn.aiCore.internal.container.ResultContainer;

public class InsertProcessing {
    private App app;

    public InsertProcessing(App app) {
        this.app = app;
    }

    public void processingInsertText(String textSentence, String textResult) {
        System.out.println(textSentence + " - " + textResult);
        ResultContainer resultCon = this.app.mysqlData.dbinsert.insertResultText(textSentence.toLowerCase(),
                textResult.toLowerCase());
        System.out.println(textSentence.toLowerCase() + " - " + resultCon.result);

    }

    public void processingInsertObject(String objectName, String objectClass, String keywordName,
                                       String keywordFunction, String objectResult) {
        System.out.println(objectName + " - " + objectClass + " - " + keywordName + " - " + keywordFunction + " - "
                + objectResult);

        ObjectContainer objectCon = this.app.mysqlData.dbinsert.insertObject(objectName.toLowerCase(),
                objectClass.toLowerCase());

        KeywordContainer keywordCon = this.app.mysqlData.dbinsert.insertKeyword(objectCon, keywordName.toLowerCase(),
                keywordFunction.toLowerCase());

        ResultContainer resultCon = this.app.mysqlData.dbinsert.insertResultObject(objectCon, keywordCon,
                objectResult.toLowerCase());

        System.out.println(objectCon.objectname + " - " + objectCon.classname + " - " + keywordCon.keywordname + " - "
                + keywordCon.function + " - " + resultCon.result);
    }

}
