package ru.alfabank.homeworks.nomework8;

public class Winamp {
    static void main() {


        // Создаём объект плейлиста

        Playlist playlist = new Playlist();


        // Вызываем метод добавления песни в плейлист

        playlist.addSong("Таранный камень - Золнышко");
        playlist.addSong("Сектор газа - Колхозный панк");
        playlist.addSong("Королева - Зэ шоу должно продолжацца");
        playlist.addSong("Переменное напряжение/Постоянное напряжение - Назад в чёрного");
        playlist.addSong("Розовый Флойд - Зэ тёмная сторона оф зэ Луны");


        // Вызываем метод удаления песни из плейлиста по индексу

        playlist.deleteSong(2);


        // Вызываем метод обновления песни по индексу

        playlist.updateSong(2, "Скутер - Почём рыба?");


        // Вызываем метод получения песни по номеру позиции

        playlist.getSong(4);


        // Выводим в консоль весь плейлист как просили в задании

        System.out.println(playlist.getPlaylist());


        // Выводим в консоль весь плейлист красиво и построчно

        System.out.println(playlist);
    }
}
