package managers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class JsonManager {

    public static boolean compareJSons(JSONObject js1, JSONObject js2) {

        boolean result = false;

        // Compare jsons string as is
        if (js1.toString().equals(js2.toString()))
            return true;

        // Build map from json
        Map jsMap1 = jsonToMap(js1);
        Map jsMap2 = jsonToMap(js2);

        // If root items in maps is not the same, break: jsons are different for sure
        if (jsMap1.entrySet().toArray().length != jsMap2.entrySet().toArray().length) return false;

        Object[] js1Array = jsMap1.entrySet().toArray();
        Object[] js2Array = jsMap2.entrySet().toArray();

        System.out.println("---");
        for (Object arrayItem : js1Array) {
            System.out.println(arrayItem.toString());
        }

        System.out.println("---");
        for (Object arrayItem : js2Array) {
            System.out.println(arrayItem.toString());
        }

        return result;
    }

    private static Map<Object, Object> jsonToMap(JSONObject json) throws JSONException {

        Map<Object, Object> retMap = new TreeMap<>();
        if (json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    private static Map<Object, Object> toMap(JSONObject object) throws JSONException {

        Map<Object, Object> map = new TreeMap<>();
        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    private static List<Object> toList(JSONArray array) throws JSONException {

        List<Object> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
}
