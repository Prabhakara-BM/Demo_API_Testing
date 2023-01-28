import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class SumValidations {
//	6. Verify if Sum of all Course prices matches with Purchase Amount
	@Test
	public void sumAllCourses() {
		int sum=0;
		JsonPath js=new JsonPath(Payload.coursePrize());
		int count=js.getInt("courses.size()");
		for(int i=0;i<count;i++) {
			int prices=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			int total=prices * copies;
			System.out.println(total);
			sum=sum+total;
		}
		System.out.println("The total sum is :"+ " "+sum);
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println("The purchase amount is :"+purchaseAmount);
		
	}

}
