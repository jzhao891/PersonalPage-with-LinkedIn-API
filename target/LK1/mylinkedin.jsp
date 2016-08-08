<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import=" org.apache.commons.httpclient.HttpClient"%>
<%@ page import=" org.apache.commons.httpclient.HttpStatus"%>
<%@ page import=" org.apache.commons.httpclient.methods.GetMethod"%>
<%@ page import=" org.apache.commons.httpclient.params.HttpMethodParams"%>
<%@ page import=" org.apache.http.message.BasicNameValuePair"%>
<%@ page import=" org.apache.commons.httpclient.NameValuePair"%>
<%@ page import=" org.apache.commons.httpclient.methods.PostMethod"%>
<%@ page import=" org.json.*"%>
<%@ page import=" java.util.List"%>
<%@ page import=" java.util.ArrayList"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.io.ByteArrayInputStream"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileWriter"%>
<%@ page import="java.io.IOException"%>


<%
	FileWriter fw=null;
	////////get accessToken
	String code=request.getParameter("code");
	String accessToken=null;
	int expiresIn=0;
	
	String url="https://www.linkedin.com/uas/oauth2/accessToken";
	HttpClient client=new HttpClient();
	client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
	
	PostMethod postmethod=new PostMethod(url);
	String[] paras={"grant_type","code","redirect_uri","client_id","client_secret"};
	String[] values={"authorization_code",code,"http://localhost:8080/LK1/mylinkedin.jsp","77by2ysae48o8n","mBEDuI2c19qeQPlJ"};
	NameValuePair[] urlparameters=new NameValuePair[5];
	for(int i=0;i<5;i++){
		urlparameters[i]=new NameValuePair(paras[i], values[i]); 
	}
	
	postmethod.addParameters(urlparameters);
	try{
		int status=client.executeMethod(postmethod);
		if(status==200){
			byte[] rp=postmethod.getResponseBody();
			InputStream rpInput = new ByteArrayInputStream(rp);
			StringBuilder sb=new StringBuilder();
			JSONObject JsonObject=null;
			BufferedReader br=new BufferedReader(new InputStreamReader(rpInput,"UTF-8"));
			String line=null;
			while((line=br.readLine())!=null){
				System.out.println(line);
				sb.append(line+"/n");
			}
			JsonObject=new JSONObject(sb.toString());
			accessToken=JsonObject.getString("access_token");
			try {
				fw=new FileWriter(new File("token.dat"));
				fw.append(accessToken);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			fw.close();
			System.out.println(accessToken);
			//expiresIn=JsonObject.getInt("expireds_in");
		}
		else{
			throw new RuntimeException("response state is " + status);
		}
		
	}catch(Exception e){
		throw new RuntimeException(e);
	}finally{
		postmethod.releaseConnection();
	}
	////////////////////basic information
	String name=null;
	String headline=null;
	String profileUrl=null;
	JSONObject JsonObject=null;//profile.json
	InputStream rpInput=null;
	StringBuilder sb=new StringBuilder();;
	BufferedReader br=null;
	///////////////////
	HttpClient client2=new HttpClient();
	String picUrl=null;
	client2.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
	url="https://api.linkedin.com/v1/people/~:(id,picture-urls::(original),first-name,last-name,headline,location,industry,site-standard-profile-request)?oauth2_access_token="+accessToken+"&format=json";
	GetMethod get=new GetMethod(url);
	try{
		int status=client2.executeMethod(get);
		if(status==200){
			byte[] rp=get.getResponseBody();
			rpInput = new ByteArrayInputStream(rp);
			br=new BufferedReader(new InputStreamReader(rpInput,"UTF-8"));
			String line=null;
			//JsonObject=new JSONObject(sb.toString());
			try {
				fw=new FileWriter(new File("profile.json"));
				while((line=br.readLine())!=null){
					System.out.println(line);
					sb.append(line+"\n");
					fw.append(line+"\n");
				}
				
			} catch (IOException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fw.close();
		}
	}catch(Exception e){
		throw new RuntimeException(e);
	}finally{
		postmethod.releaseConnection();
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/mystyle.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="js/myjquery.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>

<title>Insert title here</title>
</head>
<body style="background-color:#D3D3D3;">
<div class="container-head">
      <table><tr>
        <td class="td-1"></td>
        <td class="td-1" style="text-align:center;"><a href="mylinkedin.html"><img src="image/icon.png" alt="linkein" width="70"></a>
        </td>
        <td class="td-6"></td>

        <td class="td-2" style="vertical-align:bottom; text-align:right;"><nav class="nav-menu">
          <ul>
            <li><a title="SignOut" class="li-Sign" href="index.html">Sign Out</a></li>
          </ul>
        </nav></td></tr>
        </table>
</div>
<div id="content" ng-app="linkedin" ng-directive="iframeSetDimensionsOnload">


<table>
  <tr>
    <td class="td-1">1</td>
    <td class="td-4"><div id="myprofile">
    <table>
    	<tr><td class="td-3" rowspan="2"><img alt="photo" ng-src="{{imageUrl}}"></td><td class="td-7"></td></tr>
    	<tr><td class="td-7"></td></tr>
    </table>
    </div></td>
    <td class="td-4" rowspan="2"  style="vertical-align:top; text-align:right;">
      <div id="network"><iframe scrolling="none" frameborder="0" style="height:100%;width:100%" id="mynet" name="mynet" src="network.html">${accessToken}</iframe></div></td>
    <td class="td-1">4</td>
  </tr>
  <tr>
    <td class="td-1">5</td>
    <td class="td-4">
      <div id="share"><iframe ng-model="linkedin" scrolling="none" frameborder="0" style="width:100%"  id="sharing" name="sharing" src="index.html" iframe-set-dimensions-onload>${accessToken}</iframe></div></td>
    <td class="td-1">7</td>
  </tr>
<iframe  style="width:100%" scrolling="none" frameborder="0" id="profile" name="profile" src="test.jsp">
    	
   	</iframe>

</table>


</div>


<script>
var c =document.getElementById("profile");
var iframeContentWindow=$("#profile")[0].contentWindow;
var iframeDom=$("#profile")[0].contentWindow.document;
var target=$(iframeDom).find("#token");
var iframeAngular=iframeContentWindow.angular;
var iframeScope=iframeAngular.element("#token").scope();
iframeScope.parentcall();
iframeContentWindow.angular.element("#token").scope().accessToken=c;

</script>
<script>
var linkedin=angular.module("linkedin",[]);
linkedin.directive('iframeSetDimensionsOnload', [function(){
return {
    restrict: 'A',
    link: function(scope, element, attrs){
        element.on('load', function(){
            /* Set the dimensions here,
               I think that you were trying to do something like this: */
               var iFrameHeight = element[0].contentWindow.document.body.scrollHeight + 'px';
               element.css('height', iFrameHeight);
        })
    }
}}]);
</script>
</body>

</html>