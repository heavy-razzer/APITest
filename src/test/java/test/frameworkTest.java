package test;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static managers.HttpManager.getResponse;
import static managers.JsonManager.compareJSons;

/*
Tests for 'compareJSons' function, that compares two JSONs for equality
 */
public class frameworkTest {

    @Test
    public void shouldReturnTrueWhenUseTwoIdenticalJSONS() {

        System.out.println("Should Return True When Use Two Identical JSONS");

        JSONObject js1 = new JSONObject();

        js1.put("first", "qwe");
        js1.put("second", "asd");
        js1.put("third", "zxc");

        Assert.assertTrue("FAILED: Two identical JSONS marked is not equal", compareJSons(js1, js1));
    }

    @Test
    public void shouldReturnFalseWhenUseTwoDifferentJSONS() {

        System.out.println("Should Return False When Use Two Different JSONS");

        JSONObject js1 = new JSONObject();
        JSONObject js2 = new JSONObject();

        js1.put("first", "qwe");
        js1.put("second", "asd");
        js1.put("third", "zxc");

        js2.put("first", "qwe");
        js2.put("second", "DIFFERENCE");
        js2.put("third", "zxc");

        Assert.assertFalse("FAILED: Two identical JSONS marked is not equal", compareJSons(js1, js2));
    }

    @Test
    public void shouldReturnTrueWhenUseJSONWithDifferentFieldsOrder() {

        System.out.println("Should Return True When Use JSON With Different Fields Order");

        JSONObject js1 = new JSONObject();
        JSONObject js2 = new JSONObject();

        js1.put("first", "qwe");
        js1.put("second", "asd");
        js1.put("third", "zxc");

        js2.put("second", "asd");
        js2.put("third", "zxc");
        js2.put("first", "qwe");

        Assert.assertTrue("FAILED: Check is affected by JSON fields order", compareJSons(js1, js2));
    }

    @Test
    public void shouldReturnTrueWhenUseJSONFromMapWithDifferentFieldsOrder() {

        System.out.println("Should Return True When Use JSON From Map With Different Fields Order");

        // HashMap is fastest unordered map
        Map<String, String> map1 = new HashMap<>();
        map1.put("name", "user1");
        map1.put("age", "20");
        map1.put("city", "Tallinn");
        JSONObject js1 = new JSONObject(map1);

        Map<String, String> map2 = new HashMap<>();
        map2.put("age", "20");
        map2.put("city", "Tallinn");
        map2.put("name", "user1");
        JSONObject js2 = new JSONObject(map2);

        Assert.assertTrue("FAILED: Check is affected by JSON fields order, created from unordered map", compareJSons(js1, js2));
    }

    @Test
    public void shouldReturnFalseWhenOneJSONIsNull() {

        System.out.println("Should Return False When One JSON Is Null");

        JSONObject js1 = new JSONObject();
        JSONObject js2 = null;

        js1.put("first", "qwe");
        js1.put("second", "asd");
        js1.put("third", "zxc");

        Assert.assertFalse("FAILED: Returned TRUE when one JSON is null", compareJSons(js1, js2));
    }

    @Test
    public void shouldReturnFalseWhenUseTwoNullJSONs() {

        JSONObject js1 = null;
        JSONObject js2 = null;

        Assert.assertFalse("FAILED: Returned TRUE when one both JSONs are null", compareJSons(js1, js2));
    }

    @Test
    public void shouldReturnFalseWhenOneJSONIsEmpty() {

        System.out.println("Should Return False When One JSON Is Empty");

        JSONObject js1 = new JSONObject("{}");
        JSONObject js2 = new JSONObject();

        js2.put("first", "qwe");
        js2.put("second", "asd");
        js2.put("third", "zxc");

        Assert.assertFalse("FAILED: Returned TRUE when one JSOn is empty", compareJSons(js1, js2));
    }

    @Test
    public void shouldReturnFalseWhenOneResponseIsNotJSON() {

        System.out.println("Should Return False When Response Is Not JSON");

        JSONObject js1 = new JSONObject();

        js1.put("first", "qwe");
        js1.put("second", "asd");
        js1.put("third", "zxc");

        JSONObject js2 = getResponse("https://www.yandex.ru");

        Assert.assertFalse("FAILED: Returned TRUE when one response returned not json", compareJSons(js1, js2));
    }

    @Test
    public void shouldReturnTrueWhenJSONsHasMultiLevelStructure() {

        System.out.println("Should Return True When JSONs Has Multi Level Structure");

        Map<Object, Object> map1 = new HashMap<>();
        Map<Object, Object> map2 = new HashMap<>();
        Map<Object, Object> map3 = new HashMap<>();

        map1.put("name", "user1");
        map1.put("age", "20");
        map1.put("city", "Tallinn");

        map2.put("name", "user1");
        map2.put("age", "20");
        map2.put("city", "Tallinn");

        map3.put("item1", map1);
        map3.put("item2", map2);

        JSONObject js1 = new JSONObject(map3);

        Assert.assertTrue("FAILED: Returned FALSE when one two multi-level JSONS are same ", compareJSons(js1, js1));
    }

    @Test
    public void shouldReturnFalseWhenOneJSONHasAdditionalItem() {

        System.out.println("Should Return False When One JSON Has Additional Item");

        JSONObject js1 = new JSONObject();
        JSONObject js2 = new JSONObject();

        js1.put("first", "qwe");
        js1.put("second", "asd");
        js1.put("third", "zxc");

        js2.put("first", "qwe");
        js2.put("second", "asd");
        js2.put("third", "zxc");
        js2.put("fourth", "rty");

        Assert.assertFalse("FAILED: Returned TRUE when one JSON has same fields as another, but with additional item", compareJSons(js1, js2));
    }

    @Test
    public void shouldReturnFailWhenJSONsHasSameItemsWithDifferentValues() {

        JSONObject js1 = new JSONObject();
        JSONObject js2 = new JSONObject();

        js1.put("first", "qwe");
        js1.put("second", "asd");
        js1.put("third", "zxc");

        js2.put("first", "111");
        js2.put("second", "222");
        js2.put("third", "333");

        Assert.assertFalse("FAILED: Returned TRUE when one JSON has same field names, but with different values", compareJSons(js1, js2));
    }

    @Test
    public void shouldReturnFailWhenJSONsHasDifferentItemsWithSameValues() {

        JSONObject js1 = new JSONObject();
        JSONObject js2 = new JSONObject();

        js1.put("first", "qwe");
        js1.put("second", "asd");
        js1.put("third", "zxc");

        js2.put("111", "qwe");
        js2.put("222", "asd");
        js2.put("333", "zxc");

        Assert.assertFalse("FAILED: Returned TRUE when one JSON has different item names, but with same values", compareJSons(js1, js2));
    }
}
