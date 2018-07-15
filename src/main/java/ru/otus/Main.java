package ru.otus;

import com.google.gson.reflect.TypeToken;
import junit.framework.Assert;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        simpleArrayToJson();
        objectArrayToJson();
    }

    private static void simpleArrayToJson() throws IllegalAccessException {
        Integer[] obj = new Integer[10];
        for (int i = 0; i < obj.length; i++) {
            obj[i] = i;
        }
        List list = Arrays.asList(obj);
        //JsonHelperOld.simpleArrayToJson(list, filename, null, null);
        String res = JsonHelper.objectToJson(list);
        System.out.println(res);
        Type ListType = new TypeToken<Collection<Integer>>() {
        }.getType();
        List<Integer> list_other = (List<Integer>) JsonHelper.<Integer>simpleArrayFromJson(res, ListType);
        Assert.assertEquals(true, list.equals(list_other));
    }

    private static void objectArrayToJson() throws IllegalAccessException {
        MyObject[] obj = new MyObject[5];
        for (int i = 0; i < obj.length; i++) {
            boolean flag = true;
            if (i % 2 == 0) flag = false;
            obj[i] = new MyObject(i, flag);
        }
        List<MyObject> list = Arrays.asList(obj);
        //JsonHelper.objectArrayToJson(list, filename, null, null);
        String res = JsonHelper.objectToJson(list);
        System.out.println(res);
        Type ListType = new TypeToken<Collection<MyObject>>() {
        }.getType();
        List<MyObject> list_other = (List<MyObject>) JsonHelper.<MyObject>simpleArrayFromJson(res, ListType);
        Assert.assertEquals(true, list.equals(list_other));
    }
}