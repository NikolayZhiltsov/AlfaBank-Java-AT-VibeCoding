package ru.alfabank.homeworks.homework7.arena.heroes;

public class Knight extends Hero{


    // Задаём константы фразы атаки рыцаря, текста вывода уровня брони и поле брони

    private final static String KNIGHT_ATTACK_PHRASE = "Рыцарь бьёт мечом!";
    private final static String ARMOR_LEVEL_OUTPUT = "Уровень брони: %d";
    private int armor;


    //Задаём конструктор с полями из родительского класса и полем брони, свойственном только для рыцаря

    public Knight(String name, int level, int health,  int armor) {
        super(name, level, health);
        this.armor = armor;
    }


    // Задаём геттер для нового поля броня

    public int getArmor() {
        return armor;
    }


    //Задаём сеттер для нового поля броня

    public void setArmor(int armor) {
        this.armor = armor;
    }


    // Переопределяем метод вывода информации, хоть этого и не было в задании

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println(String.format(ARMOR_LEVEL_OUTPUT, armor));
    }


    // Переопределяем родительский метод нанесения атаки

    @Override
    public void attack() {
        System.out.println(String.format(KNIGHT_ATTACK_PHRASE));
    }


    // Пытаемся переопределить метод отдыха из основного класса героя

//    @Override
//    public final void rest() {
//        super.rest();
//    }


    // Переопределяем метод toString() класса Object для того, чтобы выводить все поля рыцаря

    @Override
    public String toString() {
        super.printInfo();
        return "Броня: " + getArmor() ;
    }
}
