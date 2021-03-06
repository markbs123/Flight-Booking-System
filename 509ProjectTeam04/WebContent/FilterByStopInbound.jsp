<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="entity.Flight, java.util.*, timeRelated.TimeRelated, functions.manageLineData, entity.Line, functions.Filter" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body {background-image:url("image/a04.jpg");}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>FilterByStop Inbound</title>
</head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
function onclickSubmit() {
    document.getElementById("filterByStop").submit();
}

</script>
<body>
<br><br><br>
<table width="620" height="60" border="0" align="center" style="background-image:url(image/a01.gif)">
</table>
<hr>
<form id="filterByStop" action="FilterByStopInbound.jsp" method="get">
<table style="width:100%">
    <tr align="center">
          <td width=13%>
          </td>
          <td style="text-align:right;">
          <label for="Nonstop"><strong>Nonstop</strong></label>
          <%if(request.getParameter("NonStop")==null){ %>
          <input type="checkbox" name="NonStop" value="NonStop" onclick="onclickSubmit()">
          <%} else{ %>
          <input type="checkbox" name="NonStop" value="NonStop" onclick="onclickSubmit()" checked>
          <%}%>
          
          <label for="1stop"><strong>1 stop</strong></label>
          <%if(request.getParameter("OneStop")==null){ %>
          <input type="checkbox" name="OneStop" value="OneStop" onclick="onclickSubmit()">
          <%} else{ %>
          <input type="checkbox" name="OneStop" value="OneStop" onclick="onclickSubmit()" checked>
          <%}%>
          
          <label for="2stop"><strong>2+stops</strong></label>
          <%if(request.getParameter("TwoStop")==null){ %>
          <input type="checkbox" name="TwoStop" value="TwoStop" onclick="onclickSubmit()">
          <%} else{ %>
          <input type="checkbox" name="TwoStop" value="TwoStop" onclick="onclickSubmit()" checked>
          <%}%>
       
          <td width=5%>
          </td>
          <td style="text-align:left;">
            <strong>Sort by:</strong>
            <a id="sortselectlist">
            <select id="sortselectlist" name="sortselectlist" title=" price (low to high)">
               <option value="price_a" title=" price (low to high)"  onclick="window.location.href='SortPriceAscending.jsp?NonStop=<%=request.getParameter("NonStop")%>&OneStop=<%=request.getParameter("OneStop")%>&TwoStop=<%=request.getParameter("TwoStop")%>'"> price (low to high)</option>
               <option value="price_b" title=" price (high to low)" onclick="window.location.href='SortPriceDescending.jsp?NonStop=<%=request.getParameter("NonStop")%>&OneStop=<%=request.getParameter("OneStop")%>&TwoStop=<%=request.getParameter("TwoStop")%>'"> price (high to low)</option>
               <option value="duration_a" title=" duration (shorter to longer)"  onclick="window.location.href='SortDurationAscending.jsp?NonStop=<%=request.getParameter("NonStop")%>&OneStop=<%=request.getParameter("OneStop")%>&TwoStop=<%=request.getParameter("TwoStop")%>'"> duration (shorter to longer)</option>
               <option value="duration_b" title=" duration (longer to shorter)"onclick="window.location.href='SortDurationDescending.jsp?NonStop=<%=request.getParameter("NonStop")%>&OneStop=<%=request.getParameter("OneStop")%>&TwoStop=<%=request.getParameter("TwoStop")%>'"> duration (longer to shorter)</option>
               <option value="leave_a" title=" take-off (early to late)"  onclick="window.location.href='SortTakeoffTimeAscending.jsp?NonStop=<%=request.getParameter("NonStop")%>&OneStop=<%=request.getParameter("OneStop")%>&TwoStop=<%=request.getParameter("TwoStop")%>'"> take-off (early to late)</option>
               <option value="leave_b" title=" take-off (late to early)"  onclick="window.location.href='SortTakeoffTimeDescending.jsp?NonStop=<%=request.getParameter("NonStop")%>&OneStop=<%=request.getParameter("OneStop")%>&TwoStop=<%=request.getParameter("TwoStop")%>'"> take-off (late to early)</option>
               <option value="arrive_a" title=" landing (early to late)" onclick="window.location.href='SortLandingTimeAscending.jsp?NonStop=<%=request.getParameter("NonStop")%>&OneStop=<%=request.getParameter("OneStop")%>&TwoStop=<%=request.getParameter("TwoStop")%>'"> landing (early to late)</option>
               <option value="arrive_b" title=" landing (late to early)" onclick="window.location.href='SortLandingTimeDescending.jsp?NonStop=<%=request.getParameter("NonStop")%>&OneStop=<%=request.getParameter("OneStop")%>&TwoStop=<%=request.getParameter("TwoStop")%>'"> landing (late to early)</option>          
            </select>
            </a>
          </td>
                        
    </tr>
</table> 
<%  String dpMinTimeStrIn = (String)session.getAttribute("dpMinTimeStrIn");
    String dpMaxTimeStrIn = (String)session.getAttribute("dpMaxTimeStrIn");
    String avMinTimeStrIn = (String)session.getAttribute("avMinTimeStrIn");
    String avMaxTimeStrIn = (String)session.getAttribute("avMaxTimeStrIn"); 
    if(dpMinTimeStrIn != null){%>
    <h2 align="center" style="color:white">Flights taking off between <%=dpMinTimeStrIn%>---<%=dpMaxTimeStrIn%>, landing between<%=avMinTimeStrIn%>---<%=avMaxTimeStrIn%></h2>	
  <% }%>
<hr>
<h2 align="center" style="color:white">Select an inbound flight</h2>
</form> 
 <%
 String stopInfo="";
 if(request.getParameter("NonStop")!=null)
    { stopInfo = stopInfo + request.getParameter("NonStop");}
 if(request.getParameter("OneStop")!=null)
    { stopInfo = stopInfo + request.getParameter("OneStop");}
 if(request.getParameter("TwoStop")!=null)
    { stopInfo = stopInfo + request.getParameter("TwoStop");}
 /* out.println(stopInfo); */
 
 String seat = (String) session.getAttribute("seat");
 ArrayList<Line> filteringLineIn1 = (ArrayList<Line>) session.getAttribute("filteringLineIn1");
 ArrayList<Line> filteringLineIn2= (ArrayList<Line>) session.getAttribute("filteringLineIn2"); 
 ArrayList<Line> filterByStopIn1 = Filter.doFilterByStop(filteringLineIn1, stopInfo);
 ArrayList<Line> filterByStopIn2 = Filter.doFilterByStop(filteringLineIn2, stopInfo);
 session.setAttribute("sortingLineIn1",filterByStopIn1);
 session.setAttribute("sortingLineIn2",filterByStopIn2);
 /* session.setAttribute("allokLineIn1",filterByStopIn1);
 session.setAttribute("allokLineIn2",filterByStopIn2); */

 %> 
 <%-- StopInfo:<%=stopInfo%> --%>
   <% /* display flights with desired seat-class */  
   
for(int j=0; j<filterByStopIn1.size(); j++){
	   ArrayList<Flight> fls = filterByStopIn1.get(j).getFlights();
 %>
   <hr>
  <form action="DoConfirmRoundTrip.jsp" method="get">
  <table align="center">
  <tr>
  <td><input type="hidden" name='list' value="1"></td>   <!-- used to mark which list is the selected line in, allokLine1 or allokLine2 -->
  <td><input type="hidden" name='<%="flag"%>' value="<%=j%>"></td>  <!-- used to mark which line in the list customer is choosing -->
  </tr> 
  <tr>
      <th>Flight Duration:</th>
      <th><%=manageLineData.timeToString(filterByStopIn1.get(j).getDuration())%></th>
      <th>Price:</th>
      <th><%=manageLineData.priceToString(filterByStopIn1.get(j).getPrice())%></th>
  </tr> 
 <%for(int i=0; i<fls.size(); i++){%>
   <tr>
   <td>Flight Number:</td>
   <td><input type="text" name="FlightNumber" readonly value="<%=fls.get(i).getFlightNumber()%>"></td>
   <td>Flight Time:</td>
   <td><input type="text" name="FlightTime" readonly value="<%= manageLineData.timeToString((fls.get(i).getFlightTime()))%>"></td>
 </tr>
   <tr>
     <td>From:</td>
   <td><input type="text" name="From" readonly value="<%=fls.get(i).getDpApCode()%>"></td>
   <td>To:</td>
   <td><input type="text" name="To" value="<%=fls.get(i).getAvApCode()%>"></td>
 </tr>
  <tr>
     <td>Depart At:</td>
   <td><input type="text" name="dpTime" value="<%=TimeRelated.dateFormat(fls.get(i).getDpTime())%>"></td>
   <td>Arrive At:</td>
   <td><input type="text" name="avTime" value="<%=TimeRelated.dateFormat(fls.get(i).getAvTime())%>"></td>
 </tr>
 <tr>
   <td>Seat Class:</td>
   <td><input type="text" name = "flightSeat" value="<%=seat %>"></td>
   <% if (seat.equals("FirstClass")) {%> 
   <td>Price:</td>
   <td><input type="text" name="Price" value="<%=fls.get(i).getFirstClassPrice()%>"></td>
 </tr>
 <tr>
   <td>Available Seats:</td>
   <td><input type="text" name="AvailableSeats" value="<%=fls.get(i).getFirstClassSeats()%>"></td>
   <%}//end if%>
   <% if (seat.equals("Coach")) {%> 
   <td>Price:</td>
   <td><input type="text" name="Price" value="<%=fls.get(i).getCoachPrice()%>"></td>
 </tr>
 <tr>
   <td>Available Seats:</td>
   <td><input type="text" name="AvailableSeats:" value="<%=fls.get(i).getCoachSeats()%>"></td>
 </tr>
   <%}//end if%>
<tr><td><br/></td></tr>
<tr>
<% if((fls.size()!=1) && (i!=(fls.size()-1))){%>
<td colspan="4" align="center">---------------------------------------
        Layover: <%=manageLineData.timeToString((filterByStopIn1.get(j).getLayoverTime()).get(i))%>---------------------------------------</td></tr> <%} %>
<tr><td><br/></td></tr> 
   <%}//end inner for%>
   
<tr><td colspan="4" align="right"><input type="submit" value="Select"></td></tr>
</table>
</form>
  <% } //end outer for %>

<br> 
<hr>
<br>
<!-- display flights without desired seat-class -->    
<%if((filterByStopIn2.size())!=0){%>
    <p align="center" style="font-size:20px"><strong>You May Also Consider Following Flights Without <%=seat%> Seat:</strong></p>
<% for(int j=0; j<filterByStopIn2.size(); j++){
   ArrayList<Flight> fls = filterByStopIn2.get(j).getFlights();   
 %>
   <hr>
   <form action="DoConfirmRoundTrip.jsp" method="get">
   <table align="center">
   <tr>
   <td><input type="hidden" name='list' value="2"></td>   <!-- used to mark which list is the selected line in, allokLine1 or allokLine2 -->
   <td><input type="hidden" name='<%="flag"%>' value="<%=j%>"></td>  <!-- used to mark which line in the list customer is choosing -->
   </tr> 
   <tr>
      <th>Flight Duration:</th>
      <th><%=manageLineData.timeToString(filterByStopIn2.get(j).getDuration())%></th>
      <th>Price:</th>
      <th><%=manageLineData.priceToString(filterByStopIn2.get(j).getPrice())%></th>
   </tr> 
 <% for(int i=0; i<fls.size(); i++){ %>
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
          <td><input type="text" name = "flightSeat" value="FirstClass"></td>
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
        Layover: <%=manageLineData.timeToString((filterByStopIn2.get(j).getLayoverTime()).get(i))%>---------------------------------------</td>
      </tr> <%} %>
      <tr><td><br/></td></tr>
     <%}//end inner for%>
      <tr><td colspan="4" align="right"><input type="submit" value="Select"></td></tr>
 </table>
  <% }} //end outer for %>
</form> 


</body>
</html>