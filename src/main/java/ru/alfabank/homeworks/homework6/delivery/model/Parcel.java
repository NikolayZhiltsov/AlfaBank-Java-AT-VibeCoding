package ru.alfabank.homeworks.homework6.delivery.model;

public class Parcel {


    // Задаём вспомогательные константы Базовой стоимости и Коэффициента надбавки и переменные Имя получателя, Адрес доставки, Вес посылки, Трек-номер

    private final int BASE_PRICE = 100;
    private final int EXTRA_CHARGE = 30;
    private String recipientName;
    private String deliveryAddress;
    double packageWeight;
    String trackingNumber;


    // Задаём конструктор с параметрами

    public Parcel(String recipientName, String deliveryAddress, double packageWeight, String trackingNumber) {
        this.recipientName = recipientName;
        this.deliveryAddress = deliveryAddress;
        this.packageWeight = packageWeight;
        this.trackingNumber = trackingNumber;
    }


    // Задаём конструктор по умолчанию

    public Parcel() {

    }


    // Задаём геттеры для полей Имя получателя, Адрес доставки, вес посылки

    public String getRecipientName() {
        return recipientName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public double getPackageWeight() {
        return packageWeight;
    }


    // Задаём сеттеры для полей Имя получателя, Адрес доставки, вес посылки

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setPackageWeight(double packageWeight) {
        this.packageWeight = packageWeight;
    }


    // Задаём метод, возвращающий стоимость доставки посылки

    public double calculateDeliveryPrice() {
        return BASE_PRICE + packageWeight * EXTRA_CHARGE;
    }


    // Задаём метод выводящий в консоль все поля посылки

    public void printInfo() {
        System.out.println(String.format("Имя получателя: %s%nАдрес доставки: %s%nВес посылки: %.2f%nТрек-номер: %s",
                recipientName,
                deliveryAddress,
                packageWeight, trackingNumber));
    }
}