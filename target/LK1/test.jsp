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

<%
String token=request.getParameter("token");
System.out.println(token);

	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>

<title>Insert title here</title>
</head>
<body>
<h1>hello</h1>
<div ng-app="gettoken">
<input type="hidden" id="token" value="{{accessToken}}" ></input></div>
</body>
</html>