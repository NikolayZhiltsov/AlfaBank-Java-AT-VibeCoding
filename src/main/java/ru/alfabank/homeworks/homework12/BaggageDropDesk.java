package ru.alfabank.homeworks.homework12;

import ru.alfabank.homeworks.homework12.exceptions.unverifiable.InvalidBaggageWeightException;
import ru.alfabank.homeworks.homework12.exceptions.unverifiable.InvalidPassengerNameException;
import ru.alfabank.homeworks.homework12.exceptions.verifiable.AirportServiceException;
import ru.alfabank.homeworks.homework12.exceptions.verifiable.BaggageTagPrintException;
import ru.alfabank.homeworks.homework12.exceptions.verifiable.FlightNotFoundException;
import ru.alfabank.homeworks.homework12.exceptions.verifiable.OverweightBaggageException;

import java.util.ArrayList;

public class BaggageDropDesk {

    //Объявляем массив доступных рейсов
    private ArrayList<String> flights;

    //Добавляем конструктор, инициализирующий массив доступных рейсов
    public BaggageDropDesk(ArrayList flights) {
        this.flights = flights;
    }

    //Задаём метод сдачи багажа
    public void baggageCheckIn (String passengerName, String flightName, int baggageWeight)
            throws BaggageTagPrintException, FlightNotFoundException, OverweightBaggageException {

        //Проверяем имя пассажира
        if (passengerName == null || passengerName.isBlank()) {
            throw new InvalidPassengerNameException("Ошибка: Имя пассажира не может быть пустым!");
        }

        //Проверяем вес багажа на отрицательные значения
        if (baggageWeight < 0) {
            throw new InvalidBaggageWeightException("Ошибка: Вес багажа не может быть отрицательным!");
        }

        //Проверяем наличие рейса
        if (!flights.contains(flightName)) {
            throw new FlightNotFoundException("Ошибка: Такого рейса нет в списке!");
        }


        //Проверяем вес багажа на перевес
        if (baggageWeight > 23) {
            throw new OverweightBaggageException("Ошибка: Вес багажа слишком велик!");
        }

        //Имитируем проблему с печатью бирки
        if (flightName.equals("AE-404")) {
            throw new BaggageTagPrintException("Ошибка: Неверный номер рейса!");
        }

        //Описываем поведение в случае успеха
        else {
            System.out.println("Уплатите налог за слишком правильно введённые данные!");
            BaggageTicket baggageTicket = new BaggageTicket(passengerName, flightName, baggageWeight);
            baggageTicket.toString();
        }
    }
}
