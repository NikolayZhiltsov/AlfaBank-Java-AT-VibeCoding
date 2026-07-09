package ru.alfabank.homeworks.homework13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    static void main() {

        //Создаём аррайлист чужих и заполняем его данными о пяти пришельцах, включая один дубликат
        ArrayList<Alien> aliens = new ArrayList<>();

        aliens.add(new Alien("Мандалорец", "Мандалор", 5));
        aliens.add(new Alien("Чубакка", "Чубакостан", 10));
        aliens.add(new Alien("Си3ПиО", "Имперский Роботостроительный Планетарный Завод", 1));
        aliens.add(new Alien("Р2Д2", "Альдеран", 1));
        aliens.add(new Alien("Чубакка", "Чубакостан", 9));

        //Выводим исходный список
        System.out.println(aliens.toString());
        System.out.println();

        //Проверяем список на наличие дубликатов
        boolean hasDuplicates = false;
        HashSet<Alien> uniqueAliens = new HashSet<>();

        for (Alien alien : aliens) {
            if (!uniqueAliens.add(alien)) {
                hasDuplicates = true;
                System.out.println("Обнаружен дубликат: " + alien.getName() + " с планеты " + alien.getPlanet());
                System.out.println();
                break;
            }
        }

        //Выводим итоговый результат
        System.out.println("Содержит ли список дубликаты? Ответ: " + (hasDuplicates ? "да" : "нет"));
        System.out.println();

        //Создаём экземпляр Формирования отрядов
        SquadManager squadManager = new SquadManager();

        //Вызываем метод демонстрации списков
        squadManager.demonstrateListCreations();
        System.out.println();

        //Создаём аррайлист трусов и обычных бойцов для первого фильтра трусов
        ArrayList<String> cowards1 = new ArrayList<>(List.of("Трус Вася", "Петя", "Игорёк", "Трус Эдик", "Натаныч"));

        //Передаём этот лист в фильтр трусов №1
        squadManager.filterOutCowards1(cowards1);
        System.out.println();

        //Создаём аррайлист трусов и обычных бойцов для второго фильтра трусов
        ArrayList<String> cowards2 = new ArrayList<>(List.of("Сашка", "Трус Васильич", "Толян", "Эрик", "Трус Вадян"));

        //Передаём этот лист в фильтр трусов №2
        squadManager.filterOutCowards2(cowards2);
        System.out.println();

        //Создаём объект очереди бойцов
        AssaultQueue assaultQueue = new AssaultQueue();

        //Добавляем в очередь пять человек
        assaultQueue.addRecruit("Олег");
        assaultQueue.addRecruit("Алексеич");
        assaultQueue.addRecruit("Ванёк");
        assaultQueue.addRecruit("Наташка");
        assaultQueue.addRecruit("Джонни Мнемоник");

        //Выводим текущее состояние очереди
        assaultQueue.ptintQueue();
        System.out.println();

        //Имитируем уход двух человек из начала очереди с выводом их имён
        System.out.println(assaultQueue.retreatRecruit());
        System.out.println(assaultQueue.retreatRecruit());
        System.out.println();

        //Выводим текущее состояние очереди
        assaultQueue.ptintQueue();
        System.out.println();

        //Имитируем добавление трух человек в конец очереди
        assaultQueue.addRecruit("Костян");
        assaultQueue.addRecruit("Артемон");
        assaultQueue.addRecruit("Ильич");

        //Выводим итоговое состояние очереди
        assaultQueue.ptintQueue();
        System.out.println();

        //Создаём первый объект отчёта и выводим его на экран
        List<Alien> capturedAliens = new  ArrayList<>();
        capturedAliens.addAll(aliens);
        MissionReport missionReport1 = new MissionReport("Штурм зоны 51",capturedAliens ,25);
        System.out.println(missionReport1);
        System.out.println();

        //Создаём второй объект отчёта и выводим его на экран
        MissionReport missionReport2 = new MissionReport("Штурм зоны 51",capturedAliens ,25);
        System.out.println(missionReport2);
        System.out.println();

        //Сравниваем два объекта отчёта друг с другом двумя разными способами и выводим результат
        System.out.println("Объекты отчётов идентичны? " + (missionReport1 == missionReport2 ? "Да" : "Нет"));
        System.out.println("Объекты отчётов идентичны? " + (missionReport1.equals(missionReport2) ? "Да" : "Нет"));
    }
}
