package ru.alfabank.homeworks.homework14;

public class XMLUtils {

    static void main(String[] args) {
        System.out.println("Запуск тестов XMLUtils...");

        int failedTests = 0;

        if (!testValidTagName()) failedTests++;
        if (!testNullTagName()) failedTests++;
        if (!testEmptyTagName()) failedTests++;

        if (failedTests == 0) {
            System.out.println("\nВсе тесты успешно пройдены!");
        } else {
            System.out.println("\nОбнаружены ошибки в тестах! Количество проваленных: " + failedTests);
        }
    }

    //Тест по паттерну ААА
    private static boolean testValidTagName() {
        //1. Arrange
        String inputTagName = "user";
        String expectedResult = "<user></user>";

        //2. Act
        String actualResult = XMLUtils.createEmptyElement(inputTagName);

        //3. Assert
        return assertEquals(expectedResult, actualResult,
                String.format("Метод должен возвращать '%s' для корректного тега '%s', но вернул '%s'",
                        expectedResult, inputTagName, actualResult));
    }

    private static boolean testNullTagName() {
        //1. Arrange
        String inputTagName = null;
        String expectedResult = "<invalid/>";

        //2. Act
        String actualResult = XMLUtils.createEmptyElement(inputTagName);

        //3. Assert
        return assertEquals(expectedResult, actualResult,
                String.format("Метод должен возвращать '%s', если передан null, но вернул '%s'",
                        expectedResult, actualResult));
    }

    private static boolean testEmptyTagName() {
        //1. Arrange
        String inputTagName = "";
        String expectedResult = "<invalid/>";

        //2. Act
        String actualResult = XMLUtils.createEmptyElement(inputTagName);

        //3. Assert
        return assertEquals(expectedResult, actualResult,
                String.format("Метод должен возвращать '%s', если передана пустая строка, но вернул '%s'",
                        expectedResult, actualResult));
    }

    //Задаём пустой XML-элемент по имени тега.
    public static String createEmptyElement(String tagName) {
        if (tagName == null || tagName.isEmpty()) {
            return "<invalid/>";
        }
        return "<" + tagName + "></" + tagName + ">";
    }

    //Задаём кастомный аналог метода ассерта.
    private static boolean assertEquals(String expected, String actual, String errorMessage) {
        if (!expected.equals(actual)) {
            System.err.println("[FAIL] Ошибка утверждения: " + errorMessage);
            return false;
        }
        return true;
    }
}
