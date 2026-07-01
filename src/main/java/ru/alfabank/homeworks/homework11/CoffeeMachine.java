package ru.alfabank.homeworks.homework11;

public class CoffeeMachine {

    //Задаём метод приготовления кофе
    public void makeCoffee(int volumeOfWater) {
        if (volumeOfWater <= 200) {
            throw new NotEnoughWaterException("Ошибка: Такого количества воды недостаточно!");
        }
        System.out.println("Кофе приготовлен.");
    }

    //Задаём метод, считающий количество чашек
    public int calculateCups (int volumeOfWater, int volumeOfCup) {
        return volumeOfWater / volumeOfCup;
    }

    //Задаём метод вывода названия кофе большими буквами
    public void printCoffeeName(String coffeeName) {
        System.out.println(coffeeName.toUpperCase());
    }
}
