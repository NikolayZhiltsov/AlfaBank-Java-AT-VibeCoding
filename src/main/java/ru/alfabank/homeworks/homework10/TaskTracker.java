package ru.alfabank.homeworks.homework10;

import java.util.ArrayList;

public class TaskTracker {


    // Задаём аррайлист для хранения списка тасок

    private final ArrayList<Task> tasks;


    // Задаём конструктор

    public TaskTracker() {
        this.tasks = new ArrayList<>();
    }


    // Задаём метод добавления таски в список

    public void addTask(String title) {
        tasks.add(new Task(title));
        System.out.println("Задача \"" + title + "\" успешно добавлена.");
    }



}
