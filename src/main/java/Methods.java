import java.time.LocalDate;
import java.util.Random;
import java.util.stream.IntStream;

public class Methods {
    public static void main(String[] args) {

        //Реализуем протокол ДАШБОРД

        //Генерируем код доступа с помощью протокола ГЕНЕРАТОР и сохраняем в переменную, затем выводим получившийся код доступа
        String accessCode = generateAccessCode();
        System.out.println(String.format("Код доступа сгенерирован: %s", accessCode));

        //Валидируем получившийся код доступа с помощью протокола ВАЛИДАТОР и выводим результат проверки
        boolean codeValidation = isValidCode(accessCode, 8);
        System.out.println(String.format("Код доступа корректен? %s", codeValidation));

        //Записываем в лог событие "Server protection activated" и выводим получившееся сообщение
        String event1 = logEvent("Server protection activated");
        System.out.println(event1);

        String event2 = logEvent("Intrusion attempt detected", true);
        System.out.println(event2);

        //Генерируем три ID агентов и выводим каждый получившийся:
        final String agentPrefix = "AGENT";
        int[] agentSeeds = {42, 77, 13};

        IntStream.range(0, agentSeeds.length) // Генерируем поток индексов: 0, 1, 2
                .forEach(i -> {
                    String agentName = (generateAgentId(agentPrefix, agentSeeds[i]));
                    System.out.println(String.format("%s-й агент: %s", (i + 1), agentName));
                });

    }

    //Создаём метод, генерирующий код доступа (протокол ГЕНЕРАТОР)
    public static String generateAccessCode() {

        //Вводим переменные и константы для наполнения сгенерированного кода доступа
        final String SEPARATOR = "-";
        int firstPartOfCode = LocalDate.now().getYear();
        int secondPartOfCOde = (int) Math.pow(3, 7);

        //Возвращаем получившийся код доступа
        return firstPartOfCode + SEPARATOR + secondPartOfCOde;
    }

    //Создаём метод, валидирующий код (протокол ВАЛИДАТОР)
    private static boolean isValidCode(String code, int minLength) {

        //Вводим константу для параметров оценки правильности кода
        final String SEPARATOR = "-";

        //Выполняем проверки валидности полученного кода
        return code != null && code.length() >= minLength && code.contains(SEPARATOR);
    }

    //Создаём первую версию логгера, принимающую на вход только стрингу (протокол ЛОГГЕР №1)
    public static String logEvent (String message) {

        return "[INFO]" + message;
    }

    //Создаём вторую версию логгера, перегруженную, которая принимает на вход стрингу и логическую переменную (протокол ЛОГГЕР №2)
    public static String logEvent (String message, boolean isCritical) {

        if (isCritical) {
            return logEvent("[CRITICAL]" + message); //Добавляем новую логику перегруженного метода
        }
        return logEvent(message); //Переиспользуем оригинальный метод logEvent
    }

    //Создаём метод для рандомайзера (протокол РАНДОМАЙЗЕР)
    private static String generateAgentId (String prefix, int seed) {

        //Добавляем константу разделителя для конечного значения, объявляем рандомайзер и получаем вторую часть
        final String SEPARATOR = "-";
        Random random = new Random(seed);
        int randomizedSeed = random.nextInt(1000, 10000);

        //Возвращаем получившееся значение
        return prefix + SEPARATOR + randomizedSeed;
    }

}
