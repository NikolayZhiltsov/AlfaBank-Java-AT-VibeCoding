package ru.alfabank.homeworks.homework20.calc.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import ru.alfabank.homeworks.homework20.calc.steps.CalculatorSteps;

import java.text.MessageFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Калькулятор")
@Feature("Математические функции")
public class MathFunctionsTest {

    private final CalculatorSteps calculatorSteps = new CalculatorSteps();

    @Story("Возведение в степень")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест, проверяющий возведение числа в заданную степень")
    @Test
    public void testPower() {
        Allure.step("Возвести 2.0 в степень 10.0", () -> {
            double result = calculatorSteps.mod(2.0, 10.0);
            Allure.addAttachment("Результат вычисления", "text/plain",
                    MessageFormat.format("2.0 ^ 10.0 = {0}", result), "txt");
            assertEquals(1024.0, result, "Результат возведения в степень неверный!");
        });
    }

    @Story("Квадратный корень")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Николай Жильцов")
    @Test
    public void testNormalSqrt() {
        double result = calculatorSteps.sqrt(144.0);
        assertEquals(12.0, result, "Результат вычисления квадратного корня неверный!");
    }

    @Story("Квадратный корень")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест, проверяющий корректность выдаваемого исключения при извлечении корня из отрицательного числа")
    @Test
    public void testSqrtOfNegativeNumber() {
        ArithmeticException exception = calculatorSteps.sqrtWithException(-11.0, ArithmeticException.class);
        assertEquals("Корень из отрицательного числа", exception.getMessage(),
                "Текст ошибки не совпадает!");
    }
}
