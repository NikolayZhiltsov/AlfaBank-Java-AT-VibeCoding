package ru.alfabank.homeworks.homework11;

public class NotEnoughWaterException extends RuntimeException{

    //Переопределяем исключение через конструктор
    public NotEnoughWaterException(String message) {
        super(message);
    }
}
