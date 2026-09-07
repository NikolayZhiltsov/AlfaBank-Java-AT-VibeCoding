package ru.alfabank.homeworks.homework20.calc.steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import ru.alfabank.homeworks.homework20.calc.Calculator;

public class CalculatorSteps {
    private final Calculator calculator = new Calculator();

    @Step("Сложить {a} + {b}")
    public double add(double a, double b) {
        return calculator.add(a, b);
    }

    @Step("Вычесть {a} - {b}")
    public double sub(double a, double b) {
        return calculator.subtract(a, b);
    }

    @Step("Умножить {a} × {b}")
    public double mul(double a, double b) {
        return calculator.multiply(a, b);
    }

    @Step("Разделить {a} ÷ {b}")
    public double div(double a, double b) {
        return calculator.divide(a, b);
    }

    @Step("Разделить {a} ÷ {b} и получить ошибку {expectedException}")
    public <T extends Throwable> T divWithException(double a, double b, Class<T> expectedException) {
        return Assertions.assertThrows(expectedException, () -> {
            calculator.divide(a, b);
        });
    }

    @Step("Возвести {a} в степень {b}")
    public double mod(double a, double b) {
        return calculator.power(a, b);
    }

    @Step("Вычислить квадратный корень числа {a}")
    public double sqrt(double a) {
        return calculator.sqrt(a);
    }

    @Step("Вычислить квадратный корень числа {a} и получить ошибку {expectedException}")
    public <T extends Throwable> T sqrtWithException(double a, Class<T> expectedException) {
        return Assertions.assertThrows(expectedException, () -> {
            calculator.sqrt(a);
        });
    }
}
