package volley;

//This class is for storing all URLs as a model of URLs

public class Config
{
	//Base URL
	private static String base_URL = "http://10.0.2.2:90/";		//Default configuration for WAMP - 80 is default port for WAMP and 10.0.2.2 is localhost IP in Android Emulator
	// Server url POST
	public static String URL = "http://skripsi13.com/sisfordik/RestApi/";
	//public static String URL = "http://192.168.1.100/sisfordik/RestApi/";
	public static String URL2 = "http://167.205.7.227:10/sisfordik/ApiSiaterNew/";

	//params in API
	public static String TAG = "tag";
	public static String TAG_LOGIN = "login";

	public static String username = "username";
	public static String password = "password";

	public static String Jaringan_error = "Mohon Periksa Koneksi Internet Anda !!";

}