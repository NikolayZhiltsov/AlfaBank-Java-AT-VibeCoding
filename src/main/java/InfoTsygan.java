import java.util.Random;

public class InfoTsygan {
	public static void main(String[] args) {
		Random rnd = new Random();

		// Генерируем через рандомайзер возраст и сумму налички
		int visitorAge = rnd.nextInt(0, 120);
		int amountOnAccount = rnd.nextInt(2000000000);

		// Задаём явно, как и описано в задании, признаки наличия приглашения и нахождения в чёрном списке
		boolean hasInvitation = true;
		boolean blackListed = false;

		// Создаём переменную для определения совершеннолетия посетителя и метод её определения
		boolean visitorIsAdult = false;
		if (visitorAge >= 18) {
			visitorIsAdult = true;
		}

		// Создаём переменную для определения наличия приглашения или достаточной суммы на счёте и метод её определения
		boolean hasCashOrInvite = false;
		if (amountOnAccount > 50000 || hasInvitation) {
			hasCashOrInvite = true;
		}

		// Создаём переменную для определения подходит ли посетитель или нет по совокупности признаков: совершеннолетний, имеет необходимую сумму или приглашение, не внесён в чёрный список
		boolean hasAccess = false;
		if (visitorIsAdult && hasCashOrInvite && !blackListed)  {
			hasAccess = true;
		}

		// Создаём переменную для суммы обязательного добровольного взноса и определяем его размер
		double mandatoryVoluntaryContribution = amountOnAccount / 100 * 7.5;

		// Выводим в консоль подходит ли клиент и сумму взноса
		System.out.println("Пользователь подходит: " + hasAccess);
		System.out.printf("Обязательный добровольный взнос: " + "%.2f", mandatoryVoluntaryContribution);
	}
}