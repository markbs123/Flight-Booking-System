<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entity.Flight, java.util.*, timeRelated.TimeRelated, functions.manageLineData, 
        entity.Line, functions.Filter, interactWithServer.buyTicket" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Processing</title>
</head>
     <jsp:useBean id="lock" class="interactWithServer.lock" scope="request"/>
     <jsp:useBean id="unlock" class="interactWithServer.unlock" scope="request"/>
     <%-- <jsp:useBean id="dateFormat" class="timeRelated.DateFormat" scope="request"/> --%>
<body>
  <%
  Line selectLine = (Line)session.getAttribute("selectLine");
  String[] selectFlightNum = (String[]) session.getAttribute("selectFlightNum");
  String[] selectFlightSeat = (String[]) session.getAttribute("selectFlightSeat");
  
  String ticketAgency = "Team04";
  String xml = buyTicket.getxmlString(selectFlightNum, selectFlightSeat, ticketAgency);
  
  if(lock.doLock(ticketAgency)==false){out.println("server exception! Please try again 2 minutes later!");}
  else{
     boolean buySuccess = buyTicket.doBuyTicket(ticketAgency,xml);
     if(buySuccess==true)
	    { request.getRequestDispatcher("BookSuccess.jsp").forward(request,response);
	      unlock.doUnlock(ticketAgency);
	     }
     else %>
    	<h3 align="center"><strong>Server exception! Please try again later!</strong></h3> 
       <% unlock.doUnlock(ticketAgency);
  }
  %>  

</body>
</html>