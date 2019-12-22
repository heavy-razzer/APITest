package managers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/*
Class with all functions to work with JSON from http response: converting to Map and comparison
 */
public class JsonManager {

    public static boolean compareJSons(JSONObject js1, JSONObject js2) {

        if (js1 != null && js2 != null) {

            // Quick compare jsons strings as is
            if (js1.toString().equals(js2.toString())) return true;

            // Build map from json
            Map jsMap1;
            Map jsMap2;
            try {
                jsMap1 = jsonToMap(js1);
                jsMap2 = jsonToMap(js2);
            } catch (JSONException e) {
                System.out.println("JSON Exception");
                return false;
            }

            if (jsMap1.isEmpty() || jsMap2.isEmpty()) return false;

            // If root items count in maps is not the same, break: jsons are different, no need to proceed
            if (jsMap1.entrySet().toArray().length != jsMap2.entrySet().toArray().length) return false;

            return jsMap1.equals(jsMap2);
        } else
            // I assume, that two nulls are not the same responses
            return false;
    }

    /*
    TreeMap is used to have sorted map. It works slower than HashMap.
    But to compare maps properly maps must be sorted (has same fields order)
     */

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
