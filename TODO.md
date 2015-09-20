JSP application that has Junit tests that talks to MySQL database.
 
Scenario:
* An employee (a contract driver) of MyShuttle.biz (think Uber) hits  a login page on their “intranet”
* Logs in (User ID (e-mail address?) and password
* Gets an “Employee Dashboard” page. We can work out UX but I’m thinking “tiles’ with fake options and one “live” one that if you click on it takes you too
* The third page which reads all the fares the driver had done grouped by date listing:
  * Pick up location
  * Drop off location
  * Start Time
  * End Time
  * Fare charged
  * Driver Amount (say 75% of fare or whatever Lyft or Uber does)
  * Rating by Passenger
  * Rating by Driver
* We need a script that adds new data for testing / demo purposes so the page can “update”
 
