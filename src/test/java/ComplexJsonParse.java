import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js=new JsonPath(Payload.coursePrize());
		
		//1. Print No of courses returned by API
		
		int count=js.getInt("courses.size()");
		System.out.println("The count is :"+count);
		
		//2.Print Purchase Amount
		int totalAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println("The total purchase amount is :"+totalAmount);
		
		//3. Print Title of the first course
		String nameOfFirstCourse=js.getString("courses[0].title");
		System.out.println("The name of fist course is :"+nameOfFirstCourse);
		
		//4. Print All course titles and their respective Prices
		for(int i=0;i<count;i++) {
			String courseTitles=js.getString("courses["+i+"].title");
			System.out.println("The courses tiltes are :"+courseTitles);
			int copies=js.getInt("courses["+i+"].copies");
			System.out.println(copies);
		}
		
		System.out.println("Print no of copies sold by RPA Course");
		for(int i=0;i<count;i++) {
			String courseTitles=js.getString("courses["+i+"].title");
			if(courseTitles.equalsIgnoreCase("RPA")){
				int RPAprice=js.getInt("courses["+i+"].price");
				System.out.println("The RPA price is :"+" "+RPAprice);
				break;
				
				
			}
		}

	}

}
