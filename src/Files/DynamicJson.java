package Files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured. *;

public class DynamicJson {
	
	
	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, String aisle)
	{
		RestAssured.baseURI = "http://216.10.245.166";
//		String response = given().log().all().header("Content-Type", "application/json").body(Payload.AddBook())
//			.when().post("Library/Addbook.php")
//			.then().log().all().assertThat().statusCode(200)
//			.extract().response().asString();
//		
//		JsonPath js = ReusableMethods.rawToJson(response);
//		String id = js.get("ID");		//extracting ID from the response
//		System.out.println(id);
		
		
		// QQ. Sends a POST request to Library/Addbook.php.
		//Parses the response as JSON.
		//Extracts the "ID" field from the response.
		//1 Dynamically build json payload with external data inputs -- add book
		
		
		Response resp = given().header("Content-Type", "application/json").body(Payload.Addbook("bcghd", "26547")).
				when().post("Library/Addbook.php").
				then().assertThat().statusCode(200).extract().response();
		
		System.out.println("Response: " + resp.asString()); // Debug line
		
		JsonPath js = new JsonPath(resp.asString()); // Try direct use first
		String id = js.get("ID");
		System.out.println(id);
	}
	
	//parameterize the API Tests with multiple data sets
	@DataProvider(name="BooksData")
	public Object getData()
	{
		//array-collection of elements
		//multidimensional array-collection of arrays
		
		return new Object[][] {{"juykk", "9846"},{"wetprd", "68065"},{"aiddg", "34954"}};
	}
	}

