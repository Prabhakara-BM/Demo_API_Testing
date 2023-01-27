import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Files.Payload;
import Files.ReUsableMethods;

public class Basics {

	public static void main(String[] args) {

		// Given=All input details
		// When=Submit API response
		// Then=Validate the response
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.addPlace()).when().post("/maps/api/place/add/json").then().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("Server", equalTo("Apache/2.4.41 (Ubuntu)"))
				.extract().response().asString();
		// .then().extract().response().asString();
		System.out.println("The Whole response is:" + "" + "" + response);
		JsonPath js = new JsonPath(response);
		String placeID=js.getString("place_id");
		System.out.println("The Place Id is :"+placeID);
		
		//Update Place
		
		String newAddress="70 winter walk, Africa";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		
		String getPlaceRepsonse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeID)
		.header("Content-Type","application/json")
		.when().get("/maps/api/place/get/json").then().log().all()
		.assertThat().statusCode(200).extract().response().asString();
		//JsonPath js2=new JsonPath(getPlaceRepsonse);
		JsonPath js2=ReUsableMethods.rowToJson(getPlaceRepsonse);
		String ActualAddres=js2.getString("address");
		System.out.println("The actual adress is :"+ActualAddres);
		

	}
	

}
