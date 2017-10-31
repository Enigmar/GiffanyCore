package de.linzn.leegianOS.internal.processor;

import de.linzn.leegianOS.App;
import de.linzn.leegianOS.internal.data.GetParentSkill;
import de.linzn.leegianOS.internal.data.GetSubSkill;
import de.linzn.leegianOS.internal.ifaces.ISkillTemplate;
import de.linzn.leegianOS.internal.ifaces.ParentSkill;
import de.linzn.leegianOS.internal.ifaces.SkillClient;
import de.linzn.leegianOS.internal.ifaces.SubSkill;
import skills.DefaultTemplate;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class SkillProcessor {

    private String rawInput = null;
    private String[] formattedInput = null;
    private ParentSkill parentSkill = null;
    private SubSkill subSkill = null;
    private SkillClient skillClient = null;
    private String prefix = this.getClass().getSimpleName() + "->";

    public SkillProcessor(SkillClient skillClient, String rawInput) {
        App.logger(prefix + "creating Instance ");
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
        App.logger(prefix + "formattingInput-->" + "cleanup symbols");
        for (char c : symbols) {
            this.rawInput = this.rawInput.replace(String.valueOf(c), "");
        }
        if (this.rawInput.length() > 0) {
            App.logger(prefix + "formattingInput-->" + "cleanup single spacer");
            // For special case
            if (this.rawInput.toCharArray()[0] == ' ') {
                this.rawInput = this.rawInput.replaceFirst(" ", "");
            }

            App.logger(prefix + "formattingInput-->" + "cleanup double spacer");
            // Replace in case than more than one spacer
            this.rawInput = this.rawInput.replaceAll("[ ]{2,}", " ");
            this.rawInput = this.rawInput.toLowerCase();

        }
        App.logger(prefix + "formattingInput-->" + "split to array");
        // Split the string in substrings
        this.formattedInput = this.rawInput.split(" ");
        App.logger(prefix + "formattingInput-->" + "end of method");

    }


    private boolean buildSkill() {
        this.parentSkill = new GetParentSkill(this.formattedInput).getSkill();
        if (this.parentSkill != null) {
            App.logger(prefix + "buildSkill-->" + "Success search parentSkill");
            if (!this.parentSkill.standalone) {
                this.subSkill = new GetSubSkill(this.parentSkill).getSkill();
                if (this.subSkill != null) {
                    // Code for full support with sub and parent skill
                    App.logger(prefix + "buildSkill-->" + "Success search subSkill");
                    return this.executeJavaClassFunction();
                } else {
                    // Exit, because no subskill for this exist.
                    App.logger(prefix + "buildSkill-->" + "Failed search subSkill");
                    return false;
                }
            } else {
                App.logger(prefix + "buildSkill-->" + "parentSkill standalone");
                // Start, if parent skill ist standalone
                return this.executeJavaClassFunction();
            }
        } else {
            // If no parent skill exist!
            App.logger(prefix + "buildSkill-->" + "Failed search parentSkill");
            return false;
        }
    }

    private boolean executeJavaClassFunction() {
        String class_name;
        String method_name;
        if (subSkill == null) {
            class_name = this.parentSkill.java_class;
            method_name = this.parentSkill.java_method;
        } else {
            class_name = this.subSkill.java_class;
            method_name = this.subSkill.java_method;
        }

        if (class_name == null || method_name == null) {
            App.logger(prefix + "executeJavaClassFunction-->" + "Failed " + class_name + "<->" + method_name);
            return false;
        } else {
            App.logger(prefix + "executeJavaClassFunction-->" + "Success " + class_name + "<->" + method_name);
        }

        try {
            ClassLoader cl = new URLClassLoader(new URL[]{new File("").toURI().toURL()});

            Class<ISkillTemplate> act = (Class<ISkillTemplate>) cl.loadClass("skills." + Character.toUpperCase(class_name.charAt(0)) + class_name.substring(1));

            ISkillTemplate selectedSkillTemplate = act.newInstance();
            selectedSkillTemplate.setEnv(this.skillClient, this.parentSkill, this.subSkill);

            //Search method in this class
            Method method = selectedSkillTemplate.getClass().getMethod(method_name);
            //Run method in this class
            method.invoke(selectedSkillTemplate);

        } catch (ClassNotFoundException | InstantiationException | NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException | IllegalArgumentException | MalformedURLException e) {
            System.out.println(e.getMessage());
            ISkillTemplate defaultTemp = new DefaultTemplate();
            defaultTemp.setEnv(this.skillClient, null, null);
            return false;
        }
        return true;
    }
}
