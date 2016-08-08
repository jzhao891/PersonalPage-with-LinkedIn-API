

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
/**
 * Servlet implementation class GetImg
 */
@WebServlet("/GetImg")
public class GetImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetImg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
        System.out.println("doget");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("dopost");
        PrintWriter out = response.getWriter();
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        
        Gson gson = new Gson(); 
        JsonObject myObj = new JsonObject();
        /////////read profile from json file
        String imgurl=null;
        /*File f=new File("profile.json");
        FileOutputStream fout = new FileOutputStream(f);
        new PrintStream(fout).println("23456789wertyuio");
        fout.close();
        System.out.println (f.getAbsolutePath());*/
        FileInputStream file = new FileInputStream("profile.json");
        InputStreamReader is = new InputStreamReader(file);
        BufferedReader br=new BufferedReader(is);
        StringBuilder sb=new StringBuilder();
        String line=null;
        while((line=br.readLine())!=null){
        	sb.append(line);//if need "\n"
        }
        br.close();
        JSONObject JsonObject=new JSONObject(sb.toString());
        JSONObject child=JsonObject.getJSONObject("pictureUrls");
        imgurl=child.getJSONArray("values").getString(0);
        ///////////store basic information from file to memory
        Profile.clear();
        Profile.setKeyValue("pictureUrl",imgurl);
        Profile.setKeyValue("firstName", JsonObject.getString("firstName"));
        Profile.setKeyValue("lastName", JsonObject.getString("lastName"));
        Profile.setKeyValue("headline", JsonObject.getString("headline"));
        Profile.setKeyValue("id", JsonObject.getString("id"));
        Profile.setKeyValue("industry", JsonObject.getString("industry"));
        Profile.setKeyValue("summary", JsonObject.getString("summary"));
        //deal with location
        child=JsonObject.getJSONObject("location");
        Profile.setKeyValue("location", child.getString("name"));
        //deal with profile URL
        Profile.setKeyValue("publicProfileUrl", JsonObject.getString("publicProfileUrl"));
        //////////output json to front
        JsonElement urlObj = gson.toJsonTree(imgurl);
        /*if(imgurl == null){
            myObj.addProperty("success", false);
        }
        else {
            myObj.addProperty("success", true);
        }*/
        myObj.add("pictureUrl", urlObj);
        out.println(myObj.toString());
        out.close();
	}

}
