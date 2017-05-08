package de.linzn.aiCore.internal;

public class ObjectContainer {
	public int objectID;
	public String objectname;
	public String classname;

	public ObjectContainer(int objectID, String call, String classname) {
		this.objectID = objectID;
		this.objectname = call;
		this.classname = classname;
	}

}
