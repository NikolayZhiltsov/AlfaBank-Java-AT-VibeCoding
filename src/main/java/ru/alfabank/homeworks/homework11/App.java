package ru.alfabank.homeworks.homework11;

import net.datafaker.providers.food.Coffee;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    static void main(String[] args) {

        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Проверка кофе-машины начата.");

        //Просим пользователя вручную ввести количество воды и ловим ошибочный ввод текста
        System.out.println("Введите количество воды:");
        try {
            int volume = scanner.nextInt();
            coffeeMachine.makeCoffee(volume);
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: Нужно было ввести число");
        } finally {
            System.out.println("Проверка кофе-машины завершена.");
        }

        //Ловим своё исключение о недостаточном объёме воды
        try {
            coffeeMachine.makeCoffee(100);
        } catch (NotEnoughWaterException e) {
            System.out.println(e.getMessage());
        }

        //Ловим деление на ноль
        try {
            coffeeMachine.calculateCups(1000, 0);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: Размер чашки не может быть 0");
        }

        //Ловим null в тексте
        String coffeeName = null;
        try {
            coffeeMachine.printCoffeeName(coffeeName);
        } catch (NullPointerException e) {
            System.out.println("Ошибка: Название кофе отсутствует");
        }
    }
}
