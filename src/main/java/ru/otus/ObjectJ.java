package ru.otus;

import java.lang.reflect.Field;
import java.util.Collection;


public class ObjectJ<T> {
    private ObjectType state;
    private T obj;

    public ObjectJ(T obj) {
        this.obj = obj;
        setState();
    }

    public T getObj() {
        return obj;
    }

    public ObjectType getState() {
        return state;
    }

    private void setState() {
        Class clazz = obj.getClass();
        String name = clazz.toString();
        if (obj instanceof java.lang.Long ||
                obj instanceof java.lang.Integer ||
                obj instanceof java.lang.Byte ||
                obj instanceof java.lang.Boolean ||
                obj instanceof java.lang.Character ||
                obj instanceof java.lang.Short ||
                obj instanceof java.lang.Float ||
                obj instanceof java.lang.Double ||
                name.equals("long") ||
                name.equals("int") ||
                name.equals("byte") ||
                name.equals("boolean") ||
                name.equals("char") ||
                name.equals("short") ||
                name.equals("float") ||
                name.equals("double")
                ) {
            this.state = ObjectType.PRIMITIVE;
        } else if (clazz.isArray())
        {
            this.state = ObjectType.ARRAY;
        } else if (obj instanceof Collection){
            this.state = ObjectType.COLLECTION;
        } else {
            this.state = ObjectType.OBJECT;
        }
    }

    public void addChild(ResultHandler c) throws IllegalAccessException {
        if (state == ObjectType.ARRAY) {
            T[] objects = (T[]) obj;
            for (int i = 0; i < objects.length; i++)
                c.handle(objects[i], null);//objectToJsonR(objects[i], res, null);
        } else if (state == ObjectType.COLLECTION) {
            Collection<T> objects = (Collection<T>) obj;
            for (T element : objects) {
                c.handle(element, null);//objectToJsonR(element, res, null);
            }
        } else if (state == ObjectType.OBJECT) {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                c.handle(fields[i].get(obj), fields[i].getName());//objectToJsonR(fields[i].get(obj), res, fields[i].getName());
            }
        }
    }
}
