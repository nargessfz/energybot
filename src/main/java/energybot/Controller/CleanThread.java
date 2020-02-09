package energybot.Controller;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import energybot.energyBot.Oil;
import energybot.energyBot.OilHandlrer;

public class CleanThread implements Runnable {
	public void run() {
		while (true) {
			try {
			//	TimeUnit.SECONDS.sleep(30);

				TimeUnit.MINUTES.sleep(5);
				Long LastValidTime = new Date().getTime() - (1000 * 300);
				Iterator<Entry<Long, Oil>> it = OilHandlrer.mapOfOils.entrySet().iterator();
				while (it.hasNext()) {
					Entry<Long, Oil> mpair = (Entry<Long, Oil>) it.next();
					if (mpair.getValue().getLastUpdated() < LastValidTime) {
						OilHandlrer.mapOfOils.remove(mpair.getKey());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}
}
