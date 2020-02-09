package energybot.energyBot;

public class Oil {

	private Integer size;
	private Integer ecgBefore;
	private Integer ecg;
	private Integer price;
	private Long chatId;
	private Long lastUpdated;
	private String email;

	public Oil() {
		super();
	}
	public Oil(Integer size) {
		super();
		this.size = size;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getEcgBefore() {
		return ecgBefore;
	}
	public void setEcgBefore(Integer ecgBefore) {
		this.ecgBefore = ecgBefore;
	}
	public Integer getEcg() {
		return ecg;
	}
	public void setEcg(Integer ecg) {
		this.ecg = ecg;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Long getChatId() {
		return chatId;
	}
	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}
	public Long getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
