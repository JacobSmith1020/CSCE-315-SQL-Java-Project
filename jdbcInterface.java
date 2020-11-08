package jdbc;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections; 
import java.util.List;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.sql.SQLException;
import java.sql.*;
public class driver {	
	// Choe: This should work on your MySQL instance running on docker. Just
	// change the default username and password below.

	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://127.0.0.1:13306";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "password";
	   
	   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.cj.jdbc.Driver");	      
	      
	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      
	      System.out.print("Enter Query: ");
	      Scanner myScan = new Scanner(System.in);  // Create a Scanner object
	      String user_in= myScan.nextLine();  // Read user input
	      //STEP 4: Execute a query
	      stmt = conn.createStatement();
	      String sql;
	      sql = "USE adventureworks;";
	      ResultSet rs;
	      rs = stmt.executeQuery(sql);
	      sql = user_in;
	      String[] split_user = user_in.split(" ", 3);
	      
	      switch(split_user[0]){

	         case "jdb-show-related-tables":
	            sql = "select * from information_schema.columns where table_schema = 'adventureworks' and column_name = '" + split_user[1] + "ID';";
	            rs = stmt.executeQuery(sql);
	            System.out.println("Tables with primary key of " + split_user[1] + ":");
	            while(rs.next()){
		               System.out.println(rs.getString(3));
		            }
	            break;

	         case "jdb-show-all-primary-keys":
	            sql = "SELECT KCU.TABLE_NAME AS Table_Name, KCU.CONSTRAINT_NAME AS Constraint_Name, KCU.COLUMN_NAME AS COLUMN_NAME FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC JOIN INFORMATION_SCHEMA.KEY_COLUMN_USAGE KCU ON KCU.CONSTRAINT_SCHEMA = TC.CONSTRAINT_SCHEMA AND KCU.CONSTRAINT_NAME = TC.CONSTRAINT_NAME AND KCU.TABLE_SCHEMA = TC.TABLE_SCHEMA AND KCU.TABLE_NAME = TC.TABLE_NAME WHERE TC.CONSTRAINT_TYPE = 'PRIMARY KEY' AND KCU.TABLE_SCHEMA='adventureworks' ORDER BY KCU.TABLE_SCHEMA, KCU.TABLE_NAME, KCU.CONSTRAINT_NAME";
	            rs = stmt.executeQuery(sql);
	            while(rs.next()){
	               System.out.print(rs.getString(1) + ", ");
	               System.out.println(rs.getString(3));
	            }
	            break;
	  
	         case "jdb-find-column":
	            sql = "SELECT DISTINCT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME IN ('" + split_user[1] +"') AND TABLE_SCHEMA='adventureworks';";
	            rs = stmt.executeQuery(sql);

	            while(rs.next()){
	               System.out.println(rs.getString(1));
	            }
	            break;
	  
	         case "jdb-search-path":
	            sql = "SELECT fk.name 'FK Name', tp.name 'Parent table', cp.name, cp.column_id, tr.name 'Refrenced table', cr.name, cr.column_id FROM  sys.foreign_keys fk INNER JOIN  sys.tables tp ON fk.parent_object_id = tp.object_id INNER JOIN  sys.tables tr ON fk.referenced_object_id = tr.object_id INNER JOIN  sys.foreign_key_columns fkc ON fkc.constraint_object_id = fk.object_id INNER JOIN  sys.columns cp ON fkc.parent_column_id = cp.column_id AND fkc.parent_object_id = cp.object_id INNER JOIN  sys.columns cr ON fkc.referenced_column_id = cr.column_id AND fkc.referenced_object_id = cr.object_id ORDER BY tp.name, cp.column_id";
	            rs = stmt.executeQuery(sql);
	            break;
	  
	         case "jdb-search-and-join":
	            sql = "SELECT * FROM " + split_user[1] + ", " + split_user[2] + " FULL JOIN " + split_user[2] + " limit 0, 1000;";
	            rs = stmt.executeQuery(sql);
	            ResultSetMetaData rsmd3 = rs.getMetaData();
	            int columnsNumber3 = rsmd3.getColumnCount();
	            while (rs.next()) {
	               for (int i = 1; i <= columnsNumber3; i++) {
	                  if (i > 1) System.out.print(",  ");
	                  String columnValue = rs.getString(i);
	                  System.out.print(columnValue + " " + rsmd3.getColumnName(i));
	               }
	               System.out.println("");
	            }
	            break;
	   
	         case "jdb-get-view": //requires a view to be created beforehand which is a write command which is not supported by our project requirements
	            sql = split_user[2] + " FROM " + split_user[1] + ";";
	            rs = stmt.executeQuery(sql);
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int columnsNumber = rsmd.getColumnCount();
	            while (rs.next()) {
	               for (int i = 1; i <= columnsNumber; i++) {
	                  if (i > 1) System.out.print(",  ");
	                  String columnValue = rs.getString(i);
	                  System.out.print(columnValue + " " + rsmd.getColumnName(i));
	               }
	               System.out.println("");
	            }
	            break;
	  
	         case "jdb-stat":
	            sql = "select "+ split_user[2] + " from " + split_user[1] + ";";
	            rs = stmt.executeQuery(sql);
	            List <Double> stat_list = new ArrayList <Double> ();
	            while(rs.next()){
	               stat_list.add((Double)rs.getDouble(1));
	            }
	            Collections.sort(stat_list);
	            Double min = stat_list.get(0);
	            Double max = stat_list.get(stat_list.size() - 1);
	            System.out.println("Minimum is: " + min);
	            System.out.println("Maximum is: " + max);
	            Double total = 0.0;
	            for(int i = 0; i < stat_list.size(); i++) {
	            	//System.out.println(stat_list.get(i));
	            	total += stat_list.get(i);
	            }
	            Double average = total / stat_list.size();
	            System.out.println("Average is: " + average);
	            Double median = 0.0;
	            if (stat_list.size() % 2 != 0) //even num elements
	            	median = stat_list.get(stat_list.size() / 2);
	            else //odd num elements
	            	median = (stat_list.get((stat_list.size() - 1) / 2) + stat_list.get(stat_list.size() / 2)) / 2.0;
	            System.out.println("Median is: " + median);
	            
	            //histogram
	            HashMap<Double, Integer> m = new HashMap<Double, Integer>();

	            for (int i = 0; i < stat_list.size(); i++) {
	                int c = 0;

	                for (int j = 0; j < stat_list.size(); j++) {
	                    if (stat_list.get(i) == stat_list.get(j)) {
	                        c++;
	                    }
	                }
	                m.put(stat_list.get(i), c);

	            }

	            System.out.println("Histogram\n------------");
	            for (Map.Entry<Double, Integer> entry : m.entrySet()) {
	                Double key = entry.getKey();
	                int value = entry.getValue();
	                System.out.printf("%3f | ", key);
	                for (int i = 0; i < value; i++) {
	                    System.out.print("*");
	                }
	                System.out.print(" " + value + " Instance");
	                System.out.println();
	            }
	            break;
	            
	         case "jdb-total-num-orders": //custom query 1
	        	sql = "SELECT SUM(OrderQty) AS sum FROM salesorderdetail;";
	            rs = stmt.executeQuery(sql);
	            System.out.println("Total number of sales orders: ");
	            while(rs.next()){
	               System.out.println(rs.getString(1));
	            }
	            break;
	            
	         case "jdb-city-occurances": //custom query 2
	        	 sql = "SELECT City, count(City) FROM address Group by City;";
	        	 rs = stmt.executeQuery(sql);
	        	 System.out.println("Occurances of each city: ");
	        	 while(rs.next()){
		               System.out.print(rs.getString(1) + ", ");
		               System.out.println(rs.getString(2));
		         }
		         break;
		         
	         case "jdb-num-customers": //custom query 3
	        	 sql = "select customer.customerid from customer;";
	        	 rs = stmt.executeQuery(sql);
	        	 int j = 0;
	        	 while(rs.next()){
	        		 j++;
	             }
	        	 System.out.println("Total number of customers: " + j);
	             break;
	             
	         case "jdb-total-sales-amount": //custom query 4
	        	 sql = "SELECT SUM( OrderQty*UnitPrice) from salesorderdetail;";
	        	 rs = stmt.executeQuery(sql);
	        	 System.out.print("Total sales amount: ");
	        	 while(rs.next()){
	        		 System.out.println(rs.getString(1));
	             }
	             break;
	             
	         case "jdb-highest-rated-product": //custom query 5
	        	 sql = "SELECT ProductID FROM productreview GROUP BY ProductID ORDER BY COUNT(*) DESC LIMIT 1;";
	        	 rs = stmt.executeQuery(sql);
	        	 System.out.print("Highest rated product ID: ");
	        	 while(rs.next()){
	        		 System.out.println(rs.getString(1));
	             }
	             break;
	             
	         default:
	            rs = stmt.executeQuery(sql);
	            ResultSet resultSet = stmt.executeQuery(sql);
	            ResultSetMetaData rsmd2 = resultSet.getMetaData();
	            int columnsNumber2 = rsmd2.getColumnCount();
	            while (resultSet.next()) {
	               for (int i = 1; i <= columnsNumber2; i++) {
	                  if (i > 1) System.out.print(",  ");
	                  String columnValue = resultSet.getString(i);
	                  System.out.print(columnValue + " " + rsmd2.getColumnName(i));
	               }
	               System.out.println("");
	            }
	            break;

	      }
	      //STEP 4: Execute a query example
//	      System.out.println("Creating statement...");
//	      stmt = conn.createStatement();
//	      String sql;
//	      sql = "USE adventureworks;";
//	      ResultSet rs;
//	      rs = stmt.executeQuery(sql);
//	      sql = "SELECT EmployeeId, LoginID from employee";
//	      rs = stmt.executeQuery(sql);
//
//	      //STEP 5: Extract data from result set
//	      while(rs.next()){
//	         //Retrieve by column name
//	         int id  = rs.getInt("EmployeeID");
//	         String login = rs.getString("LoginID");
//
//	         //Display values
//	         System.out.print("ID: " + id);
//	         System.out.println(", Login: " + login);
//	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("END OF QUERY");
	}//end main
}//end FirstExample