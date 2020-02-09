package energybot.energyBot;

import java.util.Date;
import java.util.HashMap;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import energybot.Controller.CleanThread;
import energybot.DB.EnergyDAO;
import energybot.DB.EnergyInfo;

/**
 * Hello world!
 *
 */
public class App {
	private static EnergyDAO energyDAO = EnergyDAO.getInstance();

	public static void main(String[] args) {
		
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(new energybot());

		} catch (TelegramApiException e) {
			e.printStackTrace();
		}

		  CleanThread myThread = new CleanThread();
		  myThread.run();
		
	}
}
