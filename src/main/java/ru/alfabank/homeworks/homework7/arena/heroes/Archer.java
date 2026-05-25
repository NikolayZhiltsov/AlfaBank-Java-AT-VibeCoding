package ru.alfabank.homeworks.homework7.arena.heroes;

public class Archer extends Hero{


    // Задаём константы фразы атаки лучника, текста вывода количества стрел и поле количества стрел

    private final static String ARCHER_ATTACK_PHRASE = "Лучник выпускает стрелу!";
    private final static String ARROWS_COUNT_OUTPUT = "Количество стрел: %d";
    private int arrowsCount;


    //Задаём конструктор с полями из родительского класса и полем количества стрел, свойственном только для лучника

    public Archer(String name, int level, int health,  int arrowsCount) {
        super(name, level, health);
        this.arrowsCount = arrowsCount;
    }


    // Задаём геттер для нового поля количество стрел

    public int getArrowsCount() {
        return arrowsCount;
    }


    //Задаём сеттер для нового поля количество стрел

    public void setArrowsCount(int arrowsCount) {
        this.arrowsCount = arrowsCount;
    }


    // Переопределяем метод вывода информации, хоть этого и не было в задании

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println(String.format(ARROWS_COUNT_OUTPUT, arrowsCount));
    }


    // Переопределяем родительский метод нанесения атаки

    @Override
    public void attack() {
        System.out.println(String.format(ARCHER_ATTACK_PHRASE));
    }
}
