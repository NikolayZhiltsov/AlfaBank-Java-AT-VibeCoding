package ru.alfabank.homeworks.homework12;

public class BaggageTicket {

    //Объявляем приватные поля имени пассажира, номера рейса и веса багажа
    private String passengerName;
    private String flightNumber;
    private int baggageWeight;

    //Объявляем конструктор
    public BaggageTicket(String passengerName, String flightNumber, int baggageWeight) {
        this.passengerName = passengerName;
        this.flightNumber = flightNumber;
        this.baggageWeight = baggageWeight;
    }

    //Добавляем геттеры
    public String getPassengerName() {
        return passengerName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getBaggageWeight() {
        return baggageWeight;
    }

    //Переопределяем toString
    @Override
    public String toString() {
        return "BaggageTicket{" +
                "passengerName='" + passengerName + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", baggageWeight=" + baggageWeight + " kg" +
                '}';
    }
}
