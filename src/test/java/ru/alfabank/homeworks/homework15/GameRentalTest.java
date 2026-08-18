package ru.alfabank.homeworks.homework15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameRentalTest {

    private GameRental rentalStore;
    private BoardGame adultGame;
    private BoardGame kidsGame;

    //Объекты игр для тестов с разными возрастами.
    @BeforeEach
    void setUp() {
        rentalStore = new GameRental();
        adultGame = new BoardGame("Мафия", 18, 1200);
        kidsGame = new BoardGame("Добл", 6, 400);
        rentalStore.addGame(adultGame);
        rentalStore.addGame(kidsGame);
    }

    //Тест успешной аренды
    @Test
    void testRentGameSuccess() {
        boolean result = rentalStore.rentGame("Мафия", 20);
        assertTrue(result, "Метод должен вернуть true при успешной аренде");
        assertTrue(adultGame.isRented(), "Статус игры должен измениться на 'арендована'");
    }

    //Тест неуспешной аренды из-за неподходящего возраста
    @Test
    void testRentGameTooYoungReturnsFalse() {
        boolean result = rentalStore.rentGame("Мафия", 15);
        assertFalse(result, "Метод должен вернуть false, если клиент слишком молод");
        assertFalse(adultGame.isRented(), "Статус игры не должен измениться");
    }

    //Тест повторной аренды уже арендованной игры
    @Test
    void testRentGameAlreadyRentedReturnsFalse() {
        rentalStore.rentGame("Добл", 10);
        boolean result = rentalStore.rentGame("Добл", 25);
        assertFalse(result, "Метод должен вернуть false, если игра уже занята");
    }

    //Тест исключения при попытке арендовать несуществующую игру
    @Test
    void testRentNotExistingGameThrowsException() {
        Throwable thrown = assertThrows(
                Throwable.class,
                () -> rentalStore.rentGame("Шахматы", 20),
                "Код должен был выбросить исключение, так как такой игры нет в каталоге"
        );

        assertTrue(
                thrown instanceof IllegalArgumentException,
                "Ожидалось IllegalArgumentException, но выброшено: " + thrown.getClass().getName()
        );
        assertEquals("Игры с названием 'Шахматы' не существует", thrown.getMessage());
    }

    //Тест успешного возврата арендованной игры
    @Test
    void testReturnGameSuccess() {
        rentalStore.rentGame("Мафия", 25);
        assertTrue(adultGame.isRented());
        boolean result = rentalStore.returnGame("Мафия");
        assertTrue(result, "Метод должен вернуть true при успешном возврате");
        assertFalse(adultGame.isRented(), "Статус игры должен измениться на 'доступна' (false)");
    }

    //Тест неуспешного возврата игры из-за того, что она не аврендована
    @Test
    void testReturnGameNotRentedReturnsFalse() {
        boolean result = rentalStore.returnGame("Добл");
        assertFalse(result, "Метод должен вернуть false, если игра и так была в магазине");
    }

    //Тест неуспешного возврата игры из-за того, что она не существует
    @Test
    void testReturnNotExistingGameReturnsFalse() {
        boolean result = rentalStore.returnGame("Неизвестная Игра");
        assertFalse(result, "Метод должен вернуть false, если игра не существует");
    }
}
