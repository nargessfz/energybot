package energybot.Controller;

import java.util.Date;

import energybot.DB.EnergyDAO;
import energybot.DB.EnergyInfo;
import energybot.energyBot.Oil;

public class EnergyInfoController {
	 private static EnergyInfoController energyInfoController = new EnergyInfoController(); 
	  private EnergyInfoController() { }
	   
	   public static EnergyInfoController getInstance() {
	      return energyInfoController;
	   }
    private static EnergyDAO energyDAO = EnergyDAO.getInstance();

	public void EnergyModel(Oil oil, String userName, String name, String family) {
	try {

		EnergyInfo energyInfo = new EnergyInfo();
		energyInfo.setChat_id(oil.getChatId().intValue());
		energyInfo.setFuture_Fuel_Usage(oil.getEcg());
		energyInfo.setCurrent_Fuel_Usage(oil.getEcgBefore());
		energyInfo.setSelected_Price(oil.getPrice());
		energyInfo.setSize(oil.getSize());
		energyInfo.setUser_name(userName);
		energyInfo.setName(name);
		energyInfo.setFamily(family);
		energyInfo.setDate(new Date());
		energyInfo.setTime(new Date().getTime());
		energyDAO.saveEnergyInfo(energyInfo);
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	public void UpdateEnergy(Oil oil) {
		EnergyInfo energyInfo = new EnergyInfo();
		energyInfo.setChat_id(oil.getChatId().intValue());
		energyInfo.setEmail_phone(oil.getEmail());
		energyDAO.updateEnergyInfo(energyInfo);

	}
}
