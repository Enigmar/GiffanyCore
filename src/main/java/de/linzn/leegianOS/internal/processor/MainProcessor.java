/*
 * Copyright (C) 2018. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 */

package de.linzn.leegianOS.internal.processor;

import de.linzn.leegianOS.LeegianOSApp;
import de.linzn.leegianOS.internal.databaseAccess.GetPrimarySkill;
import de.linzn.leegianOS.internal.databaseAccess.GetSecondarySkill;
import de.linzn.leegianOS.internal.interfaces.ISkill;
import de.linzn.leegianOS.internal.objectDatabase.clients.SkillClient;
import de.linzn.leegianOS.internal.objectDatabase.skillType.PrimarySkill;
import de.linzn.leegianOS.internal.objectDatabase.skillType.SecondarySkill;
import skills.DefaultSkill;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainProcessor {

    private String rawInput = null;
    private String[] formattedInput = null;
    private PrimarySkill primarySkill = null;
    private SecondarySkill secondarySkill = null;
    private SkillClient skillClient = null;
    private String prefix = this.getClass().getSimpleName() + "->";

    public MainProcessor(SkillClient skillClient, String rawInput) {
        LeegianOSApp.logger(prefix + "creating Instance ");
        this.skillClient = skillClient;
        this.rawInput = rawInput;
    }

    public boolean processing() {
        this.formattingInput();
        if (this.skillClient.isWaitingForResponse()) {
            //Some code
            this.skillClient.newClientResponse(this.formattedInput);
        } else {
            this.buildSkill();
        }
        return true;
    }

    private void formattingInput() {
        // Initial Symbols
        char symbols[] = {'!', '"', '§', '$', '%', '&', '/', '(', ')', '=', '?', '`', '²', '³', '{', '[', ']', '}', '*',
                '€', '@', '#', '-', '.', '_', ':', ',', ';', '<', '>', '|', 'µ', '^', '°', '~', '+', '¡', '¢', '£', '¤',
                '¥', '¦', '§', '¨', '©', 'ª', '«', '¬', '®', '¯', '°', '±', '²', '³', '´', 'µ', '¶', '·', '¸', '¹', 'º',
                '»', '¼', '½', '¾', '¿', 'À', 'Á', 'Â', 'Ã', 'Ä', 'Å', 'Æ', 'Ç', 'È', 'É', 'Ê', 'Ë', 'Ì', 'Í', 'Î', 'Ï',
                'Ð', 'Ñ', 'Ò', 'Ó', 'Ô', 'Õ', 'Ö', '×', 'Ø', 'Ù', 'Ú', 'Û', 'Ü', 'Ý', 'Þ', 'ß', 'à', 'á', 'â', 'ã', 'ä',
                'å', 'æ', 'ç', 'è', 'é', 'ê', 'ë', 'ì', 'í', 'î', 'ï', 'ð', 'ñ', 'ò', 'ó', 'ô', 'õ', 'ö', '÷', 'ø', 'ù',
                'ú', 'û', 'ü', 'ý', 'þ', 'ÿ', '€', '‚', 'ƒ', '„', '…', '†', '‡', 'ˆ', '‰', 'Š', '‹', 'Œ', 'Ž', '‘', '’',
                '“', '”', '•', '–', '—', '˜', '™', 'š', '›', 'œ', 'ž', 'Ÿ', '¡', '¢', '£', '¤', '¥', '¦', '§', '¨', '©',
                'ª', '«', '¬', '®', '¯', '°', '±', '²', '³', '´', 'µ', '¶', '·', '¸', 'º', '»', '¼', '½', '¾', '¿', 'À',
                'Á', 'Â', 'Ã', 'Ä', 'Å', 'Æ', 'Ç', 'È', 'É', 'Ê', 'Ë', 'Ì', 'Í', 'Î', 'Ï', 'Ð', 'Ñ', 'Ò', 'Ó', 'Ô', 'Õ',
                'Ö', '×', 'Ø', 'Ù', 'Ú', 'Û', 'Ü', 'Ý', 'Þ', 'ß', 'à', 'á', 'â', 'ã', 'ä', 'å', 'æ', 'ç', 'è', 'é', 'ê',
                'ë', 'ì', 'í', 'î', 'ï', 'ð', 'ñ', 'ò', 'ó', 'ô', 'õ', 'ö', '÷', 'ø', 'ù', 'ú', 'û', 'ü', 'ý', 'þ', 'ÿ',
                '€', '‚', 'ƒ', '„', '…', '†', '‡', 'ˆ', '‰', 'Š', '‹', 'Œ', 'Ž', '‘', '’', '“', '”', '•', '–', '—', '˜',
                '™', 'š', '›', 'œ', 'ž', 'Ÿ', '¡', '¢', '£', '¤', '¥', '¦', '§', '¨', '©', 'ª', '«', '¬', '®', '¯', '°',
                '±', '²', '³', '´', 'µ', '¶', '·', '¸', 'º', '»', '¼', '½', '¾', '¿', 'À', 'Á', 'Â', 'Ã', 'Ä', 'Å', 'Æ',
                'Ç', 'È', 'É', 'Ê', 'Ë', 'Ì', 'Í', 'Î', 'Ï', 'Ð', 'Ñ', 'Ò', 'Ó', 'Ô', 'Õ', 'Ö', '×', 'Ø', 'Ù', 'Ú', 'Û',
                'Ü', 'Ý', 'Þ', 'ß', 'à', 'á', 'â', 'ã', 'ä', 'å', 'æ', 'ç', 'è', 'é', 'ê', 'ë', 'ì', 'í', 'î', 'ï', 'ð',
                'ñ', 'ò', 'ó', 'ô', 'õ', 'ö', '÷', 'ø', 'ù', 'ú', 'û', 'ü', 'ý', 'þ', 'ÿ', '€', '‚', 'ƒ', '„', '…', '†',
                '‡', 'ˆ', '‰', 'Š', '‹', 'Œ', 'Ž', '‘', '’', '“', '”', '•', '–', '—', '˜', '™', 'š', '›', 'œ', 'ž', 'Ÿ',
                '¡', '¡', '¡', '¡', '¥', '¦', '§', '¨', '©', 'ª', '«', '¬', '®', '¯', '°', '±', '²', '³', '´', 'µ', '¶',
                '·', '¸', 'º', '»', '¼', '½', '¾', '¿', 'À', 'Á', 'Â', 'Ã', 'Ä', 'Å', 'Æ', 'Ç', 'È', 'É', 'Ê', 'Ë', 'Ì',
                'Í', 'Î', 'Ï', 'Ð', 'Ñ', 'Ò', 'Ó', 'Ô', 'Õ', 'Ö', '×', 'Ø', 'Ù', 'Ú', 'Û', 'Ü', 'Ý', 'Þ', 'ß', 'à', 'á',
                'â', 'ã', 'ä', 'å', 'æ', 'ç', 'è', 'é', 'ê', 'ë', 'ì', 'í', 'î', 'ï', 'ð', 'ñ', 'ò', 'ó', 'ô', 'õ', 'ö',
                '÷', 'ø', 'ù', 'ú', 'û', 'ü', 'ý', 'þ', 'ÿ', '^'};

        // First clean up the string
        LeegianOSApp.logger(prefix + "formattingInput-->" + "cleanup symbols");
        for (char c : symbols) {
            this.rawInput = this.rawInput.replace(String.valueOf(c), "");
        }
        if (this.rawInput.length() > 0) {
            LeegianOSApp.logger(prefix + "formattingInput-->" + "cleanup single spacer");
            // For special case
            if (this.rawInput.toCharArray()[0] == ' ') {
                this.rawInput = this.rawInput.replaceFirst(" ", "");
            }

            LeegianOSApp.logger(prefix + "formattingInput-->" + "cleanup double spacer");
            // Replace in case than more than one spacer
            this.rawInput = this.rawInput.replaceAll("[ ]{2,}", " ");
            this.rawInput = this.rawInput.toLowerCase();

        }
        LeegianOSApp.logger(prefix + "formattingInput-->" + "split to array");
        // Split the string in substrings
        this.formattedInput = this.rawInput.split(" ");
        LeegianOSApp.logger(prefix + "formattingInput-->" + "end of method");

    }


    private boolean buildSkill() {
        this.primarySkill = new GetPrimarySkill(this.formattedInput).getSkill();
        if (this.primarySkill != null) {
            LeegianOSApp.logger(prefix + "buildSkill-->" + "Success search primarySkill");
            if (!this.primarySkill.standalone) {
                this.secondarySkill = new GetSecondarySkill(this.primarySkill).getSkill();
                if (this.secondarySkill != null) {
                    // Code for full support with sub and parent skill
                    LeegianOSApp.logger(prefix + "buildSkill-->" + "Success search secondarySkill");
                    return this.executeJavaClassFunction();
                } else {
                    // Exit, because no subskill for this exist.
                    LeegianOSApp.logger(prefix + "buildSkill-->" + "Failed search secondarySkill");
                    return false;
                }
            } else {
                LeegianOSApp.logger(prefix + "buildSkill-->" + "primarySkill standalone");
                // Start, if parent skill ist standalone
                return this.executeJavaClassFunction();
            }
        } else {
            // If no parent skill exist!
            LeegianOSApp.logger(prefix + "buildSkill-->" + "Failed search primarySkill");
            return false;
        }
    }

    private boolean executeJavaClassFunction() {
        String class_name;
        String method_name;
        if (secondarySkill == null) {
            class_name = this.primarySkill.java_class;
            method_name = this.primarySkill.java_method;
        } else {
            class_name = this.secondarySkill.java_class;
            method_name = this.secondarySkill.java_method;
        }

        if (class_name == null || method_name == null) {
            LeegianOSApp.logger(prefix + "executeJavaClassFunction-->" + "Failed " + class_name + "<->" + method_name);
            return false;
        } else {
            LeegianOSApp.logger(prefix + "executeJavaClassFunction-->" + "Success " + class_name + "<->" + method_name);
        }

        try {
            Class<ISkill> act = LeegianOSApp.leegianOSAppInstance.skillProcessor.skillList.get(class_name);
            ISkill selectedSkillTemplate = act.newInstance();
            selectedSkillTemplate.setEnv(this.skillClient, this.primarySkill, this.secondarySkill);
            //Search method in this class
            Method method = selectedSkillTemplate.getClass().getMethod(method_name);
            //Run method in this class
            method.invoke(selectedSkillTemplate);

        } catch (InstantiationException | NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            ISkill defaultTemp = new DefaultSkill();
            defaultTemp.setEnv(this.skillClient, null, null);
            return false;
        }
        return true;
    }
}
