import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class BugTest {

	public static void main(String[] args) {
		
		
		RestAssured.baseURI = "https://pratibhasaurav11.atlassian.net/";
		
		//creating bug in jeera
		String createIssue = given().header("Content-Type" , "application/json").header("Authorization" , "Basic QVRBVFQzeEZmR0YwSms5Sk9CRktaZ1FyaU1fNzBYT1BRTXZvSWgxeC1pV2kwZjMyWTRtVTJxdFpwM09UUm9vNlNHd2hrbnBsaWpMcmloalNuS0dZbGh4YzlqZW5ySFRBV1BickVpV2ZiWFJ3MkZ3UkZPazN2N29WY3hJdXhNaHJNVU01MmZ5Q2ctNU14ei1CZE5wVndsYVFjVDVyTktUTHBqamVwLUYzLW91Wjd3Tk0wVUNLdnZVPTEwQkE5RDkx\r\n"+ "\r\n"+ "")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Links not working - automation\",\r\n"
				+ "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Task\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}")
		.log().all().post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js = new JsonPath(createIssue);
		String issueID = js.getString("id");
		System.out.println(issueID);
		
		//attaching ss in the bug
		given()
		.pathParam("key" , issueID)
		.header("X-Atlassian-Token" , "no-check")
		.header("Authorization" , "Basic QVRBVFQzeEZmR0YwSms5Sk9CRktaZ1FyaU1fNzBYT1BRTXZvSWgxeC1pV2kwZjMyWTRtVTJxdFpwM09UUm9vNlNHd2hrbnBsaWpMcmloalNuS0dZbGh4YzlqZW5ySFRBV1BickVpV2ZiWFJ3MkZ3UkZPazN2N29WY3hJdXhNaHJNVU01MmZ5Q2ctNU14ei1CZE5wVndsYVFjVDVyTktUTHBqamVwLUYzLW91Wjd3Tk0wVUNLdnZVPTEwQkE5RDkx\r\n"+ "\r\n"+ "")
		.multiPart("file",new File("C:\\Users\\priya\\Downloads\\apiAutomate\\Screenshot 2025-10-01 235146.png"))     //for file upload we need to use multiPart and create new file object inside it as arg. if we have text as from param then it can be written as similar to string in double quotes
		.log().all()
		.post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
	}

}
