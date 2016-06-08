package jko;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) throws IOException, UnsupportedEncodingException {
    	
        System.out.println("Hello world!");
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss zzz");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        String currentTime = sdf.format(cal.getTime());
        System.out.println( "CurrentTime: " + currentTime );
        
        String filepath =  System.getenv("OPENSHIFT_DATA_DIR");
        
        System.out.println("File path: " + filepath);
        
        filepath = filepath + "java_output1.txt";
        
        System.out.println("File path: " + filepath);
        
        PrintWriter writer = new PrintWriter(filepath, "UTF-8");
        writer.println("The first line");
        writer.println("The second line");
        writer.println("CurrentTime: " + currentTime);
        
        String dbString = connectToDB();
        
        writer.println("DB output: ");
        writer.println(dbString);
        
        writer.close();
        
        System.out.println("End.");
        
        printEvnProperties();
        
        printCETime();
           
    }
    
    private static String connectToDB() {
    	
    	StringBuilder sb = new StringBuilder("");
    	
    	String USERNAME = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
    	String PASSWORD = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
    	String DB_NAME = System.getenv("OPENSHIFT_APP_NAME");
    	//String FORNAME_URL = "com.mysql.jdbc.Driver";
    	//String URL = "jdbc:"+System.getenv("OPENSHIFT_MYSQL_DB_URL") + DB_NAME;
    	String PORT 	= System.getenv("OPENSHIFT_MYSQL_DB_PORT");			// The MySQL server port
    	String URL = "jdbc:mysql://"+System.getenv("OPENSHIFT_MYSQL_DB_HOST") + ":" + PORT + "/" + DB_NAME;
    	
		System.out.println("USERNAME: " + USERNAME);
		System.out.println("PASSWORD: " + PASSWORD);
		System.out.println("DB_NAME:  " + DB_NAME);
		System.out.println();
		System.out.println("URL: " + URL);

		Connection connection = null;
    	
    	try {

    		connection = DriverManager.getConnection(URL , USERNAME , PASSWORD);
			
			Statement statement = connection.createStatement();
			
			String sql = "SELECT 1 + 1 FROM DUAL";
			
			sb.append("SQL : " + sql + "\n");
			
			ResultSet rs = statement.executeQuery(sql);
			
			//sb.append(rs.toString() + "\n");

			while (rs.next()) {

				System.out.println("Has next: true");

				//String lastName = rs.getString("Lname");
				int number = rs.getInt(1); // Column Index starts from 1 (not 0) !
				
				sb.append("Result: " + number + "\n");
				
				System.out.println("Number: " + number);
				
			}
			
		} catch (SQLException e) {
			System.err.println("DB connection ERROR: " + e);
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println("DB connection disconecting ERROR: " + e);
				e.printStackTrace();
			}
		}
    	
    	return sb.toString();
    	
    }
    
    public static void printEvnProperties() {
    	
    	String HOST		= System.getenv("OPENSHIFT_MYSQL_DB_HOST");			// The MySQL server IP address
    	String PORT 	= System.getenv("OPENSHIFT_MYSQL_DB_PORT");			// The MySQL server port
    	String USERNAME = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");		// MySQL username credential
    	String PASSWORD = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");		// MySQL password credential
    	String URL		= System.getenv("OPENSHIFT_MYSQL_DB_URL");			// MySQL server configuration url (mysql://OPENSHIFT_MYSQL_DB_USERNAME:OPENSHIFT_MYSQL_DB_PASSWORD@OPENSHIFT_MYSQL_DB_HOST:OPENSHIFT_MYSQL_DB_PORT)
    	String SOCKET 	= System.getenv("OPENSHIFT_MYSQL_DB_SOCKET");		// MySQL socket connection file
    	String LOG_DIR 	= System.getenv("OPENSHIFT_MYSQL_DB_LOG_DIR");		// The path to the MySQL log directory
    	String VERSION 	= System.getenv("OPENSHIFT_MYSQL_VERSION");			// The version of the MySQL server
    	String TIMEZONE = System.getenv("OPENSHIFT_MYSQL_TIMEZONE");		// The MySQL server timezone
    	String MAX_CONS = System.getenv("OPENSHIFT_MYSQL_MAX_CONNECTIONS");	// The maximum permitted number of simultaneous client connections
    	
    	StringBuilder sb = new StringBuilder("");

    	sb.append("HOST:     " + HOST		+ "\n");
    	sb.append("PORT:     " + PORT		+ "\n");
    	sb.append("USERNAME: " + USERNAME	+ "\n");
    	sb.append("PASSWORD: " + PASSWORD	+ "\n");
    	sb.append("URL:      " + URL		+ "\n");
    	sb.append("SOCKET:   " + SOCKET		+ "\n");
    	sb.append("LOG_DIR:  " + LOG_DIR	+ "\n");
    	sb.append("VERSION:  " + VERSION	+ "\n");
    	sb.append("TIMEZONE: " + TIMEZONE	+ "\n");
    	sb.append("MAX_CONS: " + MAX_CONS	+ "\n");
    	
		System.out.println();
		System.out.println("Enviroment variables: ");
		System.out.println(sb.toString());
		System.out.println();
		
    }
    
	public static void printCETime() {
    	
        Calendar cal = Calendar.getInstance();
    	String format = "dd.MM.yyyy HH:mm:ss zzz";
    	
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        SimpleDateFormat sdfUTC = new SimpleDateFormat(format);
        SimpleDateFormat sdfCET = new SimpleDateFormat(format);
       
        Date timeServer = cal.getTime();
        String serverTime = sdf.format(timeServer);
        System.out.println();
        System.out.println( "CurrentTime: " + serverTime );
        System.out.println();
        
        // Convert Local Time to UTC (Works Fine)
        sdfUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
        sdfCET.setTimeZone(TimeZone.getTimeZone("CET"));
        
        String stringUTC = sdfUTC.format(timeServer);
        String stringCET = sdfCET.format(timeServer);
        
        System.out.println("UTC    time: " + stringUTC);
        System.out.println("CET    time: " + stringCET);
        System.out.println("Server time: " + serverTime);
        
        System.out.println();
   	
    }

}
