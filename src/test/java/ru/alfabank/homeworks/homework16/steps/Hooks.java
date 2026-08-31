package ru.alfabank.homeworks.homework16.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("\n==================================================");
        System.out.println("[HOOK] Старт сценария: " + scenario.getName());
        System.out.println("==================================================");
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("==================================================");
        String status = scenario.isFailed() ? "УПАЛ (FAILED)" : "ПРОШЕЛ (PASSED)";
        System.out.println("[HOOK] Сценарий завершен. Статус: " + status);
        System.out.println("==================================================\n");
    }
}
