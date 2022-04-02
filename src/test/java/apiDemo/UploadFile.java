package apiDemo;

import org.testng.annotations.Test;
import commonUtilities.ApiResources;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class UploadFile {
	String uploadFile =ApiResources.uploadFileResource();
String fileContent="{\"features\":[{\"attributes\":{\"ObjectId\":1,\"address\":\"1214 Newgate Mall,Ogden,UT,84401\",\"street\":\"1214 Newgate Mall\",\"city\":\"Ogden\",\"zip\":\"84401\",\"state\":\"UT\",\"X\":11,\"Y\":11},\"geometry\":{\"x\":null,\"y\":null}},{\"attributes\":{\"ObjectId\":2,\"address\":\"Lythgoe Real Estate, Inc., 289 24th,Ogden,UT,84401\",\"street\":\"Lythgoe Real Estate, Inc., 289 24th\",\"city\":\"Ogden\",\"zip\":\"84401\",\"state\":\"UT\",\"X\":11,\"Y\":11},\"geometry\":{\"x\":null,\"y\":null}}],\"spatialReference\":{\"wkid\":102100},\"GeometryTypeEnum\":2,\"geometryType\":\"esriGeometryPoint\",\"fieldAliases\":{},\"fields\":[{\"name\":\"ObjectId\",\"type\":\"esriFieldTypeOID\",\"alias\":\"ObjectId\",\"length\":0},{\"name\":\"address\",\"type\":\"esriFieldTypeString\",\"alias\":\"address\",\"length\":0},{\"name\":\"street\",\"type\":\"esriFieldTypeString\",\"alias\":\"street\",\"length\":0},{\"name\":\"city\",\"type\":\"esriFieldTypeString\",\"alias\":\"city\",\"length\":0},{\"name\":\"state\",\"type\":\"esriFieldTypeString\",\"alias\":\"state\",\"length\":0},{\"name\":\"zip\",\"type\":\"esriFieldTypeString\",\"alias\":\"zip\",\"length\":0},{\"name\":\"X\",\"type\":\"esriFieldTypeInteger\",\"alias\":\"X\",\"length\":0},{\"name\":\"Y\",\"type\":\"esriFieldTypeInteger\",\"alias\":\"Y\",\"length\":0}],\"displayFieldName\":\"ObjectId\",\"exceededTransferLimit\":false}";
//String fileContent="ABC";
	@Test
	public void uploadFile() {
		RestAssured.baseURI="http://172.30.5.65:2003"; 
		Response fileUploadResponse=given().log().all().contentType("multipart/form-data")
				
				.header("spatialiticsuser","u")
				.multiPart("userid","u")
				.multiPart("isgeocoded",false)
				.multiPart("x","X")
				.multiPart("y","Y")
				.multiPart("issingleline",false)
				.multiPart("address","address")
				.multiPart("street","street")
				.multiPart("city","city")
				.multiPart("state","state")
				.multiPart("zip","zip")
				.multiPart("datefield","date")
				.multiPart("layerName","AVL")
				.multiPart("layersymbol","abc")
				.multiPart("heatmaptheme","red")
				.multiPart("heatmaplowvalue","1")
				.multiPart("heatmaphighvalue","5")
				.multiPart("clustercolor","red")
				.multiPart("file", fileContent)
				.when().post(uploadFile)
				.then().
				extract().response();
		System.out.println(fileUploadResponse.prettyPrint());
		

	}
}
