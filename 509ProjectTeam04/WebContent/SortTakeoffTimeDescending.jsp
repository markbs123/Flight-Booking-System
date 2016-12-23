<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="entity.Flight, java.util.*, entity.Line,functions.manageLineData, timeRelated.TimeRelated" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body {
    background-image:url("image/a04.jpg");
}
</style>
<script>
function onclickSubmit() {
    document.getElementById("filterByStop").submit();
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Takeoff Time late to early</title>
<jsp:useBean id="sortByTakeoffTime" class="functions.SortByTakeoffTime" scope="request"/>
</head>
<body>
<br><br><br>
 <table width="620" height="60" border="0" align="center" style="background-image:url(image/a01.gif)">
</table>
<hr>
<% 
//out.println(request.getParameter("NonStop"));out.println(request.getParameter("OneStop"));out.println(request.getParameter("TwoStop"));
String NonStop = request.getParameter("NonStop");
String OneStop =request.getParameter("OneStop");
String TwoStop =request.getParameter("TwoStop");
%> 
<form id="filterByStop" action="ResponseFilterByStop.jsp" method="get">
<table style="width:100%">
   <tr align="center">
          <td width=13%>
          </td>
          <td style="text-align:right;">
          
          <label for="Nonstop"><strong>Nonstop</strong></label>
          <%-- <%if(request.getParameter("NonStop").equals("NonStop")){ %> --%>
          <%-- <%if(request.getParameter("NonStop")!=null){ %> --%>
          <%-- <%if(NonStop.equals("NonStop")){ %> --%>
          <%if(NonStop!=null && NonStop.equals("NonStop")){ %>
          <input type="checkbox" name="NonStop" value="NonStop" onclick="onclickSubmit()" checked>
          <%} else{ %>
          <input type="checkbox" name="NonStop" value="NonStop" onclick="onclickSubmit()">
          <%}%>
          
          <label for="1stop"><strong>1 stop</strong></label>
          <%-- <%if(request.getParameter("OneStop").equals("OneStop")){ %> --%>
          <%-- <%if(request.getParameter("OneStop")!=null){ %> --%>
          <%-- <%if(OneStop.equals("OneStop")){ %> --%>
          <%if(OneStop!=null && OneStop.equals("OneStop")){ %>
          <input type="checkbox" name="OneStop" value="OneStop" onclick="onclickSubmit()" checked>
          <%} else{ %>
          <input type="checkbox" name="OneStop" value="OneStop" onclick="onclickSubmit()">
          <%}%>
          
          <label for="2stop"><strong>2+stops</strong></label>
          <%-- <%if(request.getParameter("TwoStop").equals("TwoStop")){ %> --%>
          <%-- <%if(request.getParameter("TwoStop")!=null){ %> --%>
          <%-- <%if(TwoStop.equals("TwoStop")){ %> --%>
          <%if(TwoStop!=null && TwoStop.equals("TwoStop")){ %>
          <input type="checkbox" name="TwoStop" value="TwoStop" onclick="onclickSubmit()" checked>
          <%} else{ %>
          <input type="checkbox" name="TwoStop" value="TwoStop" onclick="onclickSubmit()">
          <%}%>
              
          </td>
          
          <td><input type="hidden" name="param" value=<%=request.getParameter("param")%>></td>
         
          <td width=5%>
          </td>
          <td style="text-align:left;">
            <strong>Sort by:</strong>
            <a id="sortselectlist">
            <select id="sortselectlist" name="sortselectlist">
               <option value="price_a" title=" price (low to high)"  onclick="window.location.href='SortPriceAscending.jsp?param=<%=request.getParameter("param")%>&NonStop=<%=request.getParameter("NonStop")%>&OneStop=<%=request.getParameter("OneStop")%>&TwoStop=<%=request.getParameter("TwoStop")%>'"> price (low to high)</option>
               <option value="price_b" title=" price (high to low)" onclick="window.location.href='SortPriceDescending.jsp?param=<%=request.getParameter("param")%>&NonStop=<%=request.getParameter("NonStop")%>&OneStop=<%=request.getParameter("OneStop")%>&TwoStop=<%=request.getParameter("TwoStop")%>'"> price (high to low)</option>
               <option value="duration_a" title=" duration (shorter to longer)"  onclick="window.location.href='SortDurationAscending.jsp?param=<%=request.getParameter("param")%>&NonStop=<%=request.getParameter("NonStop")%>&OneStop=<%=request.getParameter("OneStop")%>&TwoStop=<%=request.getParameter("TwoStop")%>'"> duration (shorter to longer)</option>
               <option value="duration_b" title=" duration (longer to shorter)"onclick="window.location.href='SortDurationDescending.jsp?param=<%=request.getParameter("param")%>&NonStop=<%=request.getParameter("NonStop")%>&OneStop=<%=request.getParameter("OneStop")%>&TwoStop=<%=request.getParameter("TwoStop")%>'"> duration (longer to shorter)</option>
               <option value="leave_a" title=" take-off (early to late)"  onclick="window.location.href='SortTakeoffTimeAscending.jsp?param=<%=request.getParameter("param")%>&NonStop=<%=request.getParameter("NonStop")%>&OneStop=<%=request.getParameter("OneStop")%>&TwoStop=<%=request.getParameter("TwoStop")%>'"> take-off (early to late)</option>
               <option value="leave_b" title=" take-off (late to early)"  onclick="window.location.href='SortTakeoffTimeDescending.jsp?param=<%=request.getParameter("param")%>&NonStop=<%=request.getParameter("NonStop")%>&OneStop=<%=request.getParameter("OneStop")%>&TwoStop=<%=request.getParameter("TwoStop")%>'"> take-off (late to early)</option>
               <option value="arrive_a" title=" landing (early to late)" onclick="window.location.href='SortLandingTimeAscending.jsp?param=<%=request.getParameter("param")%>&NonStop=<%=request.getParameter("NonStop")%>&OneStop=<%=request.getParameter("OneStop")%>&TwoStop=<%=request.getParameter("TwoStop")%>'"> landing (early to late)</option>
               <option value="arrive_b" title=" landing (late to early)" onclick="window.location.href='SortLandingTimeDescending.jsp?param=<%=request.getParameter("param")%>&NonStop=<%=request.getParameter("NonStop")%>&OneStop=<%=request.getParameter("OneStop")%>&TwoStop=<%=request.getParameter("TwoStop")%>'"> landing (late to early)</option>          
            </select>
            </a>
          </td>             
    </tr>
</table> 
</form> 
 <% 
 String seat = (String) session.getAttribute("seat");
 String returnDay = (String) session.getAttribute("returnDay");
 /* out.println("returnDay"+returnDay+"<br>"); */
 String param = request.getParameter("param");
 /*  out.println("param"+param+"<br>"); */
 ArrayList<Line> sortedLine1;
 ArrayList<Line> sortedLine2;

 if(returnDay == null){
	 sortedLine1 = (ArrayList<Line>) session.getAttribute("sortingLine1");
	 sortedLine2 = (ArrayList<Line>) session.getAttribute("sortingLine2"); 
     Collections.sort(sortedLine1, sortByTakeoffTime);
     Collections.reverse(sortedLine1);
     Collections.sort(sortedLine2, sortByTakeoffTime); 
     Collections.reverse(sortedLine2);
     session.setAttribute("lineforConfirm1",sortedLine1);
     session.setAttribute("lineforConfirm2",sortedLine2);
     String dpMinTimeStr = (String)session.getAttribute("dpMinTimeStr");
     String dpMaxTimeStr = (String)session.getAttribute("dpMaxTimeStr");
     String avMinTimeStr = (String)session.getAttribute("avMinTimeStr");
     String avMaxTimeStr = (String)session.getAttribute("avMaxTimeStr"); 
     if(dpMinTimeStr != null){%>
    	 <h2 align="center" style="color:white">Flights taking off between <%=dpMinTimeStr%>---<%=dpMaxTimeStr%>, landing between<%=avMinTimeStr%>---<%=avMaxTimeStr%></h2>
     <%}
 }
 else if(param != null){
	 sortedLine1 = (ArrayList<Line>) session.getAttribute("sortingLineOut1");
	 sortedLine2 = (ArrayList<Line>) session.getAttribute("sortingLineOut2");
	 Collections.sort(sortedLine1, sortByTakeoffTime);
     Collections.reverse(sortedLine1);
     Collections.sort(sortedLine2, sortByTakeoffTime); 
     Collections.reverse(sortedLine2);  
	 session.setAttribute("lineforSearchInbound1",sortedLine1);
	 session.setAttribute("lineforSearchInbound2",sortedLine2);
	 String dpMinTimeStrOut = (String)session.getAttribute("dpMinTimeStrOut");
     String dpMaxTimeStrOut = (String)session.getAttribute("dpMaxTimeStrOut");
     String avMinTimeStrOut = (String)session.getAttribute("avMinTimeStrOut");
     String avMaxTimeStrOut = (String)session.getAttribute("avMaxTimeStrOut"); 
     if(dpMinTimeStrOut != null){ %>
    	 <h2 align="center" style="color:white">Flights taking off between <%=dpMinTimeStrOut%>---<%=dpMaxTimeStrOut%>, landing between<%=avMinTimeStrOut%>---<%=avMaxTimeStrOut%></h2>
     <%}%>
	 <h2 align="center" style="color:white">Select an outbound flight</h2>
<%}
 else{
	sortedLine1 = (ArrayList<Line>) session.getAttribute("sortingLineIn1");
	sortedLine2 = (ArrayList<Line>) session.getAttribute("sortingLineIn2");
	Collections.sort(sortedLine1, sortByTakeoffTime);
    Collections.reverse(sortedLine1);
    Collections.sort(sortedLine2, sortByTakeoffTime); 
    Collections.reverse(sortedLine2);   
	session.setAttribute("lineforConfirmRound1",sortedLine1);
	session.setAttribute("lineforConfirmRound2",sortedLine2);
	String dpMinTimeStrIn = (String)session.getAttribute("dpMinTimeStrIn");
	String dpMaxTimeStrIn = (String)session.getAttribute("dpMaxTimeStrIn");
	String avMinTimeStrIn = (String)session.getAttribute("avMinTimeStrIn");
	String avMaxTimeStrIn = (String)session.getAttribute("avMaxTimeStrIn"); 
	if(dpMinTimeStrIn != null){%>
	    <h2 align="center" style="color:white">Flights taking off between <%=dpMinTimeStrIn%>---<%=dpMaxTimeStrIn%>, landing between<%=avMinTimeStrIn%>---<%=avMaxTimeStrIn%></h2>	
	  <% }%>
	<h2 align="center" style="color:white">Select an inbound flight</h2>
<%}%>


 <% 
  /* display flights with desired seat-class */     
	for(int j=0; j<sortedLine1.size(); j++){
		   ArrayList<Flight> fls = sortedLine1.get(j).getFlights();
     %>
	   <hr>
	  <form action="Response.jsp" method="get">
	  <table align="center">
	  <tr>
      <%if(param !=null){ %>
	  <td><input type="hidden" name='param' value='<%=param%>'></td> 
	  <%} %>
	  <td><input type="hidden" name='list' value="1"></td>   <!-- used to mark which list is the selected line in, allokLine1 or allokLine2 -->
	  <td><input type="hidden" name='<%="flag"%>' value="<%=j%>"></td>  <!-- used to mark which line in the list customer is choosing -->
	  </tr> 
	  <tr>
	      <th>Flight Duration:</th>
	      <th><%=manageLineData.timeToString(sortedLine1.get(j).getDuration())%></th>
	      <th>Price:</th>
	      <th><%=manageLineData.priceToString(sortedLine1.get(j).getPrice())%></th>
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
            Layover: <%=manageLineData.timeToString((sortedLine1.get(j).getLayoverTime()).get(i))%>---------------------------------------</td></tr> <%} %>
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
 <%if((sortedLine2.size())!=0){%>
        <p align="center" style="font-size:20px"><strong>You May Also Consider Following Flights Without <%=seat%> Seat:</strong></p>
 <% for(int j=0; j<sortedLine2.size(); j++){  
	 ArrayList<Flight> fls = sortedLine2.get(j).getFlights(); 
     %>
	   <hr>
	  
	   <form action="Response.jsp" method="get">
	   <table align="center">
	   <tr>
	   <%if(param!=null && param.equals("out")){ %>
	   <td><input type="hidden" name='param' value='<%=param%>'></td> 
	   <%} %> 
	   <td><input type="hidden" name='list' value="2"></td>   <!-- used to mark which list is the selected line in, allokLine1 or allokLine2 -->
	   <td><input type="hidden" name='<%="flag"%>' value="<%=j%>"></td>  <!-- used to mark which line in the list customer is choosing -->
	   </tr> 
	   <tr>
	      <th>Flight Duration:</th>
	      <th><%=manageLineData.timeToString(sortedLine2.get(j).getDuration())%></th>
	      <th>Price:</th>
	      <th><%=manageLineData.priceToString(sortedLine2.get(j).getPrice())%></th>
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
            Layover: <%=manageLineData.timeToString((sortedLine2.get(j).getLayoverTime()).get(i))%>---------------------------------------</td>
          </tr> <%} %>
          <tr><td><br/></td></tr>
         <%}//end inner for%>
          <tr><td colspan="4" align="right"><input type="submit" value="Select"></td></tr>
     </table>
      <% }} //end outer for %>
 </form> 
 <% 
/*  session.removeAttribute("sortingLine1");
 session.removeAttribute("sortingLine2"); */
 %>
</body>
</html>