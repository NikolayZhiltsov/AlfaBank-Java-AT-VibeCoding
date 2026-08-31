package ru.alfabank.homeworks.homework16.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.*;
import java.util.List;
import java.util.Map;

public class Steps {

    //Базовые шаги действия
    @Когда("гость {string} бронирует столик на {int} человека на время {string}")
    @Когда("гость {string} бронирует столик на {int} человек на время {string}") // Поддержка окончаний
    public void гость_бронирует_столик(String name, int guestsCount, String time) {
        System.out.printf("--- [Шаг] Запрос брони: Имя=%s, Гостей=%d, Время=%s ---%n", name, guestsCount, time);
    }

    //Базовые шаги проверки (Успех)
    @Тогда("бронь успешно подтверждается")
    public void бронь_успешно_подтверждается() {
        System.out.println("--- [Шаг] Проверка: Бронь успешно подтверждена! ---");
    }

    //Базовые шаги проверки (Отказ)
    @Тогда("система возвращает отказ в бронировании")
    public void система_возвращает_отказ_в_бронировании() {
        System.out.println("--- [Шаг] Проверка: Получен корректный отказ в бронировании. ---");
    }

    //Шаги для сценария отмены
    @Допустим("у гостя {string} есть активное бронирование столика № {int}")
    public void у_гостя_есть_активное_бронирование(String name, int tableNumber) {
        System.out.printf("--- [Шаг] Исходное состояние: У гостя %s есть бронь стола №%d ---%n", name, tableNumber);
    }

    @Когда("гость {string} отменяет своё бронирование")
    public void гость_отменяет_свое_бронирование(String name) {
        System.out.printf("--- [Шаг] Действие: Гость %s отменяет бронирование ---%n", name);
    }

    @Тогда("бронь успешно аннулируется")
    public void бронь_успешно_аннулируется() {
        System.out.println("--- [Шаг] Проверка: Бронь успешно аннулирована в системе. ---");
    }

    //Scenario Outline шаги
    @Когда("гость делает запрос на столик вместимостью {int} для {int} человек")
    public void гость_делает_запрос_на_столик_параметры(int tableCapacity, int guestsCount) {
        System.out.printf("--- [Шаг Outline] Проверка пары: Стол=%d / Гости=%d ---%n", tableCapacity, guestsCount);
    }

    @Тогда("система выдает результат: {string}")
    public void система_выдает_результат(String expectedResult) {
        System.out.printf("--- [Шаг Outline] Проверка исхода: Ожидается -> %s ---%n", expectedResult);
    }

    //Data Table
    @Дано("в ресторане есть столики:")
    public void в_ресторане_есть_столики(DataTable dataTable) {
        System.out.println("\n--- [Шаг] Инициализация столиков из Data Table ---");
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            System.out.printf("Столик №%s, Вместимость: %s гостей.%n",
                    row.get("номер"), row.get("вместимость"));
        }
    }

    //Doc String (Пожелание клиента)
    @Когда("гость оставляет пожелание к брони:")
    public void гость_оставляет_пожелание_к_брони(String textComment) {
        System.out.println("--- [Шаг] Получено особое пожелание клиента ---");
        System.out.println("Текст комментария:\n" + textComment);
        System.out.println("--------------------------------------------");
    }

}
