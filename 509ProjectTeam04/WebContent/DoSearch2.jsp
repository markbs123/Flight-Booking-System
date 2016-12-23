<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.net.*, java.io.*, java.util.*, java.text.SimpleDateFormat, entity.Flight, functions.Search" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body {
    background-image:url("image/a04.jpg");
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Search processing</title>
    <jsp:useBean id="getFlights" class="interactWithServer.GetDpFlights" scope="request"/>
    <jsp:useBean id="parseFlights" class="parseXMLString.FlightParse" scope="request"/>   
</head>
<body>
<br><br><br>
 <table width="620" height="60" border="0" align="center" style="background-image:url(image/a01.gif)">
</table>
<hr>
<br><br>
<br><br><br><br>
    <%
    	// 通过request内置对象获得客户输入的信息
        String sourceApCd = request.getParameter("sourceApCd");
        String destinationApCd = request.getParameter("destinationApCd");
        String departDay = request.getParameter("day");
        //String departDay = "2015_05_13"; //wrong format
        //Date desireDpTime = TimeRelated.FindTheDate(departDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        String seat = request.getParameter("seat");
        String returnDay = request.getParameter("returnDay");
        
        try{
        	 Date desireDpTime = sdf.parse(departDay);
        	 /* out.println("departDay:"+departDay+"<br>");
        	 out.println("desireDpTime:"+desireDpTime+"<br>"); */
             ArrayList<ArrayList<Flight>> allokFlights = Search.doSearch(sourceApCd, destinationApCd, departDay, desireDpTime);
             /* out.println("size of allokFlights: "+allokFlights.size());  */
            
    	     if (allokFlights.size() == 0) {
    	    	if(returnDay != null){
    		       out.println("<h2 align='center'><strong>Oops, no outbound flight found! Try another depart day!</strong></h2>"+ "<br>");
    	    	}
    	    	else
    	    	   out.println("<h2 align='center'><strong>Oops, no flight found! Try another day!</strong></h2>"+ "<br>");
    		 } 
    	       else {
    		    	request.setAttribute("allokFlights", allokFlights);
    		    	session.setAttribute("sourceApCd",sourceApCd);
    		    	session.setAttribute("destinationApCd",destinationApCd);
    		        session.setAttribute("departDay", departDay);
       		        session.setAttribute("returnDay",returnDay);
       		        session.setAttribute("seat", seat);
    		    	request.getRequestDispatcher("SearchSuccess2.jsp").forward(request, response);}
            }//end try 
          catch (Exception e) {
          }
      
    %>
    <!-- <h2 align='center'><strong>Time Out!</strong></h2> -->
    <div style="text-align:center;">
    <a href="WelcomeAndSearch.jsp">Click Here to Search Again!</a>
    </div>
</body>
</html>