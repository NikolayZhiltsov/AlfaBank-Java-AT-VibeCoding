package ru.alfabank.homeworks.nomework8;

import java.util.ArrayList;

public class Playlist {


    // Задаём поле названия плейлиста и Arraylist списка песен.

    private String playlistTitle;
    private ArrayList<String> playlist;


    // Задаём конструктор

    public Playlist() {
        this.playlistTitle = playlistTitle;
        this.playlist = new ArrayList<>();
    }


    // Задаём геттеры и сеттеры


    public String getPlaylistTitle() {
        return playlistTitle;
    }

    public ArrayList<String> getPlaylist() {
        return playlist;
    }

    public void setPlaylistTitle(String playlistTitle) {
        this.playlistTitle = playlistTitle;
    }

    public void setPlaylist(ArrayList<String> playlist) {
        this.playlist = playlist;
    }


    // Задаём метод добавления песни в плейлист

    public void addSong(String songTitle) {
        playlist.add(songTitle);
        System.out.println(String.format("Песня \"%s\" добавлена в плейлист на позицию №%d.", songTitle, playlist.size()));
    }


    // Задаём метод удаления песни по индексу с очеловеченное логикой
    // Т.е. учитываем, что пользователь нумерует песни начиная с единицы, а не ноля

    public void deleteSong(int index) {
        index--;
        System.out.println(String.format("Песня №%d \"%s\" удалена из плейлиста.", index + 1, playlist.get(index)));
        playlist.remove(index);
    }


    // Задаём метод обновления песни по индексу с очеловеченное логикой
    // Т.е. учитываем, что пользователь нумерует песни начиная с единицы, а не ноля

    public void updateSong(int index, String songTitle) {
        index--;
        playlist.set(index, songTitle);
        System.out.println(String.format("Песня \"%s\" добавлена на позицию №%d плейлиста.", songTitle, index + 1));
    }


    // Задаём метод получения песни по индексу с очеловеченное логикой
    // Т.е. учитываем, что пользователь нумерует песни начиная с единицы, а не ноля

    public void getSong(int index) {
        index--;
        playlist.get(index);
        System.out.println(String.format("На позиции №%d плейлиста находится песня \"%s\".", index + 1, playlist.get(index)));
    }


    // Переопределяем метод toString() для вывода содержимого плейлиста красиво-построчно

    @Override
    public String toString() {
        if (playlist == null || playlist.isEmpty()) {
            return "Плейлист пуст.";
        }
        return String.join("\n", playlist);
    }
}
