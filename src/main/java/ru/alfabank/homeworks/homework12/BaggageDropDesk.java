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
    public void baggageCheckIn (String passengerName, String flightName, int baggageWeight) {

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
            try {
                throw new FlightNotFoundException("Ошибка: Такого рейса нет в списке!");
            } catch (FlightNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }


        //Проверяем вес багажа на перевес
        if (baggageWeight > 23) {
            try {
                throw new OverweightBaggageException("Ошибка: Вес багажа слишком велик!");
            } catch (OverweightBaggageException e) {
                System.out.println(e.getMessage());
            }
        }

        //Имитируем проблему с печатью бирки
        if (flightName.equals("AE-404")) {
            try {
                throw new BaggageTagPrintException("Ошибка: Неверный номер рейса!");
            } catch (BaggageTagPrintException e) {
                System.out.println(e.getMessage());
            }
        }

        //Описываем поведение в случае успеха
        System.out.println("Уплатите налог за слишком правильно введённые данные!");
        BaggageTicket baggageTicket = new BaggageTicket(passengerName, flightName, baggageWeight);
        baggageTicket.toString();
    }
}
