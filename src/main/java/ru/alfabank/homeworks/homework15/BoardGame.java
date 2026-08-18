package ru.alfabank.homeworks.homework15;

public class BoardGame {

    private String boardTitle;
    private int playerMinimalAge;
    private int rentCost;
    private boolean isRented;

    public BoardGame(String boardTitle, int playerMinimalAge, int rentCost) {
        if (boardTitle == null) {
            throw new IllegalArgumentException("Название игры не может быть null");
        }
        if (boardTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Название игры не может быть пустым");
        }
        if (playerMinimalAge < 0) {
            throw new IllegalArgumentException("Минимальный возраст не может быть меньше нуля");
        }
        if (rentCost <= 0) {
            throw new IllegalArgumentException("Стоимость аренды должна быть больше нуля");
        }
        this.boardTitle = boardTitle;
        this.playerMinimalAge = playerMinimalAge;
        this.rentCost = rentCost;
        this.isRented = false;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public int getPlayerMinimalAge() {
        return playerMinimalAge;
    }

    public int getRentCost() {
        return rentCost;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public boolean canBeRentedBy (int age) {
        return age >= playerMinimalAge;
    }
}
