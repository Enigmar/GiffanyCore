package de.linzn.aiCore.internal.container;

public class ResultContainer {
    public int objectID;
    public int keywordID;
    public String result;

    public int sentenceID;

    public ResultContainer(int objectID, int keywordID, String result) {
        this.objectID = objectID;
        this.keywordID = keywordID;
        this.result = result;
    }

    public ResultContainer(int sentenceID, String result) {
        this.sentenceID = sentenceID;
        this.result = result;
    }

}
