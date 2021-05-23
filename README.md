# momentum_active_shoppe

Environment setup instructions:
1. Have Java 8 or later version installed on your enviroment.
2. Have Maven setup on your environment.
3. Have MongoDB server version 4.2.13 installed in your envronment.
4. Do a git clone using command using git bash if you using windows or terminal if you using linux "git clone https://github.com/molebogenggiven/memontum.git" into your project workspace
5. Do "mvn clean install" in the same directory where your pom file is.
6. The above command will build the fat jar file then you can run your jar file in the target folder using "nohup java -jar demo.jar &"
3. Open your favourite browser and navigate to http://localhost:8080/swagger-ui.html to view services


Alternatively you can run application using your desired IDE.
1. Please ensure that the lombok plugins are installed on your IDE.


I have also deployed the aapplication on AWS EC2 instance:
-> The url to access the swagger document is http://ec2-13-245-21-79.af-south-1.compute.amazonaws.com:8080/swagger-ui.html#/

How to access test data:
-> When the application start up, the test data is saved in database in the main Application class.
-> for easy access to test test you can either use the mongoDB queries or the following services.
  -> /api/v1/product/getAllCustomers to view all the customers that were inserted in the database.
  -> /api/v1/product/getAllProducts to view all the products that were inserted in the database.


