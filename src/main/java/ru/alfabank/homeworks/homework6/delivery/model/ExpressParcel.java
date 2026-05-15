package ru.alfabank.homeworks.homework6.delivery.model;

public class ExpressParcel extends Parcel {


    // Задаём константу надбавки за доставку быстрее 24-х часов, константу значения часов для обработки логики надбавки за срочность и переменную Количество часов доставки

    private final int EXTRA_CHARGE_FOR_SPEED = 500;
    private final int NUMBER_OF_HOURS = 24;
    private int deliveryHours;


    // Задаём конструктор с полями родительского класса и полем количества часов доставки

    public ExpressParcel(String recipientName, String deliveryAddress, double packageWeight, String trackingNumber, int deliveryHours) {
        super(recipientName, deliveryAddress, packageWeight, trackingNumber);
        this.deliveryHours = deliveryHours;
    }


    // Задаём метод, накидивающий сверху вознагроаждение за доставку быстрее 24-х часов

    public double calculateDeliveryPrice() {
        if (deliveryHours < NUMBER_OF_HOURS) {
            return super.calculateDeliveryPrice() + EXTRA_CHARGE_FOR_SPEED;
        }
        return super.calculateDeliveryPrice();
    }


    // Задаём метод, выводящий в консоль все поля родительского класса плюс Количество часов доставки

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println(String.format("Крайний срок доставки: %d", deliveryHours));
    }
}
