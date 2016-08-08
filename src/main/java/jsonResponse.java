import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.json.JSONObject; 

public class jsonResponse {
	public JSONObject getJSONObject(byte[] rp) throws IOException{
		InputStream rpInput = new ByteArrayInputStream(rp);
		StringBuilder sb=new StringBuilder();
		JSONObject JsonObject=null;
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(rpInput,"UTF-8"));
			String line=null;
			while((line=br.readLine())!=null){
				sb.append(line+"/n");
			}
			JsonObject=new JSONObject(sb.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JsonObject;
	}
}
