import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class BasketsAndPasswords {
    public static void main(String[] args) {

        System.out.println("Задание №1. Корзины и их сравнение");

        //Создаём массивы переменных с наполнением корзин для Пети, Коли и Терентия.
        String[] petyaBasket = {"курица", "бананы", "творог"};
        String[] kolyaBasket = {"курица", "бананы", "творог"};
        String[] terentiyBasket = {"пиво", "пельмени", "Ласка магия чёрного"};

        //Упаковываем корзины Пети, Коли и Терентия в один массив для дальнейшей сортировки и поиска наибольшего и среднего значения в них.
        String[] summedBasket = Stream.of(petyaBasket, kolyaBasket, terentiyBasket)
                .flatMap(Stream::of)
                .toArray(String[]::new);

        //Сравниваем корзины Пети и Коли по размеру.
        if (petyaBasket.length == kolyaBasket.length) {
            System.out.println("Корзины Пети и Коли одинаковы по размеру");
        } else {
            System.out.println("Корзины Пети и Коли разные по размеру");
        }

        //Сравниваем корзины Пети и Терентия по размеру.
        if (petyaBasket.length == terentiyBasket.length) {
            System.out.println("Корзины Пети и Терентия одинаковы по размеру");
        } else {
            System.out.println("Корзины Пети и Терентия разные по размеру");
        }

        //Сравниваем корзины Пети и Коли по составу.
        if (Arrays.equals(petyaBasket, kolyaBasket)) {
            System.out.println("Корзины Пети и Коли одинаковы по составу");
        } else {
            System.out.println("Корзины Пети и Коли разные по составу");
        }

        //Сравниваем корзины Пети и Терентия по составу.
        if (Arrays.equals(petyaBasket, terentiyBasket)) {
            System.out.println("Корзины Пети и Терентия одинаковы по составу");
        } else {
            System.out.println("Корзины Пети и Терентия разные по составу");
        }

        //Сортируем получившийся упакованный массив всех корзин и выводим самое длинное название товара.
        String longestWord = Arrays.stream(summedBasket)
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println("Товар с самым длинным названием: " + longestWord);

        //Сортируем получившийся упакованный массив всех корзин и выводим самое короткое название товара.
        String shortestWord = Arrays.stream(summedBasket)
                .min(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println("Товар с самым коротким названием: " + shortestWord);

        //Выводим среднюю длину названия товара среди всех корзин.
        double averageLength = Arrays.stream(summedBasket)
                .mapToInt(String::length)
                .average()
                .orElse(0.0);

        System.out.printf("Средняя длина слова (символов): " + "%.2f", averageLength);

        System.out.println("\n===================================================");

        System.out.println("Задание №2. Пароли и их анализ");

        //Создаём одномерный массив из трёх паролей.
        String[] passwords = {"SuperSecretPasswordZ", "123qwerty1234", "^&hy5UY%^YHGGGGHJ"};

        //Добавляем булеву переменную для хранения значения годности паролей.
        //Добавляем переменную для хранения значений паролей в нижнем регистре для удобства дальнейшего сравнения.
        //Проверяем пароли на условия соответствия: длиннее 8 символов, не начинается с символа 1, не заканчивается символом z, не содержит последовательность "1234", не содержит последовательность "qwerty" и выводим заключение о пригодности.
        for (String password : passwords) {
            boolean passwordFits;
            String passwordToLowerCase = password.toLowerCase();

            if (passwordToLowerCase != null
                    && passwordToLowerCase.length() > 8
                    && passwordToLowerCase.charAt(0) != '1'
                    && passwordToLowerCase.charAt(password.length() - 1) != 'z'
                    && !passwordToLowerCase.contains("1234")
                    && !passwordToLowerCase.contains("qwerty")) {
                passwordFits = true;
                System.out.println("пароль " + password + " прошёл проверку " + passwordFits);
            } else {
                passwordFits = false;
                System.out.println("пароль " + password + " прошёл проверку " + passwordFits);
            }
        }
    }
}
