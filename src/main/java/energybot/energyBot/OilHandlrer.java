package energybot.energyBot;

import java.util.HashMap;

public class OilHandlrer {
	public static HashMap<Long, Oil> mapOfOils = new HashMap<Long, Oil>() ;

	private static OilHandlrer instance = null; 
    protected OilHandlrer() {}
    public static OilHandlrer getInstance() {
        if (instance == null) {
            instance = new OilHandlrer(); }
        return instance;
    }
	
}
