package ru.alfabank.homeworks.homework10;

public class Main {

    public static void main(String[] args) {
        TaskTracker tracker = new TaskTracker();


        // Добавляем новые задачи в список

        System.out.println("=== Добавление задач ===");
        tracker.addTask("Проверить авторизацию");
        tracker.addTask("Проверить регистрацию");
        tracker.addTask("Написать автотесты");
        System.out.println();


        // Выводим все таски (все должны быть открыты)

        System.out.println("=== Вывод всех задач ===");
        tracker.printAllTasks();
        System.out.println();


        // Ищем существующие и несуществующие задачи

        System.out.println("=== Поиск задач ===");
        tracker.findTaskByTitle("Проверить авторизацию");
        tracker.findTaskByTitle("Купить молоко");
        System.out.println();


        // Помечаем таску сделанной

        System.out.println("=== Выполнение задачи ===");
        tracker.completeTask("Проверить регистрацию");
        System.out.println();


        // Повторно выводим все таски (одна должна стать выполненной)

        System.out.println("=== Вывод задач после изменений ===");
        tracker.printAllTasks();
        System.out.println();


        // Выводим итоговую статистику

        System.out.println("=== Вывод статистики ===");
        tracker.printStatistics();
    }
}
