# Flight-Booking-System

Use JAVA and JSP to design, functions are similar to KAYAK.

Fly By Night Airlines will be a new way to purchase airline flight tickets. We will allow customers to purchase them directly using a computer! With a few clicks they can search and reserve a plane ticket for a given day.

1 They can purchase a round trip or one way ticket.

2 They can specify the dates of travel.

3 They can specify the window of travel (when they leave, when they arrive).

4 They can specify first class or coach for the travel.

5 They can specify the airport they depart from and the airport they arrive at.

6 They will not be able to book a flight if it is already full.

7 They can choose the number of stopovers for the flight.

Each reservation is made through a travel agency or on-line booking facility. Representatives need to be able to undo reservations made previously. Other agencies or customers can be booking flights at the same time, so the number of available seats can be changing from actions that are not yours. Changes are effected in the Fly By Night travel database. You can lock the database while booking flights, then release the lock. Seats are not specifically assigned, the user is assigned either first class or coach, but not a specific seat.

The back-end database is accessible via http get commands with arguments

•	You can get a list of all airports

•	You can get a list of all airplane models

•	You can get a list of flights leaving from a specific airport on a specific day

•	You can get a list of flights arriving at a specific airport on a specific day

The data from the back-end database is returned via xml string. Times in the database are GMT time. Departure and arrival times should be shown in local time per airport. The time for layovers needs to be available. Travel time for each leg of a flight needs to be available.
