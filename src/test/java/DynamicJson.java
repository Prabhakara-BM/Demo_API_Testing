import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;
import Files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class DynamicJson {
	
	@Test(dataProvider="BooksData")
	public void addBook(String isbn,String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response=given().log().all().header("Content-Type","application/json")
		.body(Payload.Addbook(isbn,aisle)).when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=ReUsableMethods.rowToJson(response);
		String idIs=js.getString("ID");
		System.out.println("The id is:"+idIs);
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		return new Object[][] { {"bha","816"},{"kar","098"},{"sos","249"}  };
	}
	

}
