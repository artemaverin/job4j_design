package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.xml.Factory;
import ru.job4j.serialization.xml.Product;

import java.util.ArrayList;
import java.util.List;

public class JsonPojo {
    public static void main(String[] args) {
        /* JSONObject из json-строки строки */
        JSONObject jsonProduct = new JSONObject("{\"product\":\"washbasins\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("mechanical unit");
        list.add("quality control");
        JSONArray jsonUnits = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Factory factory = new Factory(true,
                450,
                "OOO Keramika",
                new Product("kitchen sinks"), new String[]{"drying", "painting", "moulding"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSubsidized", factory.isSubsidized());
        jsonObject.put("personalQuantity", factory.getPersonalQuantity());
        jsonObject.put("name", factory.getName());
        jsonObject.put("product", jsonProduct);
        jsonObject.put("units", jsonUnits);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(factory).toString());
    }
}
