package ru.alfabank.homeworks.homework7.arena.heroes;

public class Mage extends Hero{


    // Задаём константу фразы атаки мага, текста вывода количества маны и поле маны

    private final static String MAGE_ATTACK_PHRASE = "Маг запускает огненный шар!";
    private final static String MANA_COUNT_OUTPUT = "Количество маны: %d";
    private int mana;


    //Задаём конструктор с полями из родительского класса и полем маны, свойственном только для мага

    public Mage(String name, int level, int health,  int mana) {
        super(name, level, health);
        this.mana = mana;
    }


    // Задаём геттер для нового поля мана

    public int getMana() {
        return mana;
    }


    //Задаём сеттер для нового поля мана

    public void setMana(int mana) {
        this.mana = mana;
    }


    // Переопределяем метод вывода информации, хоть этого и не было в задании

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println(String.format(MANA_COUNT_OUTPUT, mana));
    }


    // Переопределяем родительский метод нанесения атаки

    @Override
    public void attack() {
        System.out.println(String.format(MAGE_ATTACK_PHRASE));
    }
}
