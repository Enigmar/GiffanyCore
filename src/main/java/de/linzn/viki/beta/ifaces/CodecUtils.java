package de.linzn.viki.beta.ifaces;

import java.util.HashMap;
import java.util.Map;

public class CodecUtils {

    public static String mapToString(Map<String, String> map) {
        String codecString = "";

        for (String mapTest : map.keySet()) {
            String item = mapTest + "=" + map.get(mapTest);
            if (codecString == "") {
                codecString = item;
            } else {
                codecString += "#" + item;
            }
        }
        return codecString;
    }

    public static Map<String, String> stringToMap(String codecString) {
        if (codecString != null && codecString != "") {
            Map<String, String> map = new HashMap<>();
            for (String itemStack : codecString.split("#")) {
                String[] item = itemStack.split("=");
                map.put(item[0], item[1]);
            }
            return map;
        }
        return null;
    }
}
