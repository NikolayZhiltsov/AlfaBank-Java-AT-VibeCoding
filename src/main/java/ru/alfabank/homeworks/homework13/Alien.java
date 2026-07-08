package ru.alfabank.homeworks.homework13;

import java.util.Objects;

public class Alien {

    //Задаём поля
    private String name;
    private String planet;
    private int dangerLevel;

    //Задаём конструктор
    public Alien(String name, String planet, int dangerLevel) {
        this.name = name;
        this.planet = planet;
        this.dangerLevel = dangerLevel;
    }

    //Задаём геттеры и сеттеры (сеттер уровня опасности с проверкой диапазона уровня)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(int dangerLevel) {
        if (dangerLevel < 1 || dangerLevel > 10) {
            throw new IllegalArgumentException("Уровень опасности должен быть в диапазоне от 1 до 10! " + '\'' +
                    "Вы задали: " + dangerLevel);
        }
        this.dangerLevel = dangerLevel;
    }

    //Переопределяем equals() и hashCode(), чтобы пришельцы считались одинаковыми при совпадении имени и планеты
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alien alien = (Alien) o;
        return Objects.equals(name, alien.name) && Objects.equals(planet, alien.planet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, planet);
    }

    //Переопределяем toString()
    @Override
    public String toString() {
        return "Параметры чужого: " + '\n'  +
                "имя: " + name + '\n' +
                "планета: " + planet + '\n' +
                "уровень опасности = " + dangerLevel;
    }
}
