

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/Comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//data from request is {title:title,desc:desc,url:url,text:text}
		
		//get data and turn to normal jsonString
		/*
		{
  "comment": "Check out developer.linkedin.com!",
  "content": {
    "title": "LinkedIn Developers Resources",
    "description": "Leverage LinkedIn's APIs to maximize engagement",
    "submitted-url": "https://developer.linkedin.com",
    "submitted-image-url": "https://example.com/logo.png"
  },
  "visibility": {
    "code": "anyone"
  }
}
		 * */
		if(request.getContentLength()==0){
			PrintWriter out = response.getWriter();
	        response.setContentType("application/json; charset=UTF-8");
	        response.setHeader("Cache-control", "no-cache, no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setHeader("Expires", "-1");
	        out.println("{}");
	        out.close();
		}
		StringBuilder sb0=new StringBuilder();
		sb0.append("{\n");
		sb0.append("\"content\":{\n");
		sb0.append("\"title\":\""+request.getParameter("title")+"\",\n");
		sb0.append("\"description\":\""+request.getParameter("desc")+"\",\n");
		sb0.append("\"submitted-url\":\""+request.getParameter("url")+"\"\n");
		sb0.append("},\n");
		sb0.append("\"comment\":\""+request.getParameter("text")+"\",\n");
		sb0.append("\"visibility\": {\n\"code\": \"anyone\"\n}\n");
		sb0.append("}\n");
		System.out.println(sb0);
		//do post to API
		JSONObject jsonObject=null;
		String accessToken=null;//get accesstoken
		FileInputStream file = new FileInputStream("token.dat");
        InputStreamReader is = new InputStreamReader(file);
        BufferedReader br=new BufferedReader(is);
        String line=null;
        if((line=br.readLine())!=null){
        	accessToken=line;
        }
		String api="https://api.linkedin.com/v1/people/~/shares?oauth2_access_token="+accessToken+"&format=json";
		br.close();
		HttpClient client=new HttpClient();
		PostMethod post=new PostMethod(api);
		post.addRequestHeader("Content-Type", "application/json");
		post.addRequestHeader("x-li-format", "json");
		StringRequestEntity re=new StringRequestEntity(sb0.toString(),"application/json","UTF-8");
		post.setRequestEntity(re);
		try{
			int status=client.executeMethod(post);
			if(status==201){
				byte[] rp=post.getResponseBody();
				InputStream rpInput=new ByteArrayInputStream(rp);
				StringBuilder sb=new StringBuilder();
				br=new BufferedReader(new InputStreamReader(rpInput,"UTF-8"));
				line=null;
				while((line=br.readLine())!=null){
					System.out.println(line);
					sb.append(line);
				}
				jsonObject=new JSONObject(sb.toString());
				
			}
			else{
				System.out.println(post.getResponseBodyAsString(100));
				throw new RuntimeException("response state is " + status);
				
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			post.releaseConnection();
		}
		//return response
		PrintWriter out = response.getWriter();
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        out.println(jsonObject.toString());
        out.close();
		
	}

}
