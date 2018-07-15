package ru.otus;

@FunctionalInterface
public interface ResultHandler {
    void handle(Object obj,String name);
}
