<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList, entity.Airport" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<style>
body {
    background-image:url("image/a04.jpg");
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>The First Page____Customer Search </title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script>
    function myFirstJS() {
        $("#myCheck").click(function () { 
       	 var x = document.getElementById("myCheck").checked;
       	 if(x==true) {
       		 $("[id=roundTrip]").show();
       		 var txt1 = "<th id='aTry'><strong><font color='#FFFFFF'>Returning on:</font></strong></th>";
        	 var txt2 = "<td id='bTry'><input type='text' name='returnDay' id='indate1'readonly onClick='WdatePicker()'/> </td>";
             $("[id=insertHere]").after(txt1,txt2);
       	 }
         else  {
        	 $("[id=aTry]").remove();
             $("[id=bTry]").remove();
            }
       	});
      }
    </script>
  <script language="javascript" type="text/javascript" src="datepicker/WdatePicker.js"></script>
</head>

<jsp:useBean id="getAirports" class="interactWithServer.GetAirports" scope="request"/>
<jsp:useBean id="parseAirports" class="parseXMLString.AirportParse" scope="request"/>
<%
   String ticketAgency = "Team04";
   String xml = getAirports.getData(ticketAgency);
   ArrayList<Airport> allAirports = parseAirports.loadXMLFromString(xml);
%>

<body onload="myFirstJS()"> 
<br><br><br>
 <table width="620" height="60" border="0" align="center" style="background-image:url(image/a01.gif)">
</table>
<hr>
<br><br>
<%session.invalidate(); %>
<br><br><br><br>
  <form action="DoSearch2.jsp" method="get" onsubmit="processing()">
     <table align="center">
       <tr>
           <th style="color:#FFFFFF">Going From:</th>
           <td>
              <select id="sourceApCd" name="sourceApCd">
              <%for (Airport oneAirport:allAirports){%>
                 <option value=<%=oneAirport.getCode()%>><%=oneAirport.getCode()%> (<%=oneAirport.getName()%>)</option> 
                 <%}%>  
              </select> 
           </td> 
        </tr>
        <tr>
           <th style="color:#FFFFFF">Going To:</th>
           <!-- <td><input type="text" name="avAirport"></td>  -->
            <td>
              <select id="destinationApCd" name="destinationApCd">
              <%for (Airport oneAirport:allAirports){%>
                 <option value=<%=oneAirport.getCode()%>><%=oneAirport.getCode()%> (<%=oneAirport.getName()%>)</option> 
                 <%}%>  
              </select> 
           </td>
       </tr>    
       <tr>    
           <th style="color:#FFFFFF">Departing on:</th>
           <td>
               <input type="text" name="day" id="indate" readonly onClick="WdatePicker()"/> 
           <!--<td><input type="text" name="returnDay" id="bTry" readonly onClick="dateSelect('bTry')"></td>--> 
       </tr>
       <tr id="insertHere">   
           <th style="color:#FFFFFF">Ticket Type:</th>
           <td><input type="checkbox" id="myCheck">Round Trip</td>  
           <!-- <td><input type="checkbox" name="roundOrOneway" value="OneWay">One Way</td> --> 
       </tr>
       <tr>    
           <th style="color:#FFFFFF">Seat:</th>
           <td>
              <select id="firstClassOrCoach" name="seat">
                 <option value="FirstClass">First-class</option>
                 <option value="Coach">Coach</option> 
              </select> 
           </td> 
       </tr>
       <tr>
         <td colspan="6" align="right"><input type="submit" value="Search"></td>
       </tr>
     </table>
  </form>
</body>
</html>