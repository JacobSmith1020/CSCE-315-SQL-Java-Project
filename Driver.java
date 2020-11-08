package banana;

import java.util.*;
import java.util.List;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.knowm.xchart.*;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.demo.charts.pie.PieChart02;
import java.awt.image.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import org.knowm.xchart.*;

public class Driver extends JFrame implements ActionListener {
	   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	   static final String DB_URL = "jdbc:mysql://127.0.0.1:13306";

	   // Database credentials
	   static final String USER = "root";
	   static final String PASS = "password";
	   private final static String newline = "\n";

	   static String user_in;
	   static String args = "";
	   

	   // Interface
	   static JTextField t;
	   static JTextArea textArea;
	   static JFrame frame;

	   static JButton button1;
	   static JButton button2;
	   static JButton button3;
	   static JButton button4;
	   static JButton button5;
	   static JButton button6;
	   static JButton button7;
	   static JButton button8;
	   static JButton button9;
	   static JButton button10;
	   static JButton button11;
	   static JButton button12;
	   static JButton button13;
	   static JButton button14;
	   static JButton button15;
	   static JButton button16;
	   static JLabel label;
	   static String data[][] = new String[5000][5000];
	   static String columns[] = new String[5];

	   Driver() {

	   }

	   // GUI
	   public static void createAndShowGUI() {
	      // Create and set up the window.
	      frame = new JFrame("HelloWorldSwing");

	      // addActionListener to button
	      Driver te = new Driver();
	      button1 = new JButton("jdb-show-all-primary-keys");
	      button2 = new JButton("jdb-show-related-tables");
	      button3 = new JButton("jdb-find-column");
	      button4 = new JButton("jdb-search-path");
	      button5 = new JButton("jdb-search-and-join");
	      button6 = new JButton("jdb-stat");
	      button7 = new JButton("jdb-total-num-orders");
	      button8 = new JButton("jdb-city-occurances");
	      button9 = new JButton("jdb-num-customers");
	      button10 = new JButton("jdb-total-sales-amount");
	      button11 = new JButton("jdb-highest-rated-product");
	      button12 = new JButton("jdb-show-tables");
	      button13 = new JButton("jdb-show-columns");
	      button14 = new JButton("jdb-join-tables");
	      button15 = new JButton("RAW");
	      button16 = new JButton("jdb-plot-schema");
	      button1.addActionListener(te);
	      button2.addActionListener(te);
	      button3.addActionListener(te);
	      button4.addActionListener(te);
	      button5.addActionListener(te);
	      button6.addActionListener(te);
	      button7.addActionListener(te);
	      button8.addActionListener(te);
	      button9.addActionListener(te);
	      button10.addActionListener(te);
	      button11.addActionListener(te);
	      button12.addActionListener(te);
	      button13.addActionListener(te);
	      button14.addActionListener(te);
	      button15.addActionListener(te);
	      button16.addActionListener(te);
	      t = new JTextField(1);

	      textArea = new JTextArea(10, 15);
	      textArea.setLineWrap(true);
	      textArea.setEditable(false);
	      textArea.setVisible(true);

	      // Add the ubiquitous "Hello World" label.
	      label = new JLabel("Input Function Arguments Here:");
	      frame.getContentPane().add(label);

	      // PANEL 1
	      JPanel panel1 = new JPanel();
	      panel1.setBorder(BorderFactory.createEmptyBorder(200, 200, 200, 200));

	      GridLayout grid = new GridLayout(4, 3);
	      grid.setHgap(10);
	      grid.setVgap(20);
	      panel1.setLayout(grid);
	      panel1.add(button1);
	      panel1.add(button2);
	      panel1.add(button3);
	      panel1.add(button4);
	      panel1.add(button5);
	      panel1.add(button6);
	      panel1.add(button7);
	      panel1.add(button8);
	      panel1.add(button9);
	      panel1.add(button10);
	      panel1.add(button11);
	      panel1.add(button12);
	      panel1.add(button13);
	      panel1.add(button14);
	      panel1.add(button15);
	      panel1.add(button16);
	      panel1.add(label);

	      panel1.add(t);
	      panel1.add(textArea);

	      JScrollPane scroll = new JScrollPane(textArea);
	      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	      scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	      panel1.add(scroll);

	      // PANEL 2
	      JPanel panel2 = new JPanel();
	      panel2.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
	      GridLayout grid2 = new GridLayout(2, 2);
	      grid2.setHgap(10);
	      grid2.setVgap(10);
	      panel2.setLayout(grid2);
	      
	      //panel 3
	        JPanel panel3 = new JPanel(); 
			JTable jt = new JTable(data, columns);
			JScrollPane sp = new JScrollPane(jt); 
			sp.setPreferredSize(new Dimension(1500, 900));
			panel3.add(sp);          
			panel3.setSize(1920,1080);    
	      
	      createGraphs("jdb-yearly-sales"); // Use xchart to make the graphs and export them as pictures
	      createGraphs("jdb-monthly-sales");
	      createGraphs("jdb-pie-product-categories");
	      createGraphs("jdb-sale-by-product");

	      // post pictures in the panel
	      BufferedImage graph1 = null;
	      BufferedImage graph2 = null;
	      BufferedImage graph3 = null;
	      BufferedImage graph4 = null;
	      
	      
	      
	      //BufferedImage image = ImageIO.read(getClass().getResource(fileName));
	      //ImageIO.read(new File("YearlySales.png"));
	      try {
	         graph1 = ImageIO.read(new File("YearlySales.png"));
	         graph2 = ImageIO.read(new File("MonthlySales.png"));
	         graph3 = ImageIO.read(new File("CategoryPieChart.png")); 
	         graph4 = ImageIO.read(new File("Salesbyproduct.png"));
	      } catch (IOException e) {
	         e.printStackTrace();
	      }

	      JLabel picLabel1 = new JLabel(new ImageIcon(graph1));
	      JLabel picLabel2 = new JLabel(new ImageIcon(graph2));
	      JLabel picLabel3 = new JLabel(new ImageIcon(graph3));
	      JLabel picLabel4 = new JLabel(new ImageIcon(graph4));
	      panel2.add(picLabel1);
	      panel2.add(picLabel2);
	      panel2.add(picLabel3);
	      panel2.add(picLabel4);



	      // Display the window.
	      JTabbedPane tabPane = new JTabbedPane();
	      tabPane.add("GUI", panel1);
	      tabPane.add("GUI Output", panel3);
	      tabPane.add("Stats Dashboard", panel2);
	      

	      frame.add(tabPane);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setTitle("JDBC GUI");
	      frame.pack();
	      frame.setVisible(true);

	   }

	   public void actionPerformed(ActionEvent e) {
	      String s = e.getActionCommand();
	      // Button press -> user_in = jdbc function
	      // Text input + Submit button -> user_in = function + parameter
	      args = t.getText();
	      
	      
	      user_in = s + " " + args;
	      System.out.println(user_in);
	      for( int i = 0; i < data.length; i++) {
	    	  Arrays.fill(data[i], "");
	      }
	      
	      runDB();
	      
	      // set the text of field to blank
	      t.setText("");
	      
	   }
	   public static void createGraphs(String graphCommand) {
		   user_in = graphCommand;
		   runDB();
		   
	   }

	   
	   public static void runDB() {

		      Connection conn = null;
		      Statement stmt = null;
		      try {
		         // STEP 2: Register JDBC driver
		         Class.forName("com.mysql.cj.jdbc.Driver");

		         // STEP 3: Open a connection
		         System.out.println("Connecting to database...");
		         conn = DriverManager.getConnection(DB_URL, USER, PASS);

		         Scanner myScan = new Scanner(System.in); // Create a Scanner object
		         // String user_in= myScan.nextLine(); // Read user input
		         // STEP 4: Execute a query
		         stmt = conn.createStatement();
		         String sql;
		         sql = "USE adventureworks;";
		         ResultSet rs;
		         rs = stmt.executeQuery(sql);
		         sql = user_in;
		         String[] split_user = user_in.split(" ", 3);
		     
		         String UIOut = "";
		         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		  	     int screenHeight = screenSize.height;
		  	     int screenWidth = screenSize.width;

		         switch (split_user[0]) {
		         
		         	case "jdb-yearly-sales":
		         		Vector<Double> xData1 = new Vector<Double>(100000000);
		         		Double xTotal1 = 0.0;
		         	    Vector<Double> yData1 = new Vector<Double>(100000000);
		         	    String tempyData1 = "";
		         	    Double yearOn = 2001.0;
		         		sql = "select salesorderheader.TotalDue,salesorderheader.OrderDate from salesorderheader;";
		         		rs = stmt.executeQuery(sql);
		         		while (rs.next()) {
		         			tempyData1 = rs.getString(2);
		         			tempyData1 = tempyData1.substring(0, 4);
		         			if(yearOn == Double.parseDouble(tempyData1)) {
		         				xTotal1 += rs.getDouble(1);
		         			}
		         			else {
		         				yearOn += 1.0;
		         				xData1.add(xTotal1);
		         				yData1.add(Double.parseDouble(tempyData1));
		         				xTotal1 = 0.0;
		         			}
			            }
		         		//XYChart chart1 = QuickChart.getChart("Yearly Sales", "Year", "Sales($)", "y(x)", yData1, xData1);
		         		XYChart chart1 = new XYChartBuilder().width(screenWidth / 2 - 100).height(screenHeight / 2 - 100).title("Yearly Sales").xAxisTitle("Year").yAxisTitle("Sales($)").build();
		         		chart1.addSeries("y(x)", yData1, xData1);
		         		chart1.getStyler().setChartBackgroundColor(new Color(178, 93, 48));
		         		chart1.getStyler().setXAxisDecimalPattern("####.#");
		         		BitmapEncoder.saveBitmap(chart1, "./YearlySales", BitmapFormat.PNG);
		         		break;
		         	
		         	case "jdb-monthly-sales":
		         		Vector<Double> xData2 = new Vector<Double>(100000000);
		         		Double xTotal2 = 0.0;
		         	    Vector<Double> yData2 = new Vector<Double>(100000000);
		         	    String tempyData2 = "";
		         	    String tempyData2_1 = "";
		         	    Double monthOn = 7.0;
		         	    //select salesorderheader.TotalDue,salesorderheader.OrderDate from salesorderheader Limit 0, 3000;
		         		sql = "select salesorderheader.TotalDue,salesorderheader.OrderDate from salesorderheader Limit 1380, 3500;";
		         		rs = stmt.executeQuery(sql);
		         		while (rs.next()) {
		         			tempyData2 = rs.getString(2);
		         			tempyData2 = tempyData2.substring(5, 7);
		         			tempyData2_1 = rs.getString(2);
		         			tempyData2_1 = tempyData2_1.substring(0, 4);
		         			if(monthOn == Double.parseDouble(tempyData2)) {
		         				xTotal2 += rs.getDouble(1);
		         			}
		         			else {
		         				if(monthOn == 12.0) {
		         					//xData2.add(xTotal2);
//		         					tempxData2 = xData2.get(monthOn.intValue() - 1);
//		         					xData2.set(monthOn.intValue() - 1, xTotal2 + tempxData2);
		         					xData2.add(xTotal2);
			         				yData2.add(Double.parseDouble(tempyData2));
			         				monthOn = 1.0;
			         				xTotal2 = 0.0;
		         				}
		         				else {
//		         					tempxData2 = xData2.get(monthOn.intValue() - 1);
//		         					xData2.set(monthOn.intValue() - 1, xTotal2 + tempxData2);
		         					xData2.add(xTotal2);
			         				yData2.add(Double.parseDouble(tempyData2));
			         				monthOn += 1.0;
			         				xTotal2 = 0.0;
			         				//System.out.println(xTotal);
		         				}
		         			}
			            }
		         		//XYChart chart2 = QuickChart.getChart("Monthly Sales for 2002", "Month", "Sales($)", "y(x)", yData2, xData2);
		         		XYChart chart2 = new XYChartBuilder().width(screenWidth / 2 - 100).height(screenHeight / 2 - 100).title("Monthly sales for 2002").xAxisTitle("Month").yAxisTitle("Sales($)").build();
		         		chart2.addSeries("y(x)", yData2, xData2);
		         		chart2.getStyler().setChartBackgroundColor(new Color(212, 175, 55));
		         		BitmapEncoder.saveBitmap(chart2, "./MonthlySales", BitmapFormat.PNG);
		         		break;
		         		
		         	case "jdb-pie-product-categories":
		         		
		         		int p1 = 0;
		         		int p2 = 0;
		         		int p3 = 0;
		         		int p4 = 0;
		         	
		         		sql = "select count(*) from productsubcategory where ProductCategoryID = 1;";
		         		rs = stmt.executeQuery(sql);
		         		while (rs.next()) {
		         			p1 = Integer.parseInt(rs.getString(1));
			            }
		         		
		         		sql = "select count(*) from productsubcategory where ProductCategoryID = 2;";
		         		rs = stmt.executeQuery(sql);
		         		while (rs.next()) {
		         			p2 = Integer.parseInt(rs.getString(1));
			            }
		         		
		         		sql = "select count(*) from productsubcategory where ProductCategoryID = 3;";
		         		rs = stmt.executeQuery(sql);
		         		while (rs.next()) {
		         			p3 = Integer.parseInt(rs.getString(1));
			            }
		         		
		         		sql = "select count(*) from productsubcategory where ProductCategoryID = 4;";
		         		rs = stmt.executeQuery(sql);
		         		while (rs.next()) {
		         			p4 = Integer.parseInt(rs.getString(1));
			            }
		         		
		         		
		         		PieChart chart = new PieChartBuilder().width(screenWidth / 2 - 100).height(screenHeight / 2 - 100).title("Percentage of Items Sold").build();
		         		
		         		Color[] sliceColors = new Color[] { new Color(224, 68, 14), new Color(230, 105, 62), new Color(236, 143, 110), new Color(243, 180, 159)};
		         	    chart.getStyler().setSeriesColors(sliceColors);
		         	    chart.getStyler().setChartBackgroundColor(new Color(212, 175, 55));
		         	    
		         	    chart.addSeries("Bikes", p1);
		         	    chart.addSeries("Components", p2);
		         	    chart.addSeries("Clothing", p3);
		         	    chart.addSeries("Accessories", p4);
		         	    
		         	    BitmapEncoder.saveBitmap(chart, "./CategoryPieChart", BitmapFormat.PNG);
		         		break;
		         		
		         	case "jdb-sale-by-product":
		         		Vector<String> xData3 = new Vector<String>(100000000);
		         	    Vector<Double> yData3 = new Vector<Double>(100000000);
		         	    Vector<Integer> productID1 = new Vector<Integer>(1000);
		         	    Vector<Double> unitPrice = new Vector<Double>(100000000);
		         	    Vector<Integer> productID2 = new Vector<Integer>(1000);
		         	    Vector<Integer> productSubID = new Vector<Integer>(1000);
		         	    Vector<Integer> productCatID3 = new Vector<Integer>(1000);
		         	    Double cat1 = 0.0;
		         	    Double cat2 = 0.0;
		         	    Double cat3 = 0.0;
		         	    Double cat4 = 0.0;
//		         	    xData3.addAll(Arrays.asList(new Double[] { 0.0, 1.0, 2.0, 3.0, 4.0 }));
//		         	    yData3.addAll(Arrays.asList(new Double[] { 2.0, 4.0, 5.0, 2.0, 8.0 }));
		         		sql = "select productID, unitprice from salesorderdetail;";
		         		rs = stmt.executeQuery(sql);
		         		while(rs.next()) {
		         			productID1.add(rs.getInt(1));
		         			unitPrice.add(rs.getDouble(2));
		         		}
		         		sql = "select productID, productsubcategoryID from product;";
		         		rs = stmt.executeQuery(sql);
		         		while(rs.next()) {
		         			productID2.add(rs.getInt(1));
		         			productSubID.add(rs.getInt(2));
		         		}
		         		sql = "select productcategoryID from productsubcategory;";
		         		rs = stmt.executeQuery(sql);
		         		while(rs.next()) {
		         			productCatID3.add(rs.getInt(1));
		         		}
		         		for(int i = 0; i < productID1.size(); i++) {
		         			Double price = unitPrice.elementAt(i);
		         			Integer tempProductID = productID1.elementAt(i);
		         			Integer tempProductSubID = productSubID.elementAt(productID2.indexOf(tempProductID));
		         			Integer temp_Cat_ID = productCatID3.elementAt(tempProductSubID - 1);
		         			
		         			if(temp_Cat_ID == 1) {
		         				cat1 += price;
		         			}
		         			else if(temp_Cat_ID == 2) {
		         				cat2 += price;
		         			}
		         			else if(temp_Cat_ID == 3) {
		         				cat3 += price;
		         			}
		         			else if(temp_Cat_ID == 4) {
		         				cat4 += price;
		         			}
		         		}     	
		         		//yData3.addAll(Arrays.asList(new Double[] {cat1, cat2, cat3, cat4}));
		         		//xData3.addAll(Arrays.asList(new String[] {"1", "2", "3", "4"}));
		         		CategoryChart chart3 = new CategoryChartBuilder().width(screenWidth / 2 - 100).height(screenHeight / 2 - 100).title("Sales by Category").yAxisTitle("Sales($)").build();
		         		chart3.addSeries("1 = Bikes", Arrays.asList(new Integer[] {1}), Arrays.asList(new Double[] {cat1}));
		         		chart3.addSeries("2 = Bikes", Arrays.asList(new Integer[] {2}), Arrays.asList(new Double[] {cat2}));
		         		chart3.addSeries("3 = Bikes", Arrays.asList(new Integer[] {3}), Arrays.asList(new Double[] {cat3}));
		         		chart3.addSeries("4 = Bikes", Arrays.asList(new Integer[] {4}), Arrays.asList(new Double[] {cat4}));
		         		chart3.getStyler().setChartBackgroundColor(new Color(178, 93, 48));
		         		chart3.getStyler().setXAxisTicksVisible(false);
		         		BitmapEncoder.saveBitmap(chart3, "./Salesbyproduct", BitmapFormat.PNG);
		         		break;

		            case "jdb-show-related-tables":
		            	String arr[] = {};
		            	
		               sql = "select * from information_schema.columns where table_schema = 'adventureworks' and column_name = '"
		                     + split_user[1] + "ID';";
		               rs = stmt.executeQuery(sql);
		               textArea.setText(null);
		               System.out.println("Tables with primary key of " + split_user[1] + ":");
		               int a = 0;
		               while (rs.next()) {
		                  System.out.println(rs.getString(3));
		                  List<String> arrlist = new ArrayList<String>(Arrays.asList(arr)); 
		    		      
			              arrlist.add(rs.getString(3)); 
			      
			              arr = arrlist.toArray(arr); 
			              
			              data[a][0] = arr[a];
			              a += 1;

		                  UIOut = rs.getString(3);
		                  textArea.append(UIOut + newline);  
		               }
		               
		               break;

		            case "jdb-show-all-primary-keys":
		            	String arr2[] = {};
		               sql = "SELECT KCU.TABLE_NAME AS Table_Name, KCU.CONSTRAINT_NAME AS Constraint_Name, KCU.COLUMN_NAME AS COLUMN_NAME FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC JOIN INFORMATION_SCHEMA.KEY_COLUMN_USAGE KCU ON KCU.CONSTRAINT_SCHEMA = TC.CONSTRAINT_SCHEMA AND KCU.CONSTRAINT_NAME = TC.CONSTRAINT_NAME AND KCU.TABLE_SCHEMA = TC.TABLE_SCHEMA AND KCU.TABLE_NAME = TC.TABLE_NAME WHERE TC.CONSTRAINT_TYPE = 'PRIMARY KEY' AND KCU.TABLE_SCHEMA='adventureworks' ORDER BY KCU.TABLE_SCHEMA, KCU.TABLE_NAME, KCU.CONSTRAINT_NAME";
		               rs = stmt.executeQuery(sql);
		               textArea.setText(null);
		               int b = 0;
		               int b1 = 0;
		               while (rs.next()) {
		                  System.out.print(rs.getString(1) + ", ");
		                  System.out.println(rs.getString(3));
		                  List<String> arrlist2 = new ArrayList<String>(Arrays.asList(arr2)); 
		    		      
			              arrlist2.add(rs.getString(1)); 
			              arrlist2.add(rs.getString(3)); 
			      
			              arr2 = arrlist2.toArray(arr2);
			              
			              
			              data[b][0] = arr2[b1];
			              data[b][1] = arr2[b1 + 1];
			              b += 1;
			              b1 += 2;

		                  UIOut =  rs.getString(1) + ", " + rs.getString(3);
		                  textArea.append(UIOut + newline);
		               }
		               


		               break;

		            case "jdb-find-column":
		            	String arr3[] = {};
		               sql = "SELECT DISTINCT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME IN ('"
		                     + split_user[1] + "') AND TABLE_SCHEMA='adventureworks';";
		               rs = stmt.executeQuery(sql);

		               textArea.setText(null);
		               int d = 0;
		               while (rs.next()) {
		                  System.out.println(rs.getString(1));
		                  List<String> arrlist = new ArrayList<String>(Arrays.asList(arr3)); 
		    		      
			              arrlist.add(rs.getString(1)); 
			      
			              arr3 = arrlist.toArray(arr3); 
			              
			              data[d][0] = arr3[d];
			              d += 1;
		                  UIOut = rs.getString(1);
		                  textArea.append(UIOut + newline);
		               }
		               break;

		            case "jdb-search-path":
		               sql = "SELECT fk.name 'FK Name', tp.name 'Parent table', cp.name, cp.column_id, tr.name 'Refrenced table', cr.name, cr.column_id FROM  sys.foreign_keys fk INNER JOIN  sys.tables tp ON fk.parent_object_id = tp.object_id INNER JOIN  sys.tables tr ON fk.referenced_object_id = tr.object_id INNER JOIN  sys.foreign_key_columns fkc ON fkc.constraint_object_id = fk.object_id INNER JOIN  sys.columns cp ON fkc.parent_column_id = cp.column_id AND fkc.parent_object_id = cp.object_id INNER JOIN  sys.columns cr ON fkc.referenced_column_id = cr.column_id AND fkc.referenced_object_id = cr.object_id ORDER BY tp.name, cp.column_id";
		               rs = stmt.executeQuery(sql);
		               break;

		            case "jdb-search-and-join":
		               sql = "SELECT * FROM " + split_user[1] + ", " + split_user[2] + " FULL JOIN " + split_user[2] + " limit 0, 50;";
		               rs = stmt.executeQuery(sql);
		               ResultSetMetaData rsmd3 = rs.getMetaData();
		               int columnsNumber3 = rsmd3.getColumnCount();

		               textArea.setText(null);
		               while (rs.next()) {
		                  for (int i = 1; i <= columnsNumber3; i++) {
		                     if (i > 1){
		                        System.out.print(",  ");
		                        textArea.append(",  ");
		                     }

		                     String columnValue = rs.getString(i);
		                     System.out.print(columnValue + " " + rsmd3.getColumnName(i));
		                     textArea.append(columnValue + " " + rsmd3.getColumnName(i));
		                  }
		                  System.out.println("");
		                  textArea.append(newline);
		               }
		               break;

		            case "jdb-get-view": // requires a view to be created beforehand which is a write command which is
		                                 // not supported by our project requirements
		               sql = split_user[2] + " FROM " + split_user[1] + ";";
		               rs = stmt.executeQuery(sql);
		               ResultSetMetaData rsmd = rs.getMetaData();
		               int columnsNumber = rsmd.getColumnCount();
		               while (rs.next()) {
		                  for (int i = 1; i <= columnsNumber; i++) {
		                     if (i > 1)
		                        System.out.print(",  ");
		                     String columnValue = rs.getString(i);
		                     System.out.print(columnValue + " " + rsmd.getColumnName(i));
		                  }
		                  System.out.println("");
		               }
		               break;

		            case "jdb-stat":
		               sql = "select " + split_user[2] + " from " + split_user[1] + ";";
		               rs = stmt.executeQuery(sql);
		               List<Double> stat_list = new ArrayList<Double>();
		               textArea.setText(null);
		               while (rs.next()) {
		                  stat_list.add((Double) rs.getDouble(1));
		               }
		               Collections.sort(stat_list);
		               Double min = stat_list.get(0);
		               Double max = stat_list.get(stat_list.size() - 1);
		               System.out.println("Minimum is: " + min);
		               textArea.append("Minimum is: " + min + newline);
		               data[0][0] = "Minimum:";
		               data[0][1] = min.toString();
		               System.out.println("Maximum is: " + max);
		               textArea.append("Maximum is: " + max + newline);
		               data[1][0] = "Maximum:";
		               data[1][1] = max.toString();
		               Double total = 0.0;
		               for (int i = 0; i < stat_list.size(); i++) {
		                  // System.out.println(stat_list.get(i));
		                  total += stat_list.get(i);
		               }
		               Double average = total / stat_list.size();
		               System.out.println("Average is: " + average);
		               textArea.append("Average is: " + average + newline);
		               data[2][0] = "Average:";
		               data[2][1] = average.toString();
		               Double median = 0.0;
		               if (stat_list.size() % 2 != 0) // even num elements
		                  median = stat_list.get(stat_list.size() / 2);
		               else // odd num elements
		                  median = (stat_list.get((stat_list.size() - 1) / 2) + stat_list.get(stat_list.size() / 2)) / 2.0;
		               System.out.println("Median is: " + median);
		               textArea.append("Median is: " + median + newline);
		               data[3][0] = "Median:";
		               data[3][1] = median.toString();

		               // histogram
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
		                  String temp = String.format("%3f | ", key);
		                  textArea.append(temp);
		                  System.out.println(temp);
		                  for (int i = 0; i < value; i++) {
		                     System.out.print("*");
		                     textArea.append("*");
		                  }
		                  System.out.print(" " + value + " Instance");
		                  textArea.append(" " + value + " Instance" + newline);
		                  System.out.println();
		               }
		               break;

		            case "jdb-total-num-orders": // custom query 1
		               sql = "SELECT SUM(OrderQty) AS sum FROM salesorderdetail;";
		               rs = stmt.executeQuery(sql);
		               textArea.setText(null);

		               System.out.println("Total number of sales orders: ");
		               textArea.append("Total number of sales orders: " + newline);
		               
		               while (rs.next()) {
		                  System.out.println(rs.getString(1));
		                  textArea.append(rs.getString(1) + newline);
		                  data[0][0] = "Total number of sales orders:";
			              data[0][1] = rs.getString(1);
		               }
		               break;

		            case "jdb-city-occurances": // custom query 2
		               sql = "SELECT City, count(City) FROM address Group by City;";
		               rs = stmt.executeQuery(sql);
		               data[0][0] = "Occurances of cities:";
		               textArea.setText(null);
		               System.out.println("Occurances of each city: ");
		               textArea.append("Occurances of each city: " + newline);
		               int z = 1;
		               while (rs.next()) {
		                  System.out.print(rs.getString(1) + ", ");
		                  textArea.append(rs.getString(1) + ", ");
		                  data[z][0] = rs.getString(1);
		                  z += 1;
		               }
		               break;

		            case "jdb-num-customers": // custom query 3
		               sql = "select customer.customerid from customer;";
		               rs = stmt.executeQuery(sql);
		               textArea.setText(null);
		               data[0][0] = "Total number of customers:";

		               Integer j = 0;
		               while (rs.next()) {
		                  j++;
		               }
		               System.out.println("Total number of customers: " + j);
		               textArea.append("Total number of customers: " + j + newline);
		               data[0][1] = j.toString();
		               break;

		            case "jdb-total-sales-amount": // custom query 4
		               sql = "SELECT SUM( OrderQty*UnitPrice) from salesorderdetail;";
		               rs = stmt.executeQuery(sql);
		               textArea.setText(null);
		               data[0][0] = "Total sales amount:";
		               

		               System.out.print("Total sales amount: ");
		               textArea.append("Total sales amount: " + newline);
		               while (rs.next()) {
		                  System.out.println(rs.getString(1));
		                  textArea.append(rs.getString(1) + newline);
		                  data[0][1] = rs.getString(1);
		               }
		               break;

		            case "jdb-highest-rated-product": // custom query 5
		               sql = "SELECT ProductID FROM productreview GROUP BY ProductID ORDER BY COUNT(*) DESC LIMIT 1;";
		               rs = stmt.executeQuery(sql);
		               textArea.setText(null);
		               data[0][0] = "Highest rated product ID:";

		               System.out.print("Highest rated product ID: ");
		               textArea.append("Highest rated product ID: " + newline);
		               while (rs.next()) {
		                  System.out.println(rs.getString(1));
		                  textArea.append(rs.getString(1) + newline);
		                  data[0][1] = rs.getString(1);
		               }
		               break;

		            case "jdb-show-tables":
		               sql = "show tables;";
		               rs = stmt.executeQuery(sql);
		               textArea.setText(null);
		               
		               int x = 0;
		               while (rs.next()) {
		                  System.out.println(rs.getString(1));

		                  UIOut = rs.getString(1);
		                  textArea.append(UIOut + newline);
		                  data[x][0] = rs.getString(1);
		                  x += 1;
		               }
		               break;

		            case "jdb-show-columns":
		               //README COLUMNS NEED TO BE SEPARATED WITH COMMAS AND NO SPACES
		               //jdb-show-columns <column names> <table>

		               int num_of_cols = (split_user[1].split(",")).length;
		               sql = "SELECT " + split_user[1] + " FROM " + split_user[2] + ";";
		               rs = stmt.executeQuery(sql);
		               textArea.setText(null);
		               
		               int q = 0;
		               while (rs.next()) {

		                  for(int i = 0; i < num_of_cols; i++){
		                     UIOut = rs.getString(i + 1);
		                     textArea.append(UIOut + " ");
			                  if(q < 5000) {
			                	  data[q][i] = rs.getString(i + 1);
			                  }
		                  }
		                  q += 1;
		                  textArea.append(newline);
		               }
		               break;

		            case "jdb-join-tables":
		             //README jdb-join-tables <table> <table> <table> <table>
		               sql = "SELECT * FROM " + split_user[1] + ", " + split_user[2] + " FULL JOIN " + split_user[2] + " limit 0, 50;";
		               
		               rs = stmt.executeQuery(sql);
		               ResultSetMetaData rsmd4 = rs.getMetaData();
		               int columnsNumber4 = rsmd4.getColumnCount();

		               textArea.setText(null);
		               while (rs.next()) {
		                  for (int i = 1; i <= columnsNumber4; i++) {
		                     if (i > 1){
		                        System.out.print(",  ");
		                        textArea.append(",  ");
		                     }

		                     String columnValue = rs.getString(i);
		                     System.out.print(columnValue + " " + rsmd4.getColumnName(i));
		                     textArea.append(columnValue + " " + rsmd4.getColumnName(i));
		                  }
		                  System.out.println("");
		                  textArea.append(newline);
		               }
		               break;

		            case "RAW":
		            	sql = sql.substring(4);
		            
		            	//sql = sql + ";";
		              // rs = stmt.executeQuery(sql);
		            	
		               ResultSet resultSet = stmt.executeQuery(sql);
		               ResultSetMetaData rsmd2 = resultSet.getMetaData();
		               int columnsNumber2 = rsmd2.getColumnCount();
		               
		               textArea.setText(null);
		               while (resultSet.next()) {
		                  for (int i = 1; i <= columnsNumber2; i++) {
		                     if (i > 1)
		                        System.out.print(",  ");
		                     String columnValue = resultSet.getString(i);
		                     System.out.print(columnValue + " " + rsmd2.getColumnName(i));
		                     textArea.append(columnValue + " " + rsmd2.getColumnName(i));
		                     
		                  }
		                  System.out.println("");
		                  textArea.append(newline);
		               }
		               break;
		               
		            case "jdb-plot-schema":
		                //schema and graphviz code
		            	
		            	GraphTest graphTest = new GraphTest();
		            	
		            	graphTest.RenderSchema();
		            
		            	BufferedImage img = null;
		            	try {
		            	    img = ImageIO.read(new File("example/exampleSchema.png"));
		            	} catch (IOException e) {
		            		System.out.println("oh god it is broken.");
		            	}
		            	
		            	JFrame schemaFrame = new JFrame("Schema Graph");
		            	schemaFrame.add(new JLabel(new ImageIcon("example/exampleSchema.png")));
		            	schemaFrame.pack();
		            	schemaFrame.setVisible(true);
		            	break;
		         }

		         // STEP 6: Clean-up environment
		         myScan.close();
		         rs.close();
		         stmt.close();
		         conn.close();
		      } catch (SQLException se) {
		         // Handle errors for JDBC
		         se.printStackTrace();
		      } catch (Exception e) {
		         // Handle errors for Class.forName
		         e.printStackTrace();
		      } finally {
		         // finally block used to close resources
		         try {
		            if (stmt != null)
		               stmt.close();
		         } catch (SQLException se2) {
		         } // nothing we can do
		         try {
		            if (conn != null)
		               conn.close();
		         } catch (SQLException se) {
		            se.printStackTrace();
		         } // end finally try
		      } // end try
		      System.out.println("END OF QUERY");

		   }
	   
		
	   public static void main(String[] args) {
		      // GUI CALL
		   	columns[0] = "Column";
		   	columns[1] = "Column";
		   	columns[2] = "Column";
		   	columns[3] = "Column";
		   	columns[4] = "Column";
		   	

		      javax.swing.SwingUtilities.invokeLater(new Runnable() {
		         public void run() {
		            createAndShowGUI();
		         }
		      });
	
	   }
}




