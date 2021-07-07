package StepDefinition;

public enum APIResources {
	
	AddPlace("/maps/api/place/add/json"),
	GetPlace("/maps/api/place/get/json"),
	PutPlace("/maps/api/place/update/json"),
	DeletePlace("/maps/api/place/delete/json");

	APIResources(String string) {
		// TODO Auto-generated constructor stub
	}
	

}
