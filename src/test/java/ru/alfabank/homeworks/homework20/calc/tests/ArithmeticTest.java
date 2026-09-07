package ru.alfabank.homeworks.homework20.calc.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.alfabank.homeworks.homework20.calc.steps.CalculatorSteps;

import java.text.MessageFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Калькулятор")
@Feature("Арифметические операции")
public class ArithmeticTest {

    private final CalculatorSteps calculatorSteps = new CalculatorSteps();

    @Story("Сложение")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Николай Жильцов")
    @Description("Параметризованный тест, проверяющий сложение положительных и отрицательных чисел")
    @ParameterizedTest(name = "Сложение {0} и {1} должно быть равно {2}")
    @CsvSource({
            "2.0,  3.0,  5.0",
            "-5.0, 3.0, -2.0"
    })
    public void testAddition(double a, double b, double expected) {
        Allure.parameter("Первое число", a);
        Allure.parameter("Второе число", b);
        Allure.parameter("Ожидаемый результат", expected);
        double result = calculatorSteps.add(a, b);
        assertEquals(expected, result, "Результат сложения неверный!");
    }

    @Story("Вычитание")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Николай Жильцов")
    @Test
    public void testSubtraction() {
        double result = calculatorSteps.sub(10.0, 4.0);
        Allure.addAttachment("Результат вычитания", "text/plain",
                MessageFormat.format("10.0 - 4.0 = {0}", result), "txt");
        assertEquals(6.0, result, "Результат вычитания неверный!");
    }

    @Story("Умножение")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testMultiplication() {
        double result = calculatorSteps.mul(7.0, 8.0);
        assertEquals(56.0, result, "Результат умножения неверный!");
    }

    @Story("Деление")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testNormalDivision() {
        double result = calculatorSteps.div(15.0, 3.0);
        assertEquals(5.0, result, "Результат деления неверный!");
    }

    @Story("Деление")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест, проверяющий корректность выдаваемого исключения при делении на ноль")
    @Issue("CALC-42")
    @Test
    public void testDivisionByZero() {
        ArithmeticException exception = calculatorSteps.divWithException(10.0, 0.0, ArithmeticException.class);
        assertEquals("Деление на ноль", exception.getMessage(), "Текст ошибки не совпадает!");
    }
}

