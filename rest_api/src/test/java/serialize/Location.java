package serialize;

public class Location {
	private String lat;
	private String lng;
	public Location(String lat2, String lng2) {
		// TODO Auto-generated constructor stub
		this.lat = lat2;
		this.lng = lng2;
	}

	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	

}
