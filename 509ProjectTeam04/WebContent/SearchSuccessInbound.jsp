<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="entity.Flight, java.util.*, timeRelated.TimeRelated, functions.Search, functions.manageLineData, entity.Line, functions.Filter, java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body {
    background-image:url("image/a04.jpg");
}
nav {
    line-height:30px;
    height:300px;
    width:100px; 
    float:left;
    padding:5px;      
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Search Success Inbound</title>
</head>
<%-- <jsp:useBean id="dateFormat" class="timeRelated.DateFormat" scope="request"/> --%> 
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
          <label for="nostop"><strong>NonStop</strong></label>
          <input type="checkbox" name="NonStop" value="NonStop" onclick="onclickSubmit()" checked>
          <label for="1stop"><strong>1 Stop</strong></label>
          <input type="checkbox" name="OneStop" value="OneStop" onclick="onclickSubmit()" checked>
          <label for="2stop"><strong>2+Stops</strong></label>
          <input type="checkbox" name="TwoStop" value="TwoStop" onclick="onclickSubmit()" checked>	    
          </td> 
       
          <td width=5%>
          </td>
          <td style="text-align:left;">
            <strong>Sort by:</strong>
            <a id="sortselectlist">
            <select id="sortselectlist" name="sortselectlist" title=" price (low to high)">
               <option value="price_a" title=" price (low to high)"  onclick="window.location.href='SortPriceAscending.jsp?NonStop=NonStop&OneStop=OneStop&TwoStop=TwoStop'"> price (low to high)</option>
               <option value="price_b" title=" price (high to low)" onclick="window.location.href='SortPriceDescending.jsp?NonStop=NonStop&OneStop=OneStop&TwoStop=TwoStop'"> price (high to low)</option>
               <option value="duration_a" title=" duration (shorter to longer)"  onclick="window.location.href='SortDurationAscending.jsp?NonStop=NonStop&OneStop=OneStop&TwoStop=TwoStop'"> duration (shorter to longer)</option>
               <option value="duration_b" title=" duration (longer to shorter)"onclick="window.location.href='SortDurationDescending.jsp?NonStop=NonStop&OneStop=OneStop&TwoStop=TwoStop'"> duration (longer to shorter)</option>
               <option value="leave_a" title=" take-off (early to late)"  onclick="window.location.href='SortTakeoffTimeAscending.jsp?NonStop=NonStop&OneStop=OneStop&TwoStop=TwoStop'"> take-off (early to late)</option>
               <option value="leave_b" title=" take-off (late to early)"  onclick="window.location.href='SortTakeoffTimeDescending.jsp?NonStop=NonStop&OneStop=OneStop&TwoStop=TwoStop'"> take-off (late to early)</option>
               <option value="arrive_a" title=" landing (early to late)" onclick="window.location.href='SortLandingTimeAscending.jsp?NonStop=NonStop&OneStop=OneStop&TwoStop=TwoStop'"> landing (early to late)</option>
               <option value="arrive_b" title=" landing (late to early)" onclick="window.location.href='SortLandingTimeDescending.jsp?NonStop=NonStop&OneStop=OneStop&TwoStop=TwoStop'"> landing (late to early)</option>          
            </select>
            </a>
          </td>              
    </tr>
</table> 
<hr>
</form> 

   <%
   String departDay = (String)session.getAttribute("departDay");
   String returnDay = (String)session.getAttribute("returnDay");
   String seat = (String) session.getAttribute("seat"); 
   ArrayList<ArrayList<Flight>> allokFlightsRe = (ArrayList<ArrayList<Flight>>) request.getAttribute("allokFlightsRe");
   ArrayList<Line> allokLineIn1 = manageLineData.getAllLines(Filter.doFilterBySeats1(allokFlightsRe, seat), seat);
   ArrayList<Line> allokLineIn2 = manageLineData.getAllLines(Filter.doFilterBySeats2(allokFlightsRe, seat), seat); 
   ArrayList<Line> allokLineIn = manageLineData.getAllLines(allokFlightsRe, seat); 
   session.setAttribute("allokLineIn1",allokLineIn1);
   session.setAttribute("allokLineIn2",allokLineIn2);
   session.setAttribute("sortingLineIn1", allokLineIn1);
   session.setAttribute("sortingLineIn2", allokLineIn2);
   session.setAttribute("filteringLineIn1", allokLineIn1);
   session.setAttribute("filteringLineIn2", allokLineIn2);
   session.setAttribute("lineforConfirmRound1", allokLineIn1);
   session.setAttribute("lineforConfirmRound2", allokLineIn2);
   
   Date dpMinTimeIn = TimeRelated.findTheDate(returnDay);
   Date dpMaxTimeIn = TimeRelated.findNextTime(dpMinTimeIn, 1380);
   ArrayList<Date> dpDatesIn= TimeRelated.dateBetweenMinAndMax(dpMinTimeIn, dpMaxTimeIn);
   
   Date avMinTimeIn = allokLineIn.get(0).getLandingTime();
   Date avMaxTimeIn = allokLineIn.get(0).getLandingTime();
   /* out.println("before"+"<br>");
   out.println("avMinTimeIn:"+avMinTimeIn+"<br>");
   out.println("avMaxTimeIn:"+avMaxTimeIn+"<br>"); */
   for(Line line:allokLineIn){
	   if((line.getLandingTime()).compareTo(avMinTimeIn)<0) avMinTimeIn = line.getLandingTime();
	   if((line.getLandingTime()).compareTo(avMaxTimeIn)>0) avMaxTimeIn = line.getLandingTime();
   }
   /* out.println("avMinTimeIn:"+avMinTimeIn+"<br>");
   out.println("avMaxTimeIn:"+avMaxTimeIn+"<br>"); */
   ArrayList<Date> avDatesIn = TimeRelated.dateBetweenMinAndMax(avMinTimeIn, avMaxTimeIn);
  /*  for(int i=0; i<avDatesIn.size(); i++){
	   out.println(avDatesIn.get(i)+"<br>");
   } */
   
   session.setAttribute("dpDatesIn", dpDatesIn);
   session.setAttribute("avDatesIn", avDatesIn);
   
   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
   
   Line selectLineOut = (Line)session.getAttribute("selectLineOut");
   ArrayList<Flight> selectOutFlights = selectLineOut.getFlights();%>
   
<nav>  
<form action="FilterByTimeWindowInbound.jsp" method="get">
<table>
  <tr><th>TakeOff:</th></tr>
  <tr>
       <td>Between</td>
       <td>
              <select id="dpMinTimeIn" name="dpMinTimeIn">
              <%for (int i=0; i<dpDatesIn.size(); i++){%>
                 <option value=<%=i%>><%=sdf.format(dpDatesIn.get(i))%></option> 
                 <%}%>  
              </select>
       </td> 
          
       <td>And</td>
       <td>
              <select id="dpMaxTimeIn" name="dpMaxTimeIn">
              <%for (int i=0; i<dpDatesIn.size(); i++){%>
                 <option value=<%=i%>><%=sdf.format(dpDatesIn.get(i))%></option> 
                 <%}%>  
              </select> 
       </td> 
  </tr>
  <tr><th>Landing:</th></tr>
  <tr>         
       <td>Between</td>
       <td>
              <select id="avMinTimeIn" name="avMinTimeIn">
              <%for (int i=0; i<avDatesIn.size(); i++){%>
                 <option value=<%=i%>><%=sdf.format(avDatesIn.get(i))%></option> 
                 <%}%>  
              </select> 
       </td> 
          
       <td>And</td>
       <td>
             <select id="avMaxTimeIn" name="avMaxTimeIn">
              <%for (int i=0; i<avDatesIn.size(); i++){%>
                 <option value=<%=i%>><%=sdf.format(avDatesIn.get(i))%></option> 
                 <%}%>  
             </select> 
      </td> 
   </tr>
   <tr>
      <td colspan="4" align="right"><input type="submit" value="Filter"></td>
   </tr>
   </table>
   </form>
</nav> 
   
  <h2 align="center" style="color:white">You have selected an outbound flight</h2>
  
  <table align="center">
     <tr>
	      <th>Flight Duration:</th>
	      <th><%=manageLineData.timeToString(selectLineOut.getDuration())%></th>
	      <th>Price:</th>
	      <th><%=manageLineData.priceToString(selectLineOut.getPrice())%></th>
	 </tr> 
  <%for(int k=0; k<selectOutFlights.size(); k++){%>
     <tr>
	   <td>Flight Number:</td>
       <td><input type="text" name="FlightNumber" readonly value="<%=selectOutFlights.get(k).getFlightNumber()%>"></td>
       <td>Flight Time:</td>
       <td><input type="text" name="FlightTime" readonly value="<%= manageLineData.timeToString((selectOutFlights.get(k).getFlightTime()))%>"></td>
     </tr>
	 <tr>
		<td>From:</td>
		<td><input type="text" name="From" value="<%=selectOutFlights.get(k).getDpApCode()%>"></td>
		<td>To:</td>
		<td><input type="text" name="To" value="<%=selectOutFlights.get(k).getAvApCode()%>"></td>
	 </tr>
	 <tr>
		<td>Depart At:</td>
		<td><input type="text" name="dpTime"
			value="<%=TimeRelated.dateFormat(selectOutFlights.get(k).getDpTime())%>"></td>
		<td>Arrive At:</td>
		<td><input type="text" name="avTime"
			value="<%=TimeRelated.dateFormat(selectOutFlights.get(k).getAvTime())%>"></td>
	 </tr>
	 <tr>
         <td>Seat Class:</td>
         <td><input type="text" name = "flightSeat" value="<%=seat %>"></td>
         <% if (seat.equals("FirstClass")) {%> 
         <td>Price:</td>
         <td><input type="text" name="Price" value="<%=selectOutFlights.get(k).getFirstClassPrice()%>"></td>
       </tr>
       <tr>
         <td>Available Seats:</td>
         <td><input type="text" name="AvailableSeats" value="<%=selectOutFlights.get(k).getFirstClassSeats()%>"></td>
         <%}//end if%>
         <% if (seat.equals("Coach")) {%> 
         <td>Price:</td>
         <td><input type="text" name="Price" value="<%=selectOutFlights.get(k).getCoachPrice()%>"></td>
       </tr>
       <tr>
         <td>Available Seats:</td>
         <td><input type="text" name="AvailableSeats:" value="<%=selectOutFlights.get(k).getCoachSeats()%>"></td>
       </tr>
         <%}//end if%>
      <tr><td><br/></td></tr>
      <tr>
      <% if((selectOutFlights.size()!=1) && (k!=(selectOutFlights.size()-1))){%>
      <td colspan="4" align="center">---------------------------------------
              Layover: <%=manageLineData.timeToString((selectLineOut.getLayoverTime()).get(k))%>---------------------------------------</td></tr> <%} %>
      <tr><td><br/></td></tr> 
         <%}//end inner for%>
 </table>

  <hr>
  <h2 align="center" style="color:white">Select an inbound flight</h2>
  <!-- display flights with desired seat-class --> 
   <% 
    /* display flights with desired seat-class */  
	for(int j=0; j<allokLineIn1.size(); j++){
		   ArrayList<Flight> fls = allokLineIn1.get(j).getFlights();
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
	      <th><%=manageLineData.timeToString(allokLineIn1.get(j).getDuration())%></th>
	      <th>Price:</th>
	      <th><%=manageLineData.priceToString(allokLineIn1.get(j).getPrice())%></th>
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
              Layover: <%=manageLineData.timeToString((allokLineIn1.get(j).getLayoverTime()).get(i))%>---------------------------------------</td></tr> <%} %>
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
   <%if((allokLineIn2.size())!=0){%>
          <p align="center" style="font-size:20px"><strong>You May Also Consider Following Flights Without <%=seat%> Seat:</strong></p>
   <% for(int j=0; j<allokLineIn2.size(); j++){
	   ArrayList<Flight> fls = allokLineIn2.get(j).getFlights();   
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
	      <th><%=manageLineData.timeToString(allokLineIn2.get(j).getDuration())%></th>
	      <th>Price:</th>
	      <th><%=manageLineData.priceToString(allokLineIn2.get(j).getPrice())%></th>
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
              Layover: <%=manageLineData.timeToString((allokLineIn2.get(j).getLayoverTime()).get(i))%>---------------------------------------</td>
            </tr> <%} %>
            <tr><td><br/></td></tr>
           <%}//end inner for%>
            <tr><td colspan="4" align="right"><input type="submit" value="Select"></td></tr>
       </table>
        <% }} //end outer for %>
   </form> 
</body>
</html>