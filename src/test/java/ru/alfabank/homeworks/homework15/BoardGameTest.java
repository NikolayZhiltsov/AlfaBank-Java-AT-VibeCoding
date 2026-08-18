package ru.alfabank.homeworks.homework15;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BoardGameTest {

    private static BoardGame boardGame;
    private static String boardTitle;
    private static int playerMinimalAge;
    private static int rentCost;

    //Данные для тестов проверки полей
    @BeforeAll
    static void beforeAll() {
        boardTitle = "Шахматы";
        playerMinimalAge = 5;
        rentCost = 1000;
        boardGame = new BoardGame(boardTitle, playerMinimalAge, rentCost);
    }

    //Тесты проверки полей
    @Test
    void testGetTitle() {
        assertEquals(boardTitle, boardGame.getBoardTitle(),
                "Название игры не совпадает с заданным");
    }

    @Test
    void testGetAge() {
        assertEquals(playerMinimalAge, boardGame.getPlayerMinimalAge(),
                "Минимальный возраст не совпадает с заданным");
    }

    @Test
    void testGetRent() {
        assertEquals(rentCost, boardGame.getRentCost(),
                "Стоимость аренды не совпадает с заданной");
    }

    @Test
    void testIsRented() {
        assertFalse(boardGame.isRented(),
                "Признак аренды игры имеет неверное значение");
    }

    //Отрицательный тест метода проверки минимального возраста
    @Test
    void testIsNotAllowedForRent() {
        assertFalse(boardGame.canBeRentedBy(playerMinimalAge - 1),
                "Игру ошибочно может взять слишком молодой игрок");
    }

    //Положительный тест метода проверки минимального возраста
    @Test
    void testIsAllowedForRent() {
        assertTrue(boardGame.canBeRentedBy(playerMinimalAge),
                "Игру не может взять игрок с валидным возрастом");
    }

    //Данные для параметризованного теста исключений
    static Stream<Arguments> invalidArgumentsProvider() {
        return Stream.of(
                Arguments.of(null, 16, 1000, "Название игры не может быть null",
                        "Исключение на название игры = null не сработало"),
                Arguments.of("", 16, 1000, "Название игры не может быть пустым",
                        "Исключение на пустое название игры не сработало"),
                Arguments.of("   ", 16, 1000, "Название игры не может быть пустым",
                        "Исключение на пустое название игры не сработало"),
                Arguments.of("Монополия", -1, 1000, "Минимальный возраст не может быть меньше нуля",
                        "Исключение на отрицательный возраст не сработало"),
                Arguments.of("Монополия", 12, 0, "Стоимость аренды должна быть больше нуля",
                        "Исключение на нулевую стоимость аренды не сработало"),
                Arguments.of("Монополия", 12, -500, "Стоимость аренды должна быть больше нуля",
                        "Исключение на отрицательную стоимость аренды не сработало")
        );
    }

    //Параметризованный тест исключений
    @ParameterizedTest
    @MethodSource("invalidArgumentsProvider")
    void testConstructorThrowsExceptionForInvalidData
    (String title, int minAge, int price, String expectedMessage, String testFaultMessage) {
        Throwable thrown = assertThrows(
                Throwable.class,
                () -> new BoardGame(title, minAge, price),
                testFaultMessage
        );

        assertInstanceOf(IllegalArgumentException.class, thrown,
                "Ожидалось исключение типа IllegalArgumentException, но реально выброшено: "
                        + thrown.getClass().getName());

        assertEquals(expectedMessage, thrown.getMessage());
    }
}
