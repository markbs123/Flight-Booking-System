<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entity.Flight, java.util.*, timeRelated.TimeRelated, functions.manageLineData, 
        entity.Line, functions.Filter, interactWithServer.buyTicket" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body {
    background-image:url("image/a04.jpg");
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirm flights</title>
</head>
     <%-- <jsp:useBean id="lock" class="interactWithServer.lock" scope="request"/>
     <jsp:useBean id="unlock" class="interactWithServer.unlock" scope="request"/>
     <jsp:useBean id="dateFormat" class="timeRelated.DateFormat" scope="request"/> --%>
<body>
<form action="DoBook2.jsp" method="get">
   <br><br><br>
   <table width="620" height="60" border="0" align="center" style="background-image:url(image/a01.gif)"></table> 
   <hr>
  <%
  String seat = (String)session.getAttribute("seat");
  String[] selectFlightNum = request.getParameterValues("FlightNumber");
  String[] selectFlightSeat = request.getParameterValues("flightSeat"); 
  String flag = (String)request.getParameter("flag");
  String listNo = (String)request.getParameter("list");
  
  ArrayList<Line> lineforConfirm1 = (ArrayList<Line>) session.getAttribute("lineforConfirm1");
  ArrayList<Line> lineforConfirm2 = (ArrayList<Line>) session.getAttribute("lineforConfirm2");  
  Line selectLine;
  if(listNo.equals("1")) {selectLine =  lineforConfirm1.get(Integer.parseInt(flag));}
  else {selectLine = lineforConfirm2.get(Integer.parseInt(flag));}  
  
 /*  Line selectLine = (Line)request.getAttribute("selectLine"); */
  ArrayList<Flight> fls = selectLine.getFlights();
    
  session.setAttribute("selectLine", selectLine);
  session.setAttribute("selectFlightNum",selectFlightNum);
  session.setAttribute("selectFlightSeat",selectFlightSeat);  
  %> 
 
 
   <p align="center" style="font-size:20px"><strong>Please Confirm Your Flight</strong></p>
   <hr>

	   <!-- <form action="DoBook2.jsp" method="get"> -->
	   <table align="center">
	   	   <tr>
	      <th>Flight Duration:</th>
	      <th><%=manageLineData.timeToString(selectLine.getDuration())%></th>
	      <th>Price:</th>
	      <th><%=manageLineData.priceToString(selectLine.getPrice())%></th>
	   </tr> 
	     <% for(int i=0; i<fls.size(); i++){   
       %>
	   <tr>
	   <td><input type="hidden" name='list' value="2"></td>   <!-- used to mark which list is the selected line in, allokLine1 or allokLine2 -->
	   <td><input type="hidden" name='<%="flag"%>' value="<%=i%>"></td>  <!-- used to mark which line in the list customer is choosing -->
	   </tr> 
		    <tr>
				<td>Flight Number:</td>
				<td><input type="text" name="FlightNumber" value="<%=fls.get(i).getFlightNumber()%>"></td>
				<td>Flight Time:</td>
                <td><input type="text" name="FlightTime" readonly value="<%= manageLineData.timeToString((fls.get(i).getFlightTime()))%>"></td>
			</tr>
			<tr>
				<td>From:</td>
				<td><input type="text" name="From" value="<%=fls.get(i).getDpApCode()%>"></td>
				<td>To:</td>
				<td><input type="text" name="To" value="<%=fls.get(i).getAvApCode()%>"></td>
			</tr>
			<tr>
				<td>Depart At:</td>
				<td><input type="text" name="dpTime"
					value="<%=TimeRelated.dateFormat(fls.get(i).getDpTime())%>"></td>
				<td>Arrive At:</td>
				<td><input type="text" name="avTime"
					value="<%=TimeRelated.dateFormat(fls.get(i).getAvTime())%>"></td>
			</tr>
			<tr>
				<% if (seat.equals("FirstClass")) {
               if((fls.get(i).getFirstClassSeats()) == 0){%>
                <td>Seat Class:</td>
                <td><input type="text" name = "flightSeat" value="Coach"></td>
				<td>Price:</td>
				<td><input type="text" name="Price" value="<%=fls.get(i).getCoachPrice()%>"></td>
				</tr>
				<tr>
				<td>Available Seats:</td>
				<td><input type="text" name="AvailableSeats:" value="<%=fls.get(i).getCoachSeats()%>"></td>
				<%}//end inner if
               else{%>
                <td>Seat Class:</td>
                <td><input type="text" name = "flightSeat" value="FirstClass"></td>
				<td>Price:</td>
				<td><input type="text" name="Price" value="<%=fls.get(i).getFirstClassPrice()%>"></td>
				</tr>
				<tr>
				<td>Available Seats:</td>
				<td><input type="text" name="AvailableSeats" value="<%=fls.get(i).getFirstClassSeats()%>"></td>
				<%}//end else
              }//end outer if%>
              
				<% if (seat.equals("Coach")) {
               if((fls.get(i).getCoachSeats()) == 0){%>
               
                <td>Seat Class:</td>
                <td><input type="text" name = "flightSeat" value="FirstClass"></td>
				<td>Price:</td>
				<td><input type="text" name="Price" value="<%=fls.get(i).getFirstClassPrice()%>"></td>
				</tr>
				<tr>
				<td>Available Seats:</td>
				<td><input type="text" name="AvailableSeats:" value="<%=fls.get(i).getFirstClassSeats()%>"></td>
				<%}//end inner if
               else{%>
                <td>Seat Class:</td>
                <td><input type="text" name = "flightSeat" value="Coach"></td>
				<td>Price:</td>
				<td><input type="text" name="Price" value="<%=fls.get(i).getCoachPrice()%>"></td>
				</tr>
				<tr>
				<td>Available Seats:</td>
				<td><input type="text" name="AvailableSeats" value="<%=fls.get(i).getCoachSeats()%>"></td>
				<% }//end else%>
			</tr>
             <%}//end outer if%>
	        <tr><td><br/></td></tr>
            <tr>
             <% if((fls.size()!=1) && (i!=(fls.size()-1))){%>
                <td colspan="4" align="center">---------------------------------------
              Layover: <%=manageLineData.timeToString((selectLine.getLayoverTime()).get(i))%>---------------------------------------</td>
            </tr> <%} %>
            <tr><td><br/></td></tr>
           <%}//end inner for%>
            <tr><td colspan="4" align="right"><input type="submit" value="Book"></td></tr>
       </table>

    </form> 
</body>
</html>