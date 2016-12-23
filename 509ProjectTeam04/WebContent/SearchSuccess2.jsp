<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="entity.Flight, java.util.*, timeRelated.TimeRelated, functions.manageLineData, entity.Line, functions.Filter, java.text.SimpleDateFormat" %>
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
<title>Search Success</title>
</head>
<%-- <jsp:useBean id="dateFormat" class="timeRelated.DateFormat" scope="request"/> --%> 
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
function onclickSubmit() {
    document.getElementById("filterByStop").submit();
}

/* function onclickHide(){
	var x = document.getElementById("showOrHide").checked;
  	 if(x==true) {
  		 $("#hideThisTable").show();
  	   }
    else  {
    	 $("#hideThisTable").hide();
       } 
  
} */
</script>
<%
   String seat = (String) session.getAttribute("seat"); 
   String sourceApCd = (String) session.getAttribute("sourceApCd");
   String destinationApCd = (String) session.getAttribute("destinationApCd");
   ArrayList<ArrayList<Flight>> allokFlights = (ArrayList<ArrayList<Flight>>) request.getAttribute("allokFlights");
   String departDay = (String)session.getAttribute("departDay");
   String returnDay = (String)session.getAttribute("returnDay");
   if(returnDay != null){
	   request.setAttribute("allokFlights", allokFlights);
	   request.getRequestDispatcher("SearchSuccess Outbound.jsp").forward(request, response);
   }
   ArrayList<Line> allokLine = manageLineData.getAllLines(allokFlights, seat);
   ArrayList<Line> allokLine1 = manageLineData.getAllLines(Filter.doFilterBySeats1(allokFlights, seat), seat);
   ArrayList<Line> allokLine2 = manageLineData.getAllLines(Filter.doFilterBySeats2(allokFlights, seat), seat); 
   session.setAttribute("allokLine1", allokLine1);
   session.setAttribute("allokLine2", allokLine2);
   session.setAttribute("sortingLine1", allokLine1);
   session.setAttribute("sortingLine2", allokLine2);
   session.setAttribute("filteringLine1", allokLine1);
   session.setAttribute("filteringLine2", allokLine2);
   session.setAttribute("lineforConfirm1", allokLine1);
   session.setAttribute("lineforConfirm2", allokLine2);
   
   Date dpMinTime = TimeRelated.findTheDate(departDay);
   Date dpMaxTime = TimeRelated.findNextTime(dpMinTime, 1380);
   ArrayList<Date> dpDates = TimeRelated.dateBetweenMinAndMax(dpMinTime, dpMaxTime);
   
   Date avMinTime = allokLine.get(0).getLandingTime();
   Date avMaxTime = allokLine.get(0).getLandingTime();
   for(Line line:allokLine){
	   if((line.getLandingTime()).compareTo(avMinTime)<0) avMinTime = line.getLandingTime();
	   if((line.getLandingTime()).compareTo(avMaxTime)>0) avMaxTime = line.getLandingTime();
   }
   ArrayList<Date> avDates = TimeRelated.dateBetweenMinAndMax(avMinTime, avMaxTime);
   
   session.setAttribute("dpDates", dpDates);
   session.setAttribute("avDates", avDates);
   
   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
   %>
<body>
<br><br><br>
 <table width="620" height="60" border="0" align="center" style="background-image:url(image/a01.gif)">
</table>
<hr>
<form id="filterByStop" action="FilterByStop.jsp" method="get">
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
            <select id="sortselectlist" name="sortselectlist">
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

<%-- <nav>  
<table>
  <tr><th>TakeOff:</th></tr>
  <tr>
       <td>Between</td>
       <td>
              <select id="dpMinTime" name="dpMinTime">
              <%for (int i=0; i<dpDates.size(); i++){%>
                 <option value=<%=i%>><%=sdf.format(dpDates.get(i))%></option> 
                 <%}%>  
              </select>
       </td> 
          
       <td>And</td>
       <td>
              <select id="dpMaxTime" name="dpMaxTime">
              <%for (int i=0; i<dpDates.size(); i++){%>
                 <option value=<%=i%>><%=sdf.format(dpDates.get(i))%></option> 
                 <%}%>  
              </select> 
       </td> 
  </tr>
  <tr><th>Landing:</th></tr>
  <tr>         
       <td>Between</td>
       <td>
              <select id="avMinTime" name="avMinTime">
              <%for (int i=0; i<avDates.size(); i++){%>
                 <option value=<%=i%>><%=sdf.format(avDates.get(i))%></option> 
                 <%}%>  
              </select> 
       </td> 
          
       <td>And</td>
       <td>
             <select id="avMaxTime" name="avMaxTime">
              <%for (int i=0; i<avDates.size(); i++){%>
                 <option value=<%=i%>><%=sdf.format(avDates.get(i))%></option> 
                 <%}%>  
             </select> 
      </td> 
   </tr>
   </table>
   <button type="button" onclick="location.href='FilterByTimeWindow.jsp'">Filter</button>  
</nav> --%>
</form> 

<br>
  
<nav>  
<form action="FilterByTimeWindow.jsp" method="get">
<table>
  <!-- <tr><td><input type="hidden" name="filterByTime" value="yes"></td></tr> -->
  <tr><th>TakeOff:</th></tr>
  <tr>
       <td>Between</td>
       <td>
              <select id="dpMinTime" name="dpMinTime">
              <%for (int i=0; i<dpDates.size(); i++){%>
                 <option value=<%=i%>><%=sdf.format(dpDates.get(i))%></option> 
                 <%}%>  
              </select>
       </td> 
          
       <td>And</td>
       <td>
              <select id="dpMaxTime" name="dpMaxTime">
              <%for (int i=0; i<dpDates.size(); i++){%>
                 <option value=<%=i%>><%=sdf.format(dpDates.get(i))%></option> 
                 <%}%>  
              </select> 
       </td> 
  </tr>
  <tr><th>Landing:</th></tr>
  <tr>         
       <td>Between</td>
       <td>
              <select id="avMinTime" name="avMinTime">
              <%for (int i=0; i<avDates.size(); i++){%>
                 <option value=<%=i%>><%=sdf.format(avDates.get(i))%></option> 
                 <%}%>  
              </select> 
       </td> 
          
       <td>And</td>
       <td>
             <select id="avMaxTime" name="avMaxTime">
              <%for (int i=0; i<avDates.size(); i++){%>
                 <option value=<%=i%>><%=sdf.format(avDates.get(i))%></option> 
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
   
 
   <!-- display flights with desired seat-class --> 
   <% 
    /* display flights with desired seat-class */  
	   
	for(int j=0; j<allokLine1.size(); j++){
		   ArrayList<Flight> fls = allokLine1.get(j).getFlights();
       %>
	   <hr>
	  <form action="DoConfirm.jsp" method="get">
	  <%-- <% request.setAttribute("allokLine11", allokLine1); %>  --%>
	  <table align="center">
	  <tr>
	  <td><input type="hidden" name='list' value="1"></td>   <!-- used to mark which list is the selected line in, allokLine1 or allokLine2 -->
	  <td><input type="hidden" name='<%="flag"%>' value="<%=j%>"></td>  <!-- used to mark which line in the list customer is choosing -->
	  </tr> 
	  <tr>
	      <th>Flight Duration:</th>
	      <th><%=manageLineData.timeToString(allokLine1.get(j).getDuration())%></th>
	      <th>Price:</th>
	      <th><%=manageLineData.priceToString(allokLine1.get(j).getPrice())%></th>
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
              Layover: <%=manageLineData.timeToString((allokLine1.get(j).getLayoverTime()).get(i))%>---------------------------------------</td></tr> <%} %>
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
   <%if((allokLine2.size())!=0){%>
          <p align="center" style="font-size:20px"><strong>You May Also Consider Following Flights Without <%=seat%> Seat:</strong></p>
   <% for(int j=0; j<allokLine2.size(); j++){
	   ArrayList<Flight> fls = allokLine2.get(j).getFlights();   
       %>
	   <hr>
	   <form action="DoConfirm.jsp" method="get">
	   <%-- <% request.setAttribute("selectLine", allokLine2.get(j)); %> --%>
	   <table align="center">
	   <tr>
	   <td><input type="hidden" name='list' value="2"></td>   <!-- used to mark which list is the selected line in, allokLine1 or allokLine2 -->
	   <td><input type="hidden" name='<%="flag"%>' value="<%=j%>"></td>  <!-- used to mark which line in the list customer is choosing -->
	   </tr> 
	   <tr>
	      <th>Flight Duration:</th>
	      <th><%=manageLineData.timeToString(allokLine2.get(j).getDuration())%></th>
	      <th>Price:</th>
	      <th><%=manageLineData.priceToString(allokLine2.get(j).getPrice())%></th>
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
              Layover: <%=manageLineData.timeToString((allokLine2.get(j).getLayoverTime()).get(i))%>---------------------------------------</td>
            </tr> <%} %>
            <tr><td><br/></td></tr>
           <%}//end inner for%>
            <tr><td colspan="4" align="right"><input type="submit" value="Select"></td></tr>
       </table>
        <% }} //end outer for %>
   </form> 

</body>
</html>