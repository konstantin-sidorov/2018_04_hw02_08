package ru.otus;

import com.google.gson.Gson;
import org.json.simple.*;

import javax.json.Json;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;

public class JsonHelper {
    private static String objectToJsonRR(ObjectJ obj, JSONAware jsonParent, String itemName) throws IllegalAccessException {
        JSONAware json = obj.getState().getJson(obj);
        obj.addChild(
                (f, g) -> {
                    ObjectJ obj2 = new ObjectJ(f);
                    try {
                        objectToJsonRR(obj2, json, g);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
        );
        return obj.getState().toJSONString(json, jsonParent, itemName, obj);
    }

//    private static <T> String objectToJsonR(T obj, JSONAware json, String itemName) throws IllegalAccessException {
//        Class clazz = obj.getClass();
//        if (obj instanceof java.lang.Long ||
//                obj instanceof java.lang.Integer ||
//                obj instanceof java.lang.Byte ||
//                obj instanceof java.lang.Boolean ||
//                obj instanceof java.lang.Character ||
//                obj instanceof java.lang.Short ||
//                obj instanceof java.lang.Float ||
//                obj instanceof java.lang.Double
//                ) {
//
//            if (json != null && itemName != null) {
//                ((JSONObject) json).put(itemName, obj);
//            } else if (json != null && itemName == null) {
//                ((JSONArray) json).add(obj);
//            } else return JSONValue.toJSONString(obj);
//            return json.toJSONString();
//        }
//        if (clazz.isArray()) {
//            JSONArray res = new JSONArray();
//            T[] objects = (T[]) obj;
//            for (int i = 0; i < objects.length; i++) {
//                objectToJsonR(objects[i], res, null);
//            }
//            if (json != null && itemName != null) {
//                ((JSONObject) json).put(itemName, res);
//            } else if (json != null && itemName == null) {
//                ((JSONArray) json).add(res);
//            } else return res.toJSONString();
//            return json.toJSONString();
//        } else if (obj instanceof Collection) {
//            JSONArray res = new JSONArray();
//            Collection<T> objects = (Collection<T>) obj;
//            for (T element : objects) {
//                objectToJsonR(element, res, null);
//            }
//            if (json != null && itemName != null) {
//                ((JSONObject) json).put(itemName, res);
//            } else if (json != null && itemName == null) {
//                ((JSONArray) json).add(res);
//            } else return res.toJSONString();
//            return json.toJSONString();
//        } else {
//            JSONObject res = new JSONObject();
//            Field[] fields = obj.getClass().getDeclaredFields();
//            for (int i = 0; i < fields.length; i++) {
//                fields[i].setAccessible(true);
//                objectToJsonR(fields[i].get(obj), res, fields[i].getName());
//            }
//            if (json != null && itemName != null) {
//                ((JSONObject) json).put(itemName, res);
//            } else if (json != null && itemName == null) {
//                ((JSONArray) json).add(res);
//            } else return res.toJSONString();
//            return json.toJSONString();
//        }
//    }

    public static String objectToJson(Object obj) throws IllegalAccessException {

        //return objectToJsonR(obj, null, null);
        ObjectJ obj2 = new ObjectJ(obj);
        return objectToJsonRR(obj2, null, null);
    }

    public static <T> Collection<T> simpleArrayFromJson(String json, Type typeT) {
        Gson gson = new Gson();
        Collection<T> collection = gson.fromJson(json, typeT);
        return collection;
    }

    //    public static Object getObject(String json,Class clazz){
//        TypeToken<?> typeToken=TypeToken.get(clazz);
//        /*Type ListType = new TypeToken<Collection<Integer>>() {
//        }.getType();*/
//
//        return getObjectT(json,typeToken.getType());
//    }
//    private static <T> T getObjectT(String json,Type type){
//        Gson gson = new Gson();
//        return gson.fromJson(json, type);
//    }
    public static String Example() {
        javax.json.JsonObject personObject = Json.createObjectBuilder()
                .add("name", "John")
                .add("age", 13)
                .add("isMarried", false)
                .add("address",
                        Json.createObjectBuilder().add("street", "Main Street")
                                .add("city", "New York")
                                .add("zipCode", "11111")
                                .build()
                )
                .add("phoneNumber",
                        Json.createArrayBuilder().add("00-000-0000")
                                .add("11-111-1111")
                                .add("11-111-1112")
                                .build()
                )
                .build();

        return personObject.toString();
    }
}