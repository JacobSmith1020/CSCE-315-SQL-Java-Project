
# 315GroupProj1

# CSCE 315 Project 2 Run Instructions

# Make sure you have the docker yml file that looks like the one below
version: '3.3'
services:
  db:
    image: mysql:5.7
    restart: always
    environment:
      MYSQL_DATABASE: 'db'
      MYSQL_USER: 'root'
      MYSQL_PASSWORD: 'password'
      MYSQL_ROOT_PASSWORD: 'password'
    ports:
      - '13306:3306'
    expose:
      - '3306'
    volumes:
      - my-db:/var/lib/mysql
volumes:
  my-db:

# Start docker desktop: In command prompt:
	cd <Working directory> (In my case, C:\Users\Smith\Documents\Jacob's Folder\CSCE 315)
	docker-compose up -d
	docker ps
	(get the container ID from docker ps command)
	docker cp AWBackup.sql <container ID>:/
	docker exec -it <container ID> bin/bash
	mysql -u <username> -p
	<password>
	use db
	source AWBackup.sql

# In mySQL workbench:
	Click "Database" tab
	Click "Manage Connections"
	Choose "Hostname" to be "localhost" or whatever default ip address is in there (In my case it is 127.0.0.1)
	Choose "Port" to be the one in your docker yml file (In my case it is 13306)
	Choose "Username" to be root
	Choose "Password" to be password
	Click "Test Connection" and make sure it succeeds
	Go to startup/shutdown in administration and make sure the server is running 

# In Eclipse:
	Create a Maven Java project
	Right click on the project folder and select new>class and create a new class
	Copy over the skeleton code of JDBCExample into the new file you just created
	Change Class.forName("com.mysql.jdbc.Driver") to Class.forName("com.mysql.cj.jdbc.Driver")
	Change DB_URL to "jdbc:mysql://<ip address or localhost from mySQL>:<port used in mySQL and yml file>" (In my case it is "jdbc:mysql://127.0.0.1:13306")
	Right click project folder and select new>folder
	Copy the connector file into this folder (mysql-connector-java-8.0.21)
	Right click on project folder and select build path>configure build path>libraries
	Click on classpath and then click add external jars on the right and add the connector file (mysql-connector-java-8.0.21)
	Download xchart and add the associated .jar file to the classpath
	Run java file


	
	
	
