package commonUtilities;

import io.restassured.path.json.JsonPath;

public class Common_utilities {
	
	public static JsonPath rawToJson(String response)
	{
		JsonPath js=new JsonPath(response);
		return js;
	}

}
