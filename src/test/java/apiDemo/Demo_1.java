package apiDemo;

import org.testng.Assert;
import org.testng.annotations.Test;
import commonUtilities.ApiResources;
import commonUtilities.Common_utilities;
import files.Payload;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Demo_1 {
	public static String  placeId;
	public static String newAddress="Summer Walk Africa";
	String baseURI=RestAssured.baseURI="https://rahulshettyacademy.com/";
	@Test(priority=1,enabled=false)
	public void addPlaceAPI()
	{
		//Specify BaseURI

		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(Payload.addPlace())
				.when().post(ApiResources.addPlace())
				.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server","Apache/2.4.18 (Ubuntu)").extract().asString();
		System.out.println(response);

		JsonPath js=new JsonPath(response);
		placeId=js.get("place_id");
		System.out.println("Place Id is:"+placeId);
	}

	//Update place

	@Test(priority=2,enabled=false)
	void updatePlaceAPI()
	{
		String responseUpdatePlace=given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type","application/json")
				.body(Payload.updatePlacePayload())
				.when().put(ApiResources.updatePlace())
				.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().asString();
		System.out.println("Update place API response...:"+responseUpdatePlace);
	}

	//get place api
	@Test(priority=3,enabled=false)
	public void getlocation() {
		String responseGetLocation=given().queryParam("key", "qaclick123").queryParam("place_id",placeId)
				.when().get(ApiResources.getPlace())
				.then().log().all().assertThat().statusCode(200).extract().asString();


		JsonPath js1=Common_utilities.rawToJson(responseGetLocation);
		String actualAddress=js1.getString("address");

		System.out.println("actual address is:"+actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
	}
	@Test(priority=4,enabled=false)
	public void complexJsonParse() {
		JsonPath js2=new JsonPath(Payload.coursePrice());
		int noOfCources=js2.get("courses.size()");
		System.out.println(noOfCources);
		int purchaseAmnt=js2.get("dashboard.purchaseAmount");
		System.out.println("Purcahse amout :"+purchaseAmnt);
		String titleOfFirstCourse=js2.get("courses[0].title");
		System.out.println(titleOfFirstCourse);
		String titleOfThirdCourse=js2.get("courses[2].title");
		System.out.println(titleOfThirdCourse);

		for(int i=0;i<noOfCources;i++)
		{
			String courseTitles=js2.get("courses["+i+"].title");
			System.out.println(courseTitles);
			int coursesPrice=js2.get("courses["+i+"].price");
			System.out.println(coursesPrice);

		}

		//Print number of copies by RPA course
		for(int i=0;i<noOfCources;i++)
		{
			String courseTitles=js2.get("courses["+i+"].title");
			if(courseTitles.equalsIgnoreCase("RPA"))
			{
				//return copies sold
				int copies = js2.get("courses["+i+"].copies");
				System.out.println("Copies sold by RPA:"+copies);
				break;
			}
		}
	}

	@Test(priority=5,enabled=true)
	public void sumValidation() {
		JsonPath js2=new JsonPath(Payload.coursePrice());
		int noOfCources=js2.get("courses.size()");
		System.out.println(noOfCources);
		int sum=0;
		for(int i=0;i<noOfCources;i++)
		{
			int coursePrices=js2.get("courses["+i+"].price");
			int coursecopies=js2.get("courses["+i+"].copies");
			int amount=coursePrices*coursecopies;
			sum=sum+amount;
		}
		System.out.println("total courses prices.."+sum);
		int purchaseAmt=js2.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmt);




	}
}