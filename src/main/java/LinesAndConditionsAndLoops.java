import java.util.Scanner;

public class LinesAndConditionsAndLoops {
    public static void main(String[] args) {

        System.out.println("Задание №1 — Секретное послание древнего кода.");

        //Добавляем сканер и константы для разделителей и дальнейшего сбора и вывода текста из ввода консоли
        final String SEPARATOR_FOR_PARTS = "#";
        final String DAMAGED_PARTS_REPLACER = "XX";
        final int PARTS_COUNTER = 5;
        final String INPUT_PART_MESSAGE = "Введите %d-ю часть сообщения:";
        final String INPUT_PART_ERROR_MESSAGE = "Часть сообщения повреждена! Используем резервный фрагмент...";
        final String FINAL_UNENCRYPTED_MESSAGE = "Расшифрованное послание: %s";
        Scanner input = new Scanner(System.in);
        StringBuilder totalMessage = new StringBuilder();

        //Запрашиваем ввод текста в консоли
        System.out.println("Необходимо ввести последовательно 5 частей закодированного сообщения.");

        for (int i = 1; i <= PARTS_COUNTER; i++) {
            System.out.println(String.format(INPUT_PART_MESSAGE, i));
            String line = input.nextLine();
            if (i > 1) {
                totalMessage.append(SEPARATOR_FOR_PARTS);
            }
            if (line == null || line.isEmpty() || line.isBlank()) {
                System.out.println(INPUT_PART_ERROR_MESSAGE);
                totalMessage.append(DAMAGED_PARTS_REPLACER);
            } else {
                totalMessage.append(line);
            }
        }

        //Выводим результат
        System.out.println(String.format(FINAL_UNENCRYPTED_MESSAGE, totalMessage));

        System.out.println("========================================");

        System.out.println("Задание №2 — Ночная смена в QA-департаменте.");

        //Добавляем переменные для дальнейшей обработки в цикле и флаг для условия побудки тимлида
        final String CRITICAL_MESSAGE = "Тест #%d: Critical!";
        final String FLAKY_MESSAGE = "Тест #%d: Flaky";
        final String BUG_MESSAGE = "Тест #%d: Bug";
        final String PASS_MESSAGE = "Тест #%d: Pass";
        final String ALARM_MESSAGE = "🚨 Слишком много критических багов — будим тимлида!";
        final String STATISTIC_MESSAGE = "=====ИТОГИ НОЧНОЙ СМЕНЫ=====\n" +
                "Всего тестов:  %d\n" +
                "Pass:          %d\n" +
                "Flaky:         %d\n" +
                "Bug:           %d\n" +
                "Critical:      %d\n";
        int flaky = 0;
        int bug = 0;
        int critical = 0;
        int pass = 0;
        int total;
        boolean showOnlyIssues = true;


        //Прогоняем цикл и проверяем критерии соответствия номеров тестов и их результатов
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                critical++;
                System.out.println(String.format(CRITICAL_MESSAGE, i));
            }
            else if (i % 3 == 0) {
                flaky++;
                System.out.println(String.format(FLAKY_MESSAGE, i));
            }
            else if (i % 5 == 0) {
                bug++;
                System.out.println(String.format(BUG_MESSAGE, i));
            } else if (showOnlyIssues && critical == 3){
                System.out.println(ALARM_MESSAGE);
                break;
            } else if (showOnlyIssues) {
                pass++;
            } else {
                pass++;
                System.out.println(String.format(PASS_MESSAGE, i));
            }
        }

        //Суммируем общее количество тестов и выводим количество тестов с каждым статусом
        total = flaky + bug + critical + pass;
        System.out.println(String.format(STATISTIC_MESSAGE, total, pass, flaky, bug, critical));

    }
}
