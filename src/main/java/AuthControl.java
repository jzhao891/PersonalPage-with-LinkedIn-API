

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class AuthControl
 */
@WebServlet("/AuthControl")
public class AuthControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String API_KEY = "77by2ysae48o8n";
	private static final String API_SECRET = "mBEDuI2c19qeQPlJ";
	private static final String REDIRECT_URI="http://localhost:8080/LK1/mylinkedin.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthControl() {
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url="https://www.linkedin.com/uas/oauth2/authorization?response_type=code&client_id="+API_KEY
				+"&redirect_uri="+REDIRECT_URI+"&state=6789Jkl&scope=r_basicprofile%20r_emailaddress%20w_share";
		System.out.print("execute");
		//response.sendRedirect(url);
		PrintWriter out = response.getWriter();
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        
        Gson gson = new Gson(); 
        JsonObject myObj = new JsonObject();
        JsonElement Obj=gson.toJsonTree(url);
        
        myObj.add("url", Obj);
        out.println(myObj.toString());
        out.close();
	}

}
