package de.linzn.aiCore.internal;

import de.linzn.aiCore.internal.container.ClientContainer;
import de.linzn.aiCore.internal.container.KeywordContainer;
import de.linzn.aiCore.internal.container.ObjectContainer;

public interface IExecutedSkill {

    public void initial(ClientContainer clientCon, ObjectContainer objectCon, KeywordContainer keywordCon);

    public void executeSkill(String function);

    public void executeResult();

}
