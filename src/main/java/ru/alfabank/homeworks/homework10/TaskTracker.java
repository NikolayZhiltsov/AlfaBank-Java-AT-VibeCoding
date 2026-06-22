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


    // Задаём метод вывода всех тасок

    public void printAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст.");
            return;
        }
        System.out.println("--- Список всех задач ---");
        for (Task task : tasks) {
            task.printTaskInfo();
        }
    }

}
