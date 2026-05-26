package ru.alfabank.homeworks.homework7.arena.app;

import ru.alfabank.homeworks.homework7.arena.heroes.Archer;
import ru.alfabank.homeworks.homework7.arena.heroes.Hero;
import ru.alfabank.homeworks.homework7.arena.heroes.Knight;
import ru.alfabank.homeworks.homework7.arena.heroes.Mage;

public class App {
    static void main() {


        // Создаём массив героев из разных типов героев

        Hero[] heroes = {
            new Hero("Второстепенный герой", 98, 55),
            new Knight("Рыцарь в кожаных доспехах", 45, 63, 25),
            new Mage("Совсем не боевой маг", 85, 100, 5),
            new Archer("Косой лучник", 25, 99, 12)
        };


        // Создаём цикл, с помощью которого выводим информацию и атаку для каждого героя из массива

        for (Hero hero : heroes) {
            hero.printInfo();
            hero.attack();
            System.out.println(); // резделитель для большей наглядности вывода
        }


        // Создаём статичный экземпляр рыцаря и выводим значения полей полученного экземпляра

        final Knight knight = new Knight("Рыцарь в золотых доспехах", 54, 81, 2);
        System.out.println(knight);
        System.out.println(); // разделитель


        // Меняем одно любое поле уже созданного статичного рыцаря и снова выводим все поля

        knight.setArmor(5);
        System.out.println(knight);
    }
}
