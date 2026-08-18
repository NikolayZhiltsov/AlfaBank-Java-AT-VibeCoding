package ru.alfabank.homeworks.homework15;

import java.util.ArrayList;
import java.util.List;

public class GameRental {

    //Задаём коллекцию объектов BoardGame
    private final List<BoardGame> catalog = new ArrayList<>();

    //Задаём метод добавления игры в каталог.
    public void addGame(BoardGame game) {
        if (game == null) {
            throw new IllegalArgumentException("Нельзя добавить игру, равную null");
        }

        //Проверяем, нет ли уже игры с таким же названием
        if (findGame(game.getBoardTitle()) != null) {
            throw new IllegalArgumentException("Игра с названием '" + game.getBoardTitle() + "' уже есть в каталоге");
        }

        catalog.add(game);
    }

    //Задаём метод поиска игры по её названию.
    public BoardGame findGame(String name) {
        if (name == null) {
            return null;
        }

        for (BoardGame game : catalog) {
            if (game.getBoardTitle().equalsIgnoreCase(name.trim())) {
                return game;
            }
        }
        return null; // Если игра не найдена
    }

    //Задаём метод аренды игры.
    public boolean rentGame(String name, int customerAge) {
        BoardGame game = findGame(name);
        if (game == null) {
            throw new IllegalArgumentException("Игры с названием '" + name + "' не существует");
        }

        //Проверяем возраст
        if (!game.canBeRentedBy(customerAge)) {
            return false;
        }

        //Проверяем не арендована ли игра сейчас
        if (game.isRented()) {
            return false;
        }

        game.setRented(true);
        return true;
    }

    //Задаём метод возврата игры.
    public boolean returnGame(String name) {
        BoardGame game = findGame(name);
        if (game == null) {
            return false;
        }

        //Если игра не была арендована, вернуть её нельзя
        if (!game.isRented()) {
            return false;
        }

        game.setRented(false);
        return true;
    }

    //Задаём метод расчёта стоимости аренды.
    public int calculateCost(String name, int days) {
        BoardGame game = findGame(name);
        if (game == null) {
            throw new IllegalArgumentException("Игры с названием '" + name + "' не существует");
        }
        if (days <= 0) {
            throw new IllegalArgumentException("Количество дней должно быть больше нуля");
        }

        return (game.getRentCost() * days);
    }

    // Задаём метод сброса состояния, который делает все игры в каталоге снова доступными.
    public void reset() {
        for (BoardGame game : catalog) {
            game.setRented(false);
        }
    }
}
