package serialize;

import java.util.List;

public class Place {
	
	private Location location;
	private String accuracy;
	private String name;
	private String phone_number;
	private String address;
	private List<String> types;
	private String website;
	private String language;

//	public Place(Location location2, String accuracy2, String name2, String phone_number2, String address2,
//			List<String> types2, String website2, String language2) {
//		// TODO Auto-generated constructor stub
//		this.location = location2;
//		this.accuracy = accuracy2;
//		this.name = name2;
//		this.phone_number = phone_number2;
//		this.address = address2;
//		this.types = types2;
//		this.website = website2;
//		this.language = language2;
//	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	

}
