package de.linzn.aiCore.internal;

import de.linzn.aiCore.internal.container.ClientContainer;
import de.linzn.aiCore.internal.container.KeywordContainer;
import de.linzn.aiCore.internal.container.ObjectContainer;

import java.lang.reflect.InvocationTargetException;

public class Reflector {

    public void functionRunner(IExecutedSkill classObject, String function) {
        java.lang.reflect.Method method;
        try {
            method = classObject.getClass().getMethod(function);
        } catch (SecurityException e) {
            e.printStackTrace();
            return;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return;
        }

        try {
            method.invoke(classObject);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public void classRunner(ClientContainer clientCon, ObjectContainer objectCon, KeywordContainer keywordCon) {
        Class<IExecutedSkill> act;
        try {
            act = (Class<IExecutedSkill>) Class.forName("de.linzn.aiCore.internal.skills."
                    + Character.toUpperCase(objectCon.classname.charAt(0)) + objectCon.classname.substring(1));
            IExecutedSkill objectclass = act.newInstance();
            objectclass.initial(clientCon, objectCon, keywordCon);
            objectclass.executeSkill(keywordCon.function);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
