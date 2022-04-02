package apiDemo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import commonUtilities.ApiResources;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicPayload {

	@Test(dataProvider="booksData")
	public void addBook()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().header("Content-Type","application/json")
		.body(Payload.addBookPayload("4376642","cbd"))
		.when().post(ApiResources.addBook())
		.then().log().all().statusCode(200).extract().asString();
		//System.out.println("Response body is:"+response);
		JsonPath js=new JsonPath(response);
		String id=js.get("ID");
		System.out.println(id);
	}
	
	@DataProvider(name="booksData")
	public Object[][] getData()
	{
		return new Object[][] {{"fdfdf","354356"},{"wgghgh","895453"},{"dgd","896211"}};
	}
}
