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
	request.getRequestDispatcher("DoConfirm.jsp").forward(request, response);}
else if(param != null){ 
	//ArrayList<Line> lineforSearchInbound1 = (ArrayList<Line>) session.getAttribute("response1");
    //ArrayList<Line> lineforSearchInbound2 = (ArrayList<Line>) session.getAttribute("response2");
    //session.setAttribute("allokLineOut1", allokLineOut1);
    //session.setAttribute("allokLineOut2", allokLineOut2);  
    request.getRequestDispatcher("DoSearchRoundTripInbound.jsp").forward(request, response);}     
else {
	//ArrayList<Line> allokLineIn1 = (ArrayList<Line>) session.getAttribute("response1");
	//ArrayList<Line> allokLineIn2 = (ArrayList<Line>) session.getAttribute("response2");
	//session.setAttribute("allokLineIn1", allokLineIn1);
	//session.setAttribute("allokLineIn2", allokLineIn2);
	request.getRequestDispatcher("DoConfirmRoundTrip.jsp").forward(request, response);}
     
%>
</body>
</html>