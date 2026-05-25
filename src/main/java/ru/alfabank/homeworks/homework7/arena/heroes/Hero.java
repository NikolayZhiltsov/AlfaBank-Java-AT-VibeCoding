package ru.alfabank.homeworks.homework7.arena.heroes;

public class Hero {


    // Задаём константы максимального уровня, шага прироста уровня, фразы для обычного удара и отдыха и переменные имени, уровня, здоровья героя и количества копий экземпляров героя

    private final static int MAX_LEVEL = 100;
    private final static int LEVEL_STEP_UP = 1;
    private final static String ATTACK_PHRASE = "Герой наносит обычный удар.";
    private final static String REST_SIGN = "Герой отдыхает и восстанавливает силы.";

    private String name;
    private int level;
    private int health;
    static int heroesCreated = 0;


    // Задаём конструктор для переменных, не являющихся константами

    public Hero(String name, int level, int health) {
        this.name = name;
        this.level = level;
        this.health = health;
        heroesCreated++;
    }


    // Задаём геттеры для не констант

    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
    }
    public int getHealth() {
        return health;
    }


    // Задаём сеттеры для не констант

    public void setName(String name) {
        this.name = name;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setHealth(int health) {
        this.health = health;
    }


    // Задаём метод, выводящий в консоль имя, уровень и здоровье героя

    public void printInfo() {
        System.out.println(String.format("Имя героя: %s%nУровень: %d%nЗдоровье: %d",
                name,
                level,
                health));
    }


    // Задаём метод уменьшения здоровья героя

    public void takeDamage(int damage) {

        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }


    // Задаём метод увеличения уровня

    public void levelUp() {

        level += LEVEL_STEP_UP;
        if (level > MAX_LEVEL) {
            level = MAX_LEVEL;
        }
    }


    // Задаём метод нанесения атаки героем

    public void attack() {
        System.out.println(String.format(ATTACK_PHRASE));
    }


    // Задаём перегруженный метод атаки, получающий имя цели на вход

    public void attack(String target) {
        System.out.println(String.format("%s Цель: %s.", ATTACK_PHRASE, target));
    }


    // Задаём перегруженный метод атаки, получающий на вход имя цели и количество атак

    public void attack(String target, int times) {
        System.out.println(String.format("Герой атакует цель %s %d раза.", target, times));
    }


    // Задаём метод выводящий количество созданных героев в консоль

    public static void printHeroesCreated() {
        System.out.println(String.format("Всего создано героев %d", heroesCreated));
    }


    // Задаём метод отдыха героя

    public final void rest() {
        System.out.println(String.format(REST_SIGN));
    }
}
