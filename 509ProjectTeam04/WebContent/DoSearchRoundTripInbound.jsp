<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="entity.Flight, java.util.*, timeRelated.TimeRelated, functions.Search, functions.manageLineData, entity.Line, functions.Filter" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body {
    background-image:url("image/a04.jpg");
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
  <%
  String seat = (String)session.getAttribute("seat");
  String sourceApCd = (String) session.getAttribute("sourceApCd");
  String destinationApCd = (String) session.getAttribute("destinationApCd");
  String returnDay = (String) session.getAttribute("returnDay");
  
  String[] selectFlightNumOut = request.getParameterValues("FlightNumber");
  String[] selectFlightSeatOut = request.getParameterValues("flightSeat"); 
  String flag = (String)request.getParameter("flag");
  String listNo = (String)request.getParameter("list");
  
  ArrayList<Line> lineforSearchInbound1 = (ArrayList<Line>) session.getAttribute("lineforSearchInbound1");
  ArrayList<Line> lineforSearchInbound2 = (ArrayList<Line>) session.getAttribute("lineforSearchInbound2");
  
  Line selectLineOut;
  if(listNo.equals("1")) {selectLineOut =  lineforSearchInbound1.get(Integer.parseInt(flag));}
  else {selectLineOut = lineforSearchInbound2.get(Integer.parseInt(flag));}
  /* ArrayList<Flight> fls = selectLineOut.getFlights(); */
  session.setAttribute("selectLineOut", selectLineOut);  
  session.setAttribute("selectFlightNumOut",selectFlightNumOut);
  session.setAttribute("selectFlightSeatOut",selectFlightSeatOut);  %> 
  
  <% 
  /* out.println("landing date:"+selectLineOut.getLandingTime()); */
  Date desireDpTimeRe = TimeRelated.findNextTime(selectLineOut.getLandingTime(), 30); //takeoff time of inbound flight should be 30minutes later than arriving at destination
  /* out.println("desireDpTimeRe:"+desireDpTimeRe); */
  ArrayList<ArrayList<Flight>> allokFlightsRe = Search.doSearch(destinationApCd, sourceApCd, returnDay, desireDpTimeRe);
  if (allokFlightsRe.size() == 0) {
		    out.println("<h2 align='center' style='color:white'><strong>Oops, no inbound flight found! Try another Search!</strong></h2>"+ "<br>");%>
		    <div style="text-align:center"> <a href="WelcomeAndSearch.jsp"><font color="white" size="+2">Click here back to home page</font></a></div>
		 <%} 
  else {
		    request.setAttribute("allokFlightsRe", allokFlightsRe);
		    request.getRequestDispatcher("SearchSuccessInbound.jsp").forward(request, response);
       } 
	  // ArrayList<Line> allokLineRe1 = StoreLineData.getFlightsLine(Filter.doFilterBySeats1(allokFlightsRe, seat), seat);
	  // ArrayList<Line> allokLineRe2 = StoreLineData.getFlightsLine(Filter.doFilterBySeats2(allokFlightsRe, seat), seat); 
  %>
	   
   
</body>
</html>