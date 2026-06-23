package ru.alfabank.homeworks.homework10;

public class Task {


    // Задаём переменные для хранения названия и состояния таски

    private final String title;
    private boolean isCompleted;


    // Задаём конструктор

    public Task(String title) {
        this.title = title;
        this.isCompleted = false; // По умолчанию задача не выполнена
    }


    // Задаём метод присвоения статуса Выполнено

    public void markAsCompleted() {
        this.isCompleted = true;
    }


    // Задаём геттеры для получения статуса выполнения и названия задачи

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getTitle() {
        return title;
    }


    // Задаём метод, выводящий в консоль задачу и статус выполнения

    public void printTaskInfo() {
        String statusIcon = isCompleted ? "[x]" : "[ ]";
        System.out.println(statusIcon + " " + title);
    }
}
