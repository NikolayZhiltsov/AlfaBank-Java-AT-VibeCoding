package ru.alfabank.homeworks.homework13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SquadManager {

    //Задаём метод демонстрации
    public void demonstrateListCreations() {
        ArrayList<String> mainSquad = new ArrayList(List.of("Вадик", "Вася", "Толян", "Кузьмич"));
        List<String> supportSquad = Arrays.asList("Анатолич", "Павлик", "Лумумба");
        List<String> eliteSquad = List.of("Рэмбо", "Шварц");

        try {
            mainSquad.add("Петенька");
            System.out.println("Добавлено успешно. Текущий состав: " + mainSquad);
        } catch (Exception e) {
            System.out.println("Ошибка добавления: " + e.getClass().getSimpleName());
        }

        try {
            mainSquad.remove("Вася");
            System.out.println("Удалено успешно. Текущий состав: " + mainSquad);
        } catch (Exception e) {
            System.out.println("Ошибка удаления: " + e.getClass().getSimpleName());
        }

        try {
            supportSquad.add("Василич");
            System.out.println("Добавлено успешно. Текущий состав: " + supportSquad);
        } catch (Exception e) {
            System.out.println("Ошибка добавления: " + e.getClass().getSimpleName());
        }

        try {
            supportSquad.remove("Павлик");
            System.out.println("Удалено успешно. Текущий состав: " + supportSquad);
        } catch (Exception e) {
            System.out.println("Ошибка удаления: " + e.getClass().getSimpleName());
        }

        try {
            eliteSquad.add("Стэтхэм");
            System.out.println("Добавлено успешно. Текущий состав: " + eliteSquad);
        } catch (Exception e) {
            System.out.println("Ошибка добавления: " + e.getClass().getSimpleName());
        }

        try {
            eliteSquad.remove("Рэмбо");
            System.out.println("Удалено успешно. Текущий состав: " + eliteSquad);
        } catch (Exception e) {
            System.out.println("Ошибка удаления: " + e.getClass().getSimpleName());
        }
    }

    //Задаём метод фильтрации трýсов №1
    public void filterOutCowards1 (List<String> squad) {
        System.out.println(squad.toString());

    }
}
