package ru.alfabank.homeworks.homework6.delivery.model;

public class FragileParcel extends Parcel {


    // Задаём константу надбавки за бережное обращение и переменную Требуется бережное отношение

    private final int EXTRA_CHARGE_FOR_HANDLE = 200;
    private boolean requiresCarefulHandling;


    // Задаём конструктор с полями родительского класса и полем Требуется бережное отношение

    public FragileParcel(String recipientName, String deliveryAddress, double packageWeight, String trackingNumber, boolean requiresCarefulHandling) {
        super(recipientName, deliveryAddress, packageWeight, trackingNumber);
        this.requiresCarefulHandling = requiresCarefulHandling;
    }


    // Задаём метод для наценки за бережную доставку

    public double calculateDeliveryPrice() {
        if (requiresCarefulHandling) {
            return super.calculateDeliveryPrice() + EXTRA_CHARGE_FOR_HANDLE;
        }
        return super.calculateDeliveryPrice();
    }


    // Задаём метод, выводящий в консоль все поля родительского класса плюс Признак необходимости бережного отношения

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println(String.format("Обращаться с осторожностью: %s", requiresCarefulHandling ? "да" : "нет"));
    }
}
