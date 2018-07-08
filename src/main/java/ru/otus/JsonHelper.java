package ru.otus;


import com.google.gson.Gson;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;


public class JsonHelper {

    public static void saveFile(JSONAware job, String name) {
        try (FileWriter file = new FileWriter(name)) {

            file.write(job.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String filename) {
        String res = "";

        try (BufferedReader file = new BufferedReader(new FileReader(filename))) {
            StringBuilder sb = new StringBuilder();
            String line = file.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = file.readLine();
            }
            res = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static <T> void simpleArrayToJson(Collection<T> collection, String filename, JSONObject jsonObject, String name) {
        JSONArray list = new JSONArray();
        for (T element : collection) {
            list.add(element);
        }
        if (name == null) {
            saveFile(list, filename);
        } else {
            jsonObject.put(name, list);
        }
    }

    public static <T> void objectArrayToJson(Collection<T> collection, String filename, JSONObject jsonObject, String name) throws IllegalAccessException {
        JSONArray list = new JSONArray();
        for (T element : collection) {
            JSONObject objJSON = objectToJson(element);
            list.add(objJSON);
        }
        if (name == null) {
            saveFile(list, filename);
        } else {
            jsonObject.put(name, list);
        }
    }

    private static <T> JSONObject objectToJson(T obj) throws IllegalAccessException {
        JSONObject res = new JSONObject();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            res.put(fields[i].getName(), fields[i].get(obj));
        }
        return res;
    }

    public static <T> Collection<T> simpleArrayFromJson(String filename, Type typeT) {
        String json = readFile(filename);
        Gson gson = new Gson();
        //Collection<T> collection= gson.fromJson(json, new TypeToken<Collection<T>>(){}.getType());
        Collection<T> collection = gson.fromJson(json, typeT);
        return collection;

    }

}
