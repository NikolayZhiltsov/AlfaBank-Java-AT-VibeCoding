package ru.alfabank.homeworks.homework6.delivery.service;

import ru.alfabank.homeworks.homework6.delivery.model.Parcel;

public class ParcelService {


    // Задаём единственный метод класса, который собирает всю информацию в массив и выводит это всё в консоль

    public void printParcelsReport(Parcel[] parcels) {

        for (Parcel parcel : parcels) {
            parcel.printInfo(); // выводим printInfo для каждой посылки
            System.out.println(String.format("Стоимость доставки: %.2f", parcel.calculateDeliveryPrice())); // выводим стоимость доставки каждой посылки
            System.out.println(); // выводим пустую строку в качестве разделителя
        }
    }
}
