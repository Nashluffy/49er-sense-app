IoT Repository


Finished 10/29:
*  Need to get scheduling right, we will store on the laptop /opt/lampp/htdocs
   *  We will store schedule in text, the format Appliance Name, Start Date (MM/YY/DD), Start Hour + Minute (HH,MM), Duration (MM)
   *  Update Spinner dynamically from text file
   *  Need a thread to read text file and start appliance if the it is the scheduled time

To do 10/30:
* Need to get security linked up
   *  Security should have a debug button to spin up a thread of test cases, like someone breaking in
   *  Need to print these events out to the notifications page
   
To do 10/31: 
*  Need to switch all calls of java appliance thread starts to PHP execute java 
   * Should use Volley and go through our LAMP User utility server
   
To do 11/1:
*  Need to linkup utility company login 
   *  Need to create LAMP server for utility
   *  Need to create landing page for utility users
