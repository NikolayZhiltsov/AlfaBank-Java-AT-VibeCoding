package ru.alfabank.homeworks.homework15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class RentalCostTest {

    private GameRental rentalStore;

    //Объекты игр для тестов с разной стоимостью
    @BeforeEach
    void setUp() {
        rentalStore = new GameRental();
        rentalStore.addGame(new BoardGame("Шахматы", 6, 500));
        rentalStore.addGame(new BoardGame("Монополия", 12, 1250));
    }

    //Позитивный параметризованный тест
    @ParameterizedTest(name = "Игра [{0}] на {1} дн. должна стоить {2}")
    @CsvSource({
            "Шахматы,   1,  500",
            "Шахматы,   3,  1500",
            "Монополия, 1,  1250",
            "Монополия, 4,  5000"
    })
    void testCalculateCostSuccess(String gameName, int days, int expectedCost) {
        int actualCost = rentalStore.calculateCost(gameName, days);
        assertEquals(expectedCost, actualCost, "Расчёт стоимости выполнен некорректно");
    }

    //Негативный параметризованный тест
    @ParameterizedTest(name = "Данные: игра=[{0}], дни={1} -> ожидается ошибка: {2}")
    @CsvSource({
            "Шахматы,          0, Количество дней должно быть больше нуля",
            "Шахматы,         -5, Количество дней должно быть больше нуля",
            "Несуществующая,   3, Игры с названием 'Несуществующая' не существует"
    })
    void testCalculateCostThrowsException(String gameName, int days, String expectedMessage) {
        Throwable thrown = assertThrows(
                Throwable.class,
                () -> rentalStore.calculateCost(gameName, days),
                "Код выполнился успешно, но ожидалось исключение для параметров: " + gameName + ", " + days
        );

        assertTrue(
                thrown instanceof IllegalArgumentException,
                "Ожидалось IllegalArgumentException, но реально выброшено: " + thrown.getClass().getName()
        );

        assertEquals(expectedMessage, thrown.getMessage());
    }
}
