package de.linzn.viki.internal.ifaces;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CodecUtils {

    /* Convert a HashMap to a string for saving in a mysql file */
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

    /* Convert a String back to a HashMap from a mysql database */
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


    public static InputStream byteToInputstream(byte[] bytes) {
        return new BufferedInputStream(new ByteArrayInputStream(bytes));

    }

    public static byte[] inputstreamToBytes(InputStream input) throws IOException {
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        return output.toByteArray();
    }

}
