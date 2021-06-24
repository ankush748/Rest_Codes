package files;

public class Update_place {
	
	public static String update(String place_id,String address) {
		return "{\r\n"
				+ "\"place_id\":"+"\""+place_id+"\",\r\n"
				+ "\"address\":\""+address+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}";
	}

}
