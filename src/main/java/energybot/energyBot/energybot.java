package energybot.energyBot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import energybot.Controller.EnergyInfoController;
import energybot.DB.EnergyDAO;

public class energybot extends TelegramLongPollingBot {
	private static EnergyInfoController energyInfoController = EnergyInfoController.getInstance();
	String text0 = "You've probably heard in the News that the Government has decided to ration Gasoline again in an unjust way - giving quota to each car instead of each person! This means continuing to pay subsidy to high consumption users from all Iranians’ pocket! (According to national household budget survey, more than half of Iranian households don’t own a car!)\n" + 
			"\n" + 
			"Now imagine a fantasy world 😬; where, instead of subsidizing Cars (remember that half!), the government considers all Iranians and prevents making their financial situation even worse. 💪\n" + 
			"\n" + 
			"One of the best ways to do this is giving a quota of Gasoline (for example, 20 liters per month) for each Iranian 👥⛽. Then anyone; who, does not have a car or does not want to consume her quota can sell it to those; who, consume a lot of gasoline, in an online market, without governmental intervention. So, the one; who, sells his gasoline quota can use his gasoline subsidy to make her life better💰, and the one; who, consumes too much will realize that gasoline is not cheap and could be consumed less.\n" + 
			"\n" + 
			"You may ask how much is the financial impact of such a change?! It just takes a minute to answer few questions and EnergyYar tells you how much you’ll earn by this plan.";
	String text00 = "Get the GPTT’s plan from gptt@gptt.ir ";
	String text777 ="For more information about the GPTT’s plan enter your contact info.";
	String text1 = "Let's go...";
	String text2 = "about us";
	String text3 = "How many are in your family? 👨‍👩‍👧‍👧";
	String text4 = "Your current monthly gasoline consumption ⛽";
	String text5 = "Let’s forecast gasoline quota price in the online market after this reform ⛽";
	String text61 = "If gasoline quota is prised to the selected value, how much will your monthly consumption be? ⛽";

	String text71 = "—> Zero people family is not valid!" + "\n" + "How many are in your family?  👨‍👩‍👧‍👧";
	String text72 = "Input string was not in a correct format";
	String text73 = "click START to see how much you can earn by implementation of this reform";
	String text74 = "Thank you! Follow Energy yar on social media @DailyEconomics";
	String text001 = "Your situation after the implementation of GPTT’s plan:" + "\n" + "\n";
	String text002 = "You Current Situation:" + "\n";
	String text000 ="You have no profit and no loss";
			
	String text010 = " ⛽⛽⛽️ Currently, before the reform, your family receives  ";
	String text012 = "  toman more than its share of gasoline subsidies per month, comparing to other Iranians." + "\n";
	String text112 = " toman less than its share of gasoline subsidies per month, comparing to other Iranians." + "\n";
	String text113 = "  ⛽⛽⛽️ You don't earn any share of gasoline" + "\n";

	String text031 = "💵💵💵  Once this reform is implemented, you will get ";
	String text032 = " toman monthly" + "\n";
	String text131 = "« 🙏🏻💵💵 After the implementation of this reform, you will monthly help the poor, directly and without governmental intervention ";
	String text132 = " toman" + "\n";
	String text331 = " 💵😞😞 You dont earn any money with this consumption pattern" + "\n";

	String text041 = " 🏡🏡🏡 If after implementation of this reform, all Iranian change their gasoline consumption like you, then in a year, ";
	String text042 = " houses can be built in flooded cities in west of Iran." + "\n";
	String text043 = "😔😔😔 Unfortunatly you could not help flooded cites with this consumption pattern!" + "\n";

	SendMessage message = new SendMessage();

	public void onUpdateReceived(Update update) {
		Long chat_id = null;
		String message_text = null;
		try {
		if (update.hasMessage() && update.getMessage().hasText()) {
			 message_text = update.getMessage().getText();

				chat_id = update.getMessage().getChatId();
				//log on console	
				
				// System.out.println("usename: " + update.getMessage().getFrom().getUserName());
				// System.out.println("id: " + update.getMessage().getFrom().getId());
				// System.out.println("first: " + update.getMessage().getFrom().getFirstName());
				// System.out.println("last: " + update.getMessage().getFrom().getLastName());
				// System.out.println("caht_id: " + chat_id);

				if (message_text.equals("/start")) {
					// System.out.println(message_text);
					// try {
					message = InlineKeyboardBuilder.create(update.getMessage().getChatId()).setText(text0).row()
							.button(text1, "action1").button(text2, "action2").endRow().build();
					execute(message);
					// } catch (TelegramApiException e) {
					// e.printStackTrace();
					// }
					
					
				}else {
					Oil oil = OilHandlrer.mapOfOils.get(chat_id);

					if(oil != null && oil.getEcg() != null && oil.getEmail() == null) {
						oil.setEmail(message_text);
						message = InlineKeyboardBuilder.create(chat_id).setText(text74).row()
								.button("Let’s play back with other gasoline quota new prices ", "other").endRow()
								.build();
						sendApiMethod(message);
						energyInfoController.UpdateEnergy(oil);
						OilHandlrer.mapOfOils.remove(chat_id);

					}
					else {
					Integer nn = null;
					// try {
					nn = Integer.valueOf(message_text);
					if(nn <0 ) {
						throw new NumberFormatException();
					}
					if (oil == null) {
						message = InlineKeyboardBuilder.create(update.getMessage().getChatId())
								.setText(text73).row().button("START", "action1").endRow().build();
						execute(message);
					}
					else if (oil.getSize() == null) {
						oil.setSize(nn);
						oil.setLastUpdated(new Date().getTime());
						if (nn != 0) {
							SendMessage message = new SendMessage() // Create a message object object
									.setChatId(chat_id).setText(text4);
							execute(message);
						} else {
							SendMessage message = new SendMessage() // Create a message object object
									.setChatId(chat_id).setText(text71);
							execute(message);
						}
					} else if (oil.getEcgBefore() == null) {
						oil.setEcgBefore(nn);
						oil.setLastUpdated(new Date().getTime());
						OilHandlrer.mapOfOils.replace(chat_id, oil);
						message = InlineKeyboardBuilder.create(chat_id).setText(text5).row()
								.button("۲۳۰۰ toman", "2300").button("۳۴۰۰ toman", "3400").button("۴۵۰۰ toman", "4500")
								.endRow().build();
						sendApiMethod(message);
					} else if (oil.getPrice() == null) {
						message = InlineKeyboardBuilder.create(chat_id).setText(text5).row()
								.button("۲۳۰۰ toman", "2300").button("۳۴۰۰ toman", "3400").button("۴۵۰۰ toman", "4500")
								.endRow().build();
						sendApiMethod(message);
					} else if (oil.getEcg() == null) {
						oil.setEcg(nn);
						oil.setLastUpdated(new Date().getTime());
						OilHandlrer.mapOfOils.replace(chat_id, oil);
						StringBuilder text = new StringBuilder();
						text.append(text001);
						if((oil.getSize() *20 ) != oil.getEcg()) {
						Integer result = (oil.getSize() * 20 - oil.getEcg()) * oil.getPrice();
						if (result > 0) {
							text.append(text031);
							text.append(String.valueOf(result));
							text.append(text032);
						} else if (result < 0) {
							text.append(text131);
							text.append(String.valueOf(result * (-1)));
							text.append(text132);
						} else {
							text.append(text331);
						}
						}else {
							text.append(text000);
						}
						// System.out.println("result3: " + result);
						
												Integer result4 = null;
						if (oil.getEcgBefore() == 0) {

							result4 = (int) (31755 - 19200) * oil.getPrice();

						} else {
							double taghsim = (float) oil.getEcg() / (float) oil.getEcgBefore();
							// System.out.println("taghsim: ... " + taghsim);

							result4 = (int) (((1 - taghsim) * 31755 * 5500)
									+ (31755 * taghsim - 19200) * oil.getPrice());
							double a = ((1 - taghsim) * 31755 * 5500);
							double b = 31755 * taghsim - 19200;
							double c = oil.getPrice();
							// System.out.println("a: ... " + a);
							// System.out.println("b: ... " + b);
							// System.out.println("c: ... " + c);
							// System.out.println("result4: " + (int)(a+b*c));
						}

						if (result4 > 0) {
							text.append(text041);
							text.append(String.valueOf(result4 / 100));
							text.append(text042);
						} else {
							text.append(text043);
						}
						text.append("\n");
						text.append(text002);

						text.append("\n");
						Long result1 = Math.round((oil.getEcgBefore() - (oil.getSize() * 13.2)) * 5500);
						if (result1 > 0) {
							text.append(text010);
							//text.append(String.valueOf(oil.getSize()));
							//text.append(text011);
							text.append(String.valueOf(result1));
							text.append(text012);
						} else if (result1 < 0) {
							text.append(text010);
							//text.append(String.valueOf(oil.getSize()));
							//text.append(text011);
							text.append(String.valueOf(result1 * (-1)));
							text.append(text112);
						} else {
							text.append(text113);
						}
						message = InlineKeyboardBuilder.create(chat_id).setText(text.toString()).row()
								.button(" Let’s play back with other gasoline quota new prices ", "other").button(" Join us...", "review").endRow()
								.build();
						sendApiMethod(message);
						// store to db
						oil.setChatId(chat_id);
						energyInfoController.EnergyModel(oil, update.getMessage().getFrom().getUserName(),
								update.getMessage().getFrom().getFirstName(),
								update.getMessage().getFrom().getLastName());
					}
				}


		//	catch (Exception e) {
		//		e.printStackTrace();
		//	}
		} } if (update.hasCallbackQuery()) {
		//	try {
				// Set variables
				String call_data = update.getCallbackQuery().getData();
				long message_id = update.getCallbackQuery().getMessage().getMessageId();
				chat_id = update.getCallbackQuery().getMessage().getChatId();
				// System.out.println(call_data);

				if (call_data.equals("action1") || call_data.equals("action9") || call_data.equals("other")) {
					Oil oil = new Oil();
					oil.setLastUpdated(new Date().getTime());
					OilHandlrer.mapOfOils.put(chat_id, oil);

					SendMessage message = new SendMessage() // Create a message object object
							.setChatId(chat_id).setText(text3);
					execute(message);
				}
				////////////
				else if (call_data.equals("action2")) {
					SendMessage sendMessage = new SendMessage()
							.setChatId(update.getCallbackQuery().getMessage().getChatId()).setText(text00);
					InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
					List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
					List<InlineKeyboardButton> rowInline = new ArrayList<>();
					rowInline.add(new InlineKeyboardButton().setText("Lets' go").setCallbackData("action9"));
					rowInline.add(new InlineKeyboardButton().setText("study the plan").setCallbackData("JOIN_PREMIUM")
							.setUrl("http://gptt.ir/برنامه-پیشنهادی-اندیشکده-حکمرانی-1/"));
					rowsInline.add(rowInline);

					markupInline.setKeyboard(rowsInline);
					sendMessage.setReplyMarkup(markupInline);

					message = InlineKeyboardBuilder.create(chat_id).setText(text00).row()
							.button("Lets' go", "action9").button("study the plan", "action10").endRow().build();

					// sendMessage(sendMessage);

					// Send the message
					sendApiMethod(sendMessage);
					// execute(message);
				}
				////////////
				else if (call_data.equals("review")) {
					SendMessage message = new SendMessage() // Create a message object object
							.setChatId(chat_id).setText(text777);
										sendApiMethod(message);
				}
	
				////////////////////

				else if (call_data.equals("fam2") || call_data.equals("fam3") || call_data.equals("fam4")
						|| call_data.equals("fam5") || call_data.equals("fam6") || call_data.equals("fam7")
						|| call_data.equals("fam8") || call_data.equals("fam9") || call_data.equals("fam0")) {

					String num_data = update.getCallbackQuery().getData().substring(3, 4);
					// System.out.println("family : " + num_data);
					Oil oil = new Oil(Integer.valueOf(num_data));
					OilHandlrer.mapOfOils.put(chat_id, oil);

					SendMessage message = new SendMessage() // Create a message object object
							.setChatId(chat_id).setText(text4);
					execute(message);
				} else if (call_data.equals("2300") || call_data.equals("3400") || call_data.equals("4500")) {

					Integer price = Integer.valueOf(update.getCallbackQuery().getData());
					if (price != null) {
						Oil oil = OilHandlrer.mapOfOils.get(chat_id);
						// System.out.println("masrafi: "+oil.getEcgBefore());
						 System.out.println("price: " + price);
						oil.setPrice(price);
						oil.setLastUpdated(new Date().getTime());
						OilHandlrer.mapOfOils.replace(chat_id, oil);

						SendMessage message = new SendMessage() // Create a message object object
								.setChatId(chat_id).setText(text61);
						execute(message);
					}
				}
		
		}
		}catch (NumberFormatException e) {
			if(!update.hasCallbackQuery()) {
			System.out.println(e.getMessage());
			try {
			if(chat_id!=null) {
			Oil oil = OilHandlrer.mapOfOils.get(chat_id);
			if (oil == null) {
				message = InlineKeyboardBuilder.create(update.getMessage().getChatId())
						.setText(text73).row().button("START", "action1").endRow().build();
				execute(message);
			}else {
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText(text72);
				
					execute(message);
			}
			}
			} catch (TelegramApiException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

	public String getBotUsername() {
		return "energey_bot";
	}

	@Override
	public String getBotToken() {
		return "code";
	}
}
