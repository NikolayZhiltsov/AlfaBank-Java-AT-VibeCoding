package ru.alfabank.homeworks.homework15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameCatalogueTest {
    private GameRental rentalStore;
    private BoardGame chess;
    private BoardGame monopoly;

    //Объекты игр для тестов
    @BeforeEach
    void setUp() {
        rentalStore = new GameRental();
        chess = new BoardGame("Шахматы", 6, 500);
        monopoly = new BoardGame("Монополия", 12, 1000);
    }

    //Тест успешного добавления игр
    @Test
    void testAddGameSuccess() {
        rentalStore.addGame(chess);
        BoardGame foundGame = rentalStore.findGame("Шахматы");
        assertNotNull(foundGame, "Игра должна быть успешно добавлена в каталог");
        assertEquals("Шахматы", foundGame.getBoardTitle());
    }

    //Тест исключения при добавлении игры со значением null
    @Test
    void testAddNullGameThrowsException() {
        Throwable thrown = assertThrows(
                Throwable.class,
                () -> rentalStore.addGame(null),
                "Код выполнился успешно, но ожидалось исключение при добавлении null!"
        );

        assertTrue(
                thrown instanceof IllegalArgumentException,
                "Ожидалось IllegalArgumentException, но выброшено: " + thrown.getClass().getName()
        );
        assertEquals("Нельзя добавить игру, равную null", thrown.getMessage());
    }

    //Тест на добавление дубликатов
    @Test
    void testAddDuplicateGameThrowsException() {
        rentalStore.addGame(chess);
        BoardGame duplicateChess = new BoardGame("Шахматы", 16, 800);
        Throwable thrown = assertThrows(
                Throwable.class,
                () -> rentalStore.addGame(duplicateChess),
                "Каталог позволил добавить игру с дублирующимся названием!"
        );

        assertTrue(
                thrown instanceof IllegalArgumentException,
                "Ожидалось IllegalArgumentException при добавлении дубликата, но выброшено: " + thrown.getClass().getName()
        );
        assertEquals("Игра с названием 'Шахматы' уже есть в каталоге", thrown.getMessage());
    }

    //Тест поиска существующей игры
    @Test
    void testFindGameWhenExists() {
        rentalStore.addGame(chess);
        rentalStore.addGame(monopoly);
        BoardGame found = rentalStore.findGame("Монополия");
        assertNotNull(found, "Игра должна быть найдена");
        assertEquals("Монополия", found.getBoardTitle());
    }

    //Тест поиска несуществующей игры
    @Test
    void testFindGameWhenNotExistsReturnsNull() {
        rentalStore.addGame(chess);
        BoardGame found = rentalStore.findGame("Мафия");
        assertNull(found, "Метод должен возвращать null, если игры нет в каталоге");
    }

    //Тест чувствительности поиска игр к регистру
    @Test
    void testFindGameIsCaseInsensitiveAndTrimsSpaces() {
        rentalStore.addGame(chess);
        BoardGame found = rentalStore.findGame("  шАхМаТы ");
        assertNotNull(found, "Поиск должен игнорировать регистр букв и пробелы по краям");
        assertEquals("Шахматы", found.getBoardTitle());
    }
}
