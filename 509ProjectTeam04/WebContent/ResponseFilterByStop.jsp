<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entity.Flight, java.util.*, entity.Line, functions.manageLineData, timeRelated.TimeRelated" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Response to "selected flight" requested by sort and filter page</title>
</head>
<body>
<% 
String returnDay = (String)session.getAttribute("returnDay");
String param = request.getParameter("param");
out.println(returnDay+"<br>");
out.println(request.getParameter("param")+"<br>");
if(returnDay==null) { 
	request.getRequestDispatcher("FilterByStop.jsp").forward(request, response);}
else if(param != null){  
    request.getRequestDispatcher("FilterByStopOutbound.jsp").forward(request, response);}     
else {
	request.getRequestDispatcher("FilterByStopInbound.jsp").forward(request, response);}
     
%>
</body>
</html>