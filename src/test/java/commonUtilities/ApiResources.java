package commonUtilities;

public class ApiResources {
	public static String uploadFileResource(){
		String apiResource="/DataService/uploadfile";
		return apiResource;
	}
	
	public static String addPlace(){
		String apiResource="/maps/api/place/add/json";
		return apiResource;
	}
	
	public static String updatePlace(){
		String apiResource="/maps/api/place/update/json";
		return apiResource;
	}
	
	public static String getPlace(){
		String apiResource="/maps/api/place/get/json";
		return apiResource;
}
	public static String addBook(){
		String apiResource="/Library/Addbook.php";
		return apiResource;
}
}
