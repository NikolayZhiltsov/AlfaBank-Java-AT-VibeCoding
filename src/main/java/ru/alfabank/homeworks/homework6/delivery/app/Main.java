package ru.alfabank.homeworks.homework6.delivery.app;

import ru.alfabank.homeworks.homework6.delivery.model.*;
import ru.alfabank.homeworks.homework6.delivery.service.ParcelService;

public class Main {
    static void main() {


        // Создаём по одной обычной, хрупкой, экпресс-посылке и посылку без аргументов

        Parcel trivialParcel = new Parcel("Вася", "г. Бобруйск, ул. Животная, д. 13, Белоруссия", 1254.36, "1542SDS11");
        Parcel fragileParcel = new FragileParcel("Петя", "г. Гусь-Хрустальный, ул. Мастеровая, д. 125, кв. 45", 52144, "151416EDSD11", true);
        Parcel fastParcel = new ExpressParcel("Ваня", "г. Москва, пр-т Строителей коммунизма, д. 154", 3121.21, "1866KFJ111", 2);
        Parcel emptyParcel = new Parcel();


        // Складываем все посылки в массив

        Parcel[] parcels = {trivialParcel, fragileParcel, fastParcel, emptyParcel};


        // Создаём объект ParcelService и передаём в него массив parcels для вывода в консоль

        ParcelService parcelService = new ParcelService();
        parcelService.printParcelsReport(parcels);
    }
}
