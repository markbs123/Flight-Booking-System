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
<title>Book Success</title>
</head>
<%   String seat = (String)session.getAttribute("seat");
     Line selectLineOut = (Line)session.getAttribute("selectLineOut");
     ArrayList<Flight> fls_Out = selectLineOut.getFlights(); 
     Line selectLineIn = (Line)session.getAttribute("selectLineIn");
     ArrayList<Flight> fls_In = selectLineIn.getFlights(); 
%>
<body>
<br><br><br>
 <table width="620" height="60" border="0" align="center" style="background-image:url(image/a01.gif)">
</table>
<hr>
<br><br>
   <h2 align="center"><strong>Congratulations! Your purchasing has been transacted!</strong></h2>
   <h3 align="center">Following is your Flight Detail. Thanks for Choosing Team04!</h3>
   <br>
   <hr>
   
   <table align="center">
   <tr><th colspan="4" align="left" style="color:white; font-size:20px">Outbound:</th></tr>
   	   <tr>
	      <th>Flight Duration:</th>
	      <th><%=manageLineData.timeToString(selectLineOut.getDuration())%></th>
	      <th>Price:</th>
	      <th><%=manageLineData.priceToString(selectLineOut.getPrice())%></th>
	   </tr>
   <% for(int i=0; i<fls_Out.size(); i++){   
       %>
	   <tr>
	   <td><input type="hidden" name='list' value="2"></td>   <!-- used to mark which list is the selected line in, allokLine1 or allokLine2 -->
	   <td><input type="hidden" name='<%="flag"%>' value="<%=i%>"></td>  <!-- used to mark which line in the list customer is choosing -->
	   </tr> 
 
			<tr>
				<td>Flight Number:</td>
				<td><input type="text" name="FlightNumber" value="<%=fls_Out.get(i).getFlightNumber()%>"></td>
				<td>Flight Time:</td>
                <td><input type="text" name="FlightTime" readonly value="<%= manageLineData.timeToString((fls_Out.get(i).getFlightTime()))%>"></td>
			</tr>
			<tr>
				<td>From:</td>
				<td><input type="text" name="From" value="<%=fls_Out.get(i).getDpApCode()%>"></td>
				<td>To:</td>
				<td><input type="text" name="To" value="<%=fls_Out.get(i).getAvApCode()%>"></td>
			</tr>
			<tr>
				<td>Depart At:</td>
				<td><input type="text" name="dpTime"
					value="<%=TimeRelated.dateFormat(fls_Out.get(i).getDpTime())%>"></td>
				<td>Arrive At:</td>
				<td><input type="text" name="avTime"
					value="<%=TimeRelated.dateFormat(fls_Out.get(i).getAvTime())%>"></td>
			</tr>
			<tr>
				<% if (seat.equals("FirstClass")) {
               if((fls_Out.get(i).getFirstClassSeats()) == 0){%>
                <td>Seat Class:</td>
                <td><input type="text" name = "flightSeat" value="Coach"></td>
				<td>Price:</td>
				<td><input type="text" name="Price" value="<%=fls_Out.get(i).getCoachPrice()%>"></td>
				</tr>
				<tr>
				<%}//end inner if
               else{%>
                <td>Seat Class:</td>
                <td><input type="text" name = "flightSeat" value="FirstClass"></td>
				<td>Price:</td>
				<td><input type="text" name="Price" value="<%=fls_Out.get(i).getFirstClassPrice()%>"></td>
				</tr>
				<tr>
				<%}//end else
              }//end outer if%>
              
				<% if (seat.equals("Coach")) {
               if((fls_Out.get(i).getCoachSeats()) == 0){%>
                <td>Seat Class:</td>
                <td><input type="text" name = "flightSeat" value="FirstClass"></td>
				<td>Price:</td>
				<td><input type="text" name="Price" value="<%=fls_Out.get(i).getFirstClassPrice()%>"></td>
				</tr>
				<tr>
				<%}//end inner if
               else{%>
                <td>Seat Class:</td>
                <td><input type="text" name = "flightSeat" value="Coach"></td>
				<td>Price:</td>
				<td><input type="text" name="Price" value="<%=fls_Out.get(i).getCoachPrice()%>"></td>
				</tr>
				<tr>
				<% }//end else%>
			
             <%}//end outer if%>
	        <tr><td><br/></td></tr>
            <tr>
             <% if((fls_In.size()!=1) && (i!=(fls_In.size()-1))){%>
                <td colspan="4" align="center">---------------------------------------
              Layover: <%=manageLineData.timeToString((selectLineOut.getLayoverTime()).get(i))%>---------------------------------------</td>
            </tr> <%} %>
            <tr><td><br/></td></tr>
           <%}//end inner for%>
       </table>
       
   <hr>
   
   <table align="center">
   <tr><th colspan="4" align="left" style="color:white; font-size:20px">Inbound:</th></tr>
   	   <tr>
	      <th>Flight Duration:</th>
	      <th><%=manageLineData.timeToString(selectLineIn.getDuration())%></th>
	      <th>Price:</th>
	      <th><%=manageLineData.priceToString(selectLineIn.getPrice())%></th>
	   </tr> 
   <% for(int i=0; i<fls_In.size(); i++){   
       %>
	   <tr>
	   <td><input type="hidden" name='list' value="2"></td>   <!-- used to mark which list is the selected line in, allokLine1 or allokLine2 -->
	   <td><input type="hidden" name='<%="flag"%>' value="<%=i%>"></td>  <!-- used to mark which line in the list customer is choosing -->
	   </tr> 

			<tr>
				<td>Flight Number:</td>
				<td><input type="text" name="FlightNumber" value="<%=fls_In.get(i).getFlightNumber()%>"></td>
				<td>Flight Time:</td>
                <td><input type="text" name="FlightTime" readonly value="<%= manageLineData.timeToString((fls_In.get(i).getFlightTime()))%>"></td>
			</tr>
			<tr>
				<td>From:</td>
				<td><input type="text" name="From" value="<%=fls_In.get(i).getDpApCode()%>"></td>
				<td>To:</td>
				<td><input type="text" name="To" value="<%=fls_In.get(i).getAvApCode()%>"></td>
			</tr>
			<tr>
				<td>Depart At:</td>
				<td><input type="text" name="dpTime"
					value="<%=TimeRelated.dateFormat(fls_In.get(i).getDpTime())%>"></td>
				<td>Arrive At:</td>
				<td><input type="text" name="avTime"
					value="<%=TimeRelated.dateFormat(fls_In.get(i).getAvTime())%>"></td>
			</tr>
			<tr>
				<% if (seat.equals("FirstClass")) {
               if((fls_In.get(i).getFirstClassSeats()) == 0){%>
                <td>Seat Class:</td>
                <td><input type="text" name = "flightSeat" value="Coach"></td>
				<td>Price:</td>
				<td><input type="text" name="Price" value="<%=fls_In.get(i).getCoachPrice()%>"></td>
				</tr>
				<tr>
				<%}//end inner if
               else{%>
                <td>Seat Class:</td>
                <td><input type="text" name = "flightSeat" value="FirstClass"></td>
				<td>Price:</td>
				<td><input type="text" name="Price" value="<%=fls_In.get(i).getFirstClassPrice()%>"></td>
				</tr>
				<tr>
				<%}//end else
              }//end outer if%>
              
				<% if (seat.equals("Coach")) {
               if((fls_In.get(i).getCoachSeats()) == 0){%>
                <td>Seat Class:</td>
                <td><input type="text" name = "flightSeat" value="FirstClass"></td>
				<td>Price:</td>
				<td><input type="text" name="Price" value="<%=fls_In.get(i).getFirstClassPrice()%>"></td>
				</tr>
				<tr>
				<%}//end inner if
               else{%>
                <td>Seat Class:</td>
                <td><input type="text" name = "flightSeat" value="Coach"></td>
				<td>Price:</td>
				<td><input type="text" name="Price" value="<%=fls_In.get(i).getCoachPrice()%>"></td>
				</tr>
				<% }//end else%>
			
             <%}//end outer if%>
	        <tr><td><br/></td></tr>
            <tr>
             <% if((fls_In.size()!=1) && (i!=(fls_In.size()-1))){%>
                <td colspan="4" align="center">---------------------------------------
              Layover: <%=manageLineData.timeToString((selectLineIn.getLayoverTime()).get(i))%>---------------------------------------</td>
            </tr> <%} %>
            <tr><td><br/></td></tr>
           <%}//end inner for%>
       </table>
        <h3><a href="WelcomeAndSearch.jsp">Click Here Back to HomePage</a></h3>
        <% session.invalidate(); %>
</body>
</html>