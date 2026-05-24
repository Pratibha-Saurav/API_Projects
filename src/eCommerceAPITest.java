import java.io.File;

import org.testng.Assert;

import POJO.loginRequest;
import POJO.loginResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class eCommerceAPITest {

	public static void main(String[] args) {

		//builder object used to configure request settings, baseuri stored, payload is in json, build to complete the builder
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		
		POJO.loginRequest loginRequest = new loginRequest();
		loginRequest.setUserEmail("Pratibhasaurav11@gmail.com");
		loginRequest.setUserPassword("Pratibha11@");
		
		//Bypass SSL certificate
		RequestSpecification reqLogin = given().relaxedHTTPSValidation().log().all().spec(req).body(loginRequest);
		loginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().log().all().extract().response().as(loginResponse.class);
		System.out.println(loginResponse.getToken());
		//System.out.println(loginResponse.getMessage());
		System.out.println(loginResponse.getUserId());
		
		RequestSpecification addProductBasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization", "token").build();
		RequestSpecification reqAddProduct = given().log().all().spec(addProductBasereq)
		.param("productName", "qwerty")
		.param("productAddedBy", "userId")
		.param("productPrice", "11500")
		.param("productCategory", "fashion")
		.param("productSubCategory", "shirts")
		.param("productDescription", "Addidas Originals")
		.param("productFor", "women")
		.multiPart("productImage", new File("\"C:\\Users\\priya\\Downloads\\Screenshot 2026-05-03 231306.png"));
		
		String addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
		JsonPath js = new JsonPath(addProductResponse);
		String productId = js.get("addProductResponse");
		
		RequestSpecification deleteProdBasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization", "token").setContentType(ContentType.JSON).build();
		RequestSpecification deleteProdReq = given().log().all().spec(deleteProdBasereq).pathParam("productId", productId);
		String deleteProductResponse = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().extract().response().asString();
		
		JsonPath js1 = new JsonPath(deleteProductResponse);
		Assert.assertEquals("Product Deleted Successfully", js1.get("message"));
	}

	private static RequestSpecification given() {
		// TODO Auto-generated method stub
		return null;
	}




	}


	


