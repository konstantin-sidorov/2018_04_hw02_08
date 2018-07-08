package ru.otus;

import com.google.gson.reflect.TypeToken;
import junit.framework.Assert;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        String filename = "C:\\rab\\MyObject.json";
        simpleArrayToJson(filename);
        objectArrayToJson(filename);
    }

    private static void simpleArrayToJson(String filename) {
        Integer[] obj = new Integer[10];
        for (int i = 0; i < obj.length; i++) {
            obj[i] = i;
        }
        List<Integer> list = Arrays.asList(obj);
        JsonHelper.simpleArrayToJson(list, filename, null, null);
        Type ListType = new TypeToken<Collection<Integer>>() {
        }.getType();
        List<Integer> list_other = (List<Integer>) JsonHelper.<Integer>simpleArrayFromJson(filename, ListType);
        Assert.assertEquals(true, list.equals(list_other));
    }

    private static void objectArrayToJson(String filename) throws IllegalAccessException {
        MyObject[] obj = new MyObject[5];
        for (int i = 0; i < obj.length; i++) {
            boolean flag = true;
            if (i % 2 == 0) flag = false;
            obj[i] = new MyObject(i, flag);
        }
        List<MyObject> list = Arrays.asList(obj);
        JsonHelper.objectArrayToJson(list, filename, null, null);
        Type ListType = new TypeToken<Collection<MyObject>>() {
        }.getType();
        List<MyObject> list_other = (List<MyObject>) JsonHelper.<MyObject>simpleArrayFromJson(filename, ListType);
        Assert.assertEquals(true, list.equals(list_other));
    }

}
