import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;


import org.testng.Assert;

import Files.Payload;
import Files.ReusableMethods;

public class Basics {

	public static void main(String[] args) {
		
		//validate if add place api is working as expected
		//Add place->Update place with new address->Get place to validate if new address is present in response
		
		//given- all input details
		//when- submit the api. resource and http method
		//then- validate the response
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//we are chaining up with given method. whatever we have in api
		//log all(input & output) method will log everything in the console whatever is being sent and how the response generated
		
		//add/create place
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "Application/json").body(Payload.AddPlace())
		//.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\priya\\Downloads\\RahulShetty\\Library+API.postman_collection.json")))  // - convert json to byte
		.when().post("maps/api/place/add/json")      //resource will be concatenating with base uri
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		//extracted the whole response
		System.out.println(response);
		
		//to parse json to extract place id from the response
		JsonPath js = new JsonPath(response);  //for parsing json
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		
		//update place
		String newAddress = "70 Summer walk, USA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "Application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		//get place
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", "e8cb47270c1577f9106a10ab53d44b5a")
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1 = ReusableMethods.rawToJson(getPlaceResponse);
		//JsonPath js1 = new JsonPath(getPlaceResponse);
		String ActualResponse = js1.getString("address");
		System.out.println(ActualResponse);
		Assert.assertEquals(ActualResponse, newAddress);

	}

}
