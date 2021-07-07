package StepDefinition;

import java.util.ArrayList;
import java.util.List;

import serialize.Location;
import serialize.Place;

public class TestDataBuild {
	
	public static Object DataBuilder(Place place1,String name,String accuracy,String phone) {
		List<String> types=new ArrayList<String>();
		types.add("lion park");
		types.add("shop");
		place1.setAddress("29, side layout, cohen 09");
		place1.setAccuracy(accuracy);
		place1.setPhone_number(phone);
		place1.setLanguage("French");
		Location loc = new Location();
		loc.setLat("-38.383494");
		loc.setLng("88.383494");
		place1.setLocation(loc);
		place1.setWebsite("http://google.com");
		place1.setName(name);
		place1.setTypes(types);
		return place1;
	}

}
