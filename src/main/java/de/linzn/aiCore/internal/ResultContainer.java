package de.linzn.aiCore.internal;

public class ResultContainer {
	public int objectID;
	public int keywordID;
	public String result;

	public ResultContainer(int objectID, int keywordID, String result) {
		this.objectID = objectID;
		this.keywordID = keywordID;
		this.result = result;
	}

}
