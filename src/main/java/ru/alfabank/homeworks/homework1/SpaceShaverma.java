package ru.alfabank.homeworks.homework1;

public class SpaceShaverma {
	public static void main(String[] args) {
		String workerFullName = "Космоторговцев Шаверман Горчицевич";
		String workingPosition = "Старший помощник младшего торговца шавермой 86-го уровня";
		int rate = 100500;
		int numberOfShifts = 22;
		int salaryBonus = 100500;
		int penaltyForBurntPitaBread = 2000000;
		int priceOfOneShaverma = 55000;
		int numberOfShavermaSold = 675;
		
		System.out.println ("Имя сотрудника: " + workerFullName + "\nДолжность: " + workingPosition + "\nОплата за смены: " + numberOfShifts * rate + "\nПремия: " + salaryBonus + "\nШтраф: " + penaltyForBurntPitaBread + "\nИтоговая зарплата: " + (rate * numberOfShifts + salaryBonus - penaltyForBurntPitaBread) + "\nШаур-выручка: " + priceOfOneShaverma * numberOfShavermaSold);
	}
}