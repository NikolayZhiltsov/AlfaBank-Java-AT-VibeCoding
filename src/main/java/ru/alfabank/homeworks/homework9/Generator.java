package ru.alfabank.homeworks.homework9;

import com.github.lalyos.jfiglet.FigletFont;
import net.datafaker.Faker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Generator {
    static void main() throws IOException {


        // Создаём массив с четырьмя фамилиями, заполняем его данными и генерируем первую фамилию с помощью метода getLastName

        ArrayList<String> lastNames = new ArrayList<>();
        lastNames.addAll(Arrays.asList("Dolcenko", "Gabannoff", "Versaceyev", "Balenciagin"));
        String lastName1 = getLastName(lastNames);


        // Вызываем dataFaker и генерируем вторую часть логотипа

        Faker faker = new Faker();
        String lastName2 = faker.name().lastName();


        // Генерируем строки для jfiglet и выводим их в консоль

        System.out.println(FigletFont.convertOneLine(lastName1));
        System.out.println(FigletFont.convertOneLine("&"));
        System.out.println(FigletFont.convertOneLine(lastName2));


        // Генерируем с помощью dataFaker данные для юр.лица и выводим в консоль результат

        String ulFirstName = faker.name().firstName();
        String ulLastName = faker.name().lastName();
        String ulAddress = faker.address().fullAddress();
        String ulPhone = faker.phoneNumber().phoneNumber();

        System.out.println(ulAddress);
        System.out.println(ulPhone);
        System.out.println(ulFirstName + " " + ulLastName);
    }


    // Задаём метод, который будет возвращать любую случайную фамилию из lastName

    private static String getLastName(ArrayList<String> lastNames) {
        if (lastNames == null || lastNames.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return lastNames.get(random.nextInt(lastNames.size()));
    }
}
