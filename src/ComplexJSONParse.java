import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJSONParse {

	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(Payload.CoursePrice());
		
		//1 print no of courses returned by API
		
		int count = js.getInt("courses.size()");  //size() use only for array type json
		System.out.println(count);
		
		//2 print purchase amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//3 print title of the first course
		String Firsttitle = js.getString("courses[0].title");
		System.out.println(Firsttitle);
		
		//4 print all course titles and their respective prices
		for(int i=0; i<count; i++) {	
		String courseTitles = js.get("courses["+i+"].title");					//+i+ concatenate operator tells java it is sending variable
		System.out.println(js.get("courses["+i+"].price").toString());  		//when you dont want to store it in a var and print in another line just add syso and at the end add toSting() so that syso will take it as string and print on console
		System.out.println(courseTitles);
		}
		
		//5 print no of copies sold by RPA course - simply printing without check
//		int RPACopies = js.getInt("courses[2].copies");
//		System.out.println(RPACopies);
		
		//5 with code optimization - by iterating in loop
		System.out.println("Number of copies sold by RPA course");
		
		for(int i=0; i<count; i++) {
			
			String courseTitles = js.get("courses["+i+"].title");
			if(courseTitles.equalsIgnoreCase("RPA")){
				
				int copies = js.getInt("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}	

	}

}
