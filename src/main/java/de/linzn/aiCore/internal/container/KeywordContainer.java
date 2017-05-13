package de.linzn.aiCore.internal.container;

public class KeywordContainer {
    public int keywordID;
    public int objectID;
    public String keywordname;
    public String function;

    public KeywordContainer(int keywordID, int objectID, String keyword, String function) {
        this.keywordID = keywordID;
        this.objectID = objectID;
        this.keywordname = keyword;
        this.function = function;
    }

}
