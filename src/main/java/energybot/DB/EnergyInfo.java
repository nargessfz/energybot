package energybot.DB;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class EnergyInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private Integer size;
	@Column
	private Integer current_Fuel_Usage;
	@Column
	private Integer future_Fuel_Usage;
	@Column
	private Integer selected_Price;
	@Column
	private String user_name;
	@Column
	private String name;
	@Column
	private String family;
	@Column
	private Integer chat_id;
	@Column
	private Date date;
	@Column
	private Long time;
	@Column
	private String email_phone;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getCurrent_Fuel_Usage() {
		return current_Fuel_Usage;
	}
	public void setCurrent_Fuel_Usage(Integer current_Fuel_Usage) {
		this.current_Fuel_Usage = current_Fuel_Usage;
	}
	public Integer getFuture_Fuel_Usage() {
		return future_Fuel_Usage;
	}
	public void setFuture_Fuel_Usage(Integer future_Fuel_Usage) {
		this.future_Fuel_Usage = future_Fuel_Usage;
	}
	public Integer getSelected_Price() {
		return selected_Price;
	}
	public void setSelected_Price(Integer selected_Price) {
		this.selected_Price = selected_Price;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public Integer getChat_id() {
		return chat_id;
	}
	public void setChat_id(Integer chat_id) {
		this.chat_id = chat_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getEmail_phone() {
		return email_phone;
	}
	public void setEmail_phone(String email_phone) {
		this.email_phone = email_phone;
	}


}
