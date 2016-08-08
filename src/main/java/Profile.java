import java.util.HashMap;

public class Profile {
	private static HashMap<String,String> file=new HashMap<String,String>();
	
	public static void setKeyValue(String key,String value){
		
		file.put(key, value);
	}
	public static String getValue(String key){
		return file.get(key);
	}
	public static void clear(){
		file.clear();
	}
	
}
