package ru.alfabank.homeworks.homework14;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MovieRatingComparator implements Comparator<MovieRatingComparator.Movie> {

    static void main(String[] args) {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Интерстеллар", 8.7));
        movies.add(new Movie("Шрек", 8.1));
        movies.add(new Movie("Начало", 8.8));
        movies.add(new Movie("Веном", 6.6));

        System.out.println("Список до сортировки:");
        for (Movie movie : movies) {
            System.out.println(movie);
        }

        //Разделитель
        System.out.println();

        //Сортировка с помощью экземпляра внешнего класса
        movies.sort(new MovieRatingComparator());

        System.out.println("Список после сортировки:");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    //Задаём метод compare интерфейса Comparator
    @Override
    public int compare(Movie m1, Movie m2) {
        return Double.compare(m1.getRating(), m2.getRating());
    }

    //Задаём класс Фильма
    public static class Movie {
        private String title;
        private double rating;

        public Movie(String title, double rating) {
            this.title = title;
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public double getRating() {
            return rating;
        }

        @Override
        public String toString() {
            return title + " (" + rating + ")";
        }
    }
}