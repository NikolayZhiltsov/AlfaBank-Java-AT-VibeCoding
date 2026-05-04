import javax.lang.model.type.NullType;
import java.util.Scanner;

public class LinesAndConditionsAndLoops {
    public static void main(String[] args) {

        System.out.println("Задание №1 — Секретное послание древнего кода.");

        //Добавляем сканер и константы для разделителей и дальнейшего сбора текста из ввода консоли
        Scanner input = new Scanner(System.in);
        StringBuilder totalMessage = new StringBuilder();
        final String SEPARATOR_FOR_PARTS = "#";
        final String DAMAGED_PARTS_REPLACER = "XX";

        //Запрашиваем ввод текста в консоли
        System.out.println("Необходимо ввести последовательно 5 частей закодированного сообщения.");

        for (int i = 1; i <= 5; i++) {
            System.out.println(String.format("Введите %d-ю часть сообщения:", i));
            String line = input.nextLine();
            if (i > 1) {
                totalMessage.append(SEPARATOR_FOR_PARTS);
            }
            if (line == null || line.isEmpty()) {
                System.out.println("Часть сообщения повреждена! Используем резервный фрагмент...");
                totalMessage.append(DAMAGED_PARTS_REPLACER);
            } else {
                totalMessage.append(line);
            }
        }

        //Выводим результат
        System.out.println(String.format("Расшифрованное послание: %s", totalMessage));

        System.out.println("========================================");

        System.out.println("Задание №2 — Ночная смена в QA-департаменте.");

        //Добавляем переменные для дальнейшей обработки в цикле и флаг для условия побудки тимлида
        int flaky = 0;
        int bug = 0;
        int critical = 0;
        int pass = 0;
        boolean showOnlyIssues = true;

        //Прогоняем цикл и проверяем критерии соответствия номеров тестов и их результатов
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                critical++;
                System.out.println(String.format("Тест #%d: Critical!", i));
            }
            else if (i % 3 == 0) {
                flaky++;
                System.out.println(String.format("Тест #%d: Flaky", i));
            }
            else if (i % 5 == 0) {
                bug++;
                System.out.println(String.format("Тест #%d: Bug", i));
            } else if (showOnlyIssues && critical == 3){
                System.out.println("🚨 Слишком много критических багов — будим тимлида!");
                break;
            } else if (showOnlyIssues) {
                pass++;
            } else {
                pass++;
                System.out.println(String.format("Тест #%d: Pass", i));
            }
        }

        //Суммируем общее количество тестов и выводим количество тестов с каждым статусом
        int total = flaky + bug + critical + pass;
        System.out.println(String.format("=====ИТОГИ НОЧНОЙ СМЕНЫ=====\n" +
                "Всего тестов:  %d\n" +
                "Pass:          %d\n" +
                "Flaky:         %d\n" +
                "Bug:           %d\n" +
                "Critical:      %d\n", total, pass, flaky, bug, critical));

    }
}
