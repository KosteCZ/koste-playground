package jko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;

public class TestDB {
	
	@Test
	public void test() {
		
		/*String USERNAME = System. getenv("OPENSHIFT_MYSQL_DB_USERNAME");
		String PASSWORD = System. getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
		String DB_NAME = System. getenv("OPENSHIFT_APP_NAME");
		//String FORNAME_URL = "com.mysql.jdbc.Driver";
		String URL = "jdbc:"+System.getenv( "OPENSHIFT_MYSQL_DB_URL") + DB_NAME ;*/
		
		String URL = "jdbc:mysql://7S8QEBlY8XyK@127.10.118.130:3306/jws";
		
		//String URL = "jdbc:mysql://127.10.118.130:3306/jws";
		
		/*String dbHost = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
		String dbPort = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
		String appName = System.getenv("OPENSHIFT_APP_NAME");	
		System.out.println(dbHost);*/

		Connection connection = null;
		
		try {
			
		     connection = DriverManager.getConnection( URL , "admin48xpSe3" , "7S8QEBlY8XyK" );
		     
		     String sql = "SELECT 1 + 1 FROM DUAL";
				
		     System.out.println("SQL : " + sql);
				
		     Statement statement = connection.createStatement();
				
		     ResultSet rs = statement.executeQuery(sql);
				
		     System.out.println(rs.toString());
				
				
		} catch (SQLException e ) {
		     System.err.println("DB connection ERROR: " + e );
		     e.printStackTrace();
		} finally {
		     try {
		          if (connection != null && !connection.isClosed()) {
		         connection.close();
		          }
		     } catch (SQLException e ) {
		          System.err.println("DB connection disconecting ERROR: " + e);
		          e.printStackTrace();
		     }
		}

		
	}
	
	@Test
	public void printCETime(){
		
		Main.printCETime();
		
	}
	
	@Test
	public void printWifiInfo() throws IOException{
		
		// WifiInfo - Android
		
		//NetworkInterface networkInterface = new NetworkInterface();
		
		ArrayList<String>ssids=new ArrayList<String>();
	    ArrayList<String>signals=new ArrayList<String>();
	    ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "netsh wlan show all");
	    
	    //netsh wlan show networks mode=bssid
	    //netsh wlan show networks mode=ssid
	    
	    
	    builder.redirectErrorStream(true);
	    Process p = builder.start();
	    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream(), "cp850"));//"UTF-8""ISO-8859-1""ISO-8859-2""Cp1250""Windows-1250""Windows-1252"
	    String line;
	    while (true) {
	        line = r.readLine();
	        if(line == null) {
	        	break;
	        }
//	        System.out.println("Line: " + line);
	        if (line.contains("SSID") || line.contains("Signal") || line.contains("Signál")) {
	            if (!line.contains("BSSID")) {
	    	        System.out.println("111 Line: " + line);
	                if (line.contains("SSID") && !line.contains("name") && !line.contains("SSIDs")) {
		    	        System.out.println("222 Line: " + line);
	                    line=line.substring(8);
		    	        System.out.println("333 Line: " + line);
	                    ssids.add(line);
	                    System.out.println("ssids.size: " + ssids.size());
	                }
	                if (line.contains("Signal") || line.contains("Signál")) {
		    	        System.out.println("444 Line: " + line);
	                    line=line.substring(30);
	                    signals.add(line);
	                    System.out.println("signals.size: " + signals.size());
	                }
	                
	                if(signals.size()==7) {
	                    break;
	                }
	            }

	        }
	        
            /*if (line.contains("Pocet") || line.contains("Počet")) {
            	System.out.println("CVN line: " + line);
            }*/

            if (line.contains("Pocet sítí,které jsou aktuálne viditelné: ")) {
            	/*System.out.println("CCCCCCCCCCCCCCCCCCCCCC-VVVVVVVVVVVVVVVVVVVVVV-NNNNNNNNNNNNNNNNNNNNN");*/
            	String currentlyVisibleNetworks = line.substring("Pocet sítí,které jsou aktuálne viditelné: ".length(), line.indexOf("."));
                System.out.println("Currently visible networks: " + currentlyVisibleNetworks);
            }

	    }
	    for (int i=0; i < ssids.size(); i++) {
	        System.out.println("SSID name == "+ssids.get(i)+"   and its signal == "+signals.get(i)  );
	    }
	
	}
	
	@Test
	public void printSQL(){ //delete me
		
		/*String selectSql1 = "SELECT r.r_object_id, r.duid " + 
				"FROM espis.cs_v_record_notif r " + 
				"WHERE r.is_one_off_collection = 0 AND r.notification_of_registered = 1 " + 
				"AND r.stat_place_change < to_date('<currentTimeString>', '<DATE_TIME_FORMAT>') - (<maxTimeToTransport>) " +  
				"AND r.STAT_REC = 'Zaregistrovaný' " + 
				"union all " + 
				"SELECT r.r_object_id, r.duid " + 
				"FROM espis.cs_v_record_notif r " + 
				"WHERE r.is_one_off_collection = 0 " + 
				"AND r.stat_place_change < to_date('<currentTimeString>', '<DATE_TIME_FORMAT>') - (<maxTimeToTransport>) " + 
				"AND r.STAT_REC = 'Zaevidovaný'";
			
		String selectSql2 = "SELECT r.r_object_id, r.duid " + 
				"FROM espis.cs_v_record_notif r " + 
				"WHERE r.is_one_off_collection = 1 " + 
				"AND r.notification_of_registered = 1 AND r.box_id <> ' ' " + 
				"AND r.stat_place_change < to_date('<currentTimeString>', '<DATE_TIME_FORMAT>') - (< maxTimeToTransport>) " + 
				"AND r.STAT_REC = 'Zaregistrovaný' " + 
				"union all " + 
				"SELECT r.r_object_id, r.duid " + 
				"FROM espis.cs_v_record_notif r " + 
				"WHERE r.is_one_off_collection = 1 AND r.box_id <> ' ' " +
				"AND r.stat_place_change < to_date('<currentTimeString>', '<DATE_TIME_FORMAT>') - (<maxTimeToTransport>) " + 
				"AND r.STAT_REC = 'Zaevidovaný'";*/
/*		
		String maxTimeToTransport = "21";
		String selectSql1;
		String selectSql2;
		
		selectSql1 = */
	//			"SELECT /*+ leading(r) */ //" +
//				"r.duid, r.r_object_id " +
/*				"FROM espis.cs_record_s r, espis.dm_relation_s rrt, espis.cs_record_type_s rt " +
				"WHERE " +
				"rrt.relation_name = 'cs_record__record_type' " +
				"and rrt.parent_id = r.r_object_id " +
				"and rrt.child_id = rt.r_object_id " +
				"and rt.is_one_off_collection = 0 " +
				"AND rt.notification_of_registered = 1 " +
				"AND r.STAT_REC                   = 'Zaregistrovaný' " +
				"AND r.RECORD_FORM  <> 'Digital' " +
				"AND r.stat_place_change < sysdate - " + maxTimeToTransport + " " +
				"AND r.stat_place in ('Na pobočce','Vrácený k dopracování') " +
				"union all " +
*///				"SELECT /*+ leading(r rrt rt) */ " +
	/*			"r.duid, r.r_object_id " +
				"FROM espis.cs_record_s r, espis.dm_relation_s rrt, espis.cs_record_type_s rt " +
				"WHERE " +
				"rrt.relation_name = 'cs_record__record_type' " +
				"and rrt.parent_id = r.r_object_id " +
				"and rrt.child_id = rt.r_object_id " +
				"and rt.is_one_off_collection = 0 " +
				"AND r.STAT_REC                   = 'Zaevidovaný' " +
				"AND r.RECORD_FORM  <> 'Digital'  " +
				"AND r.stat_place_change < sysdate - " + maxTimeToTransport + " " +
				"AND r.stat_place in ('Na pobočce','Vrácený k dopracování')";
*/
//			selectSql2 = 
//				"SELECT /*+ leading(r)  */  " +
/*				"r.duid, r.r_object_id " +
				"FROM espis.cs_record_s r, espis.dm_relation_s rrt, espis.cs_record_type_s rt " +
				"WHERE " +
				"rrt.relation_name = 'cs_record__record_type' " +
				"and rrt.parent_id = r.r_object_id " +
				"and rrt.child_id = rt.r_object_id " +
				"and rt.is_one_off_collection = 1 " +
				"AND rt.notification_of_registered = 1 " +
				"AND r.STAT_REC                   = 'Zaregistrovaný' " +
				"AND r.RECORD_FORM  <> 'Digital' " +
				"and exists (select 'x' from espis.dm_relation_s rel0, espis.cs_record__box_s rel where rel0.r_object_id = rel.r_object_id and rel0.parent_id = r.r_object_id and rel.is_canceled=0) " +
				"AND r.stat_place_change < sysdate - " + maxTimeToTransport + " " +
				"AND r.stat_place in ('Na pobočce','Vrácený k dopracování') " +
				"union all " +
		//		"SELECT /*+ leading(r rrt rt)  */ //" +
	/*			"r.duid, r.r_object_id " +
				"FROM espis.cs_record_s r, espis.dm_relation_s rrt, espis.cs_record_type_s rt " +
				"WHERE " +
				"rrt.relation_name = 'cs_record__record_type' " +
				"and rrt.parent_id = r.r_object_id " +
				"and rrt.child_id = rt.r_object_id " +
				"and rt.is_one_off_collection = 1 " +
				"AND r.STAT_REC                   = 'Zaevidovaný' " +
				"AND r.RECORD_FORM  <> 'Digital' " +
				"and exists (select 'x' from espis.dm_relation_s rel0, espis.cs_record__box_s rel where rel0.r_object_id = rel.r_object_id and rel0.parent_id = r.r_object_id and rel.is_canceled=0) " +
				"AND r.stat_place_change < sysdate - " + maxTimeToTransport + " " +
				"AND r.stat_place in ('Na pobočce','Vrácený k dopracování')";
*/
			String dql1 = "" +		
					"select cs_modifier from cs_v_last_statush_change " +
					"where " +
					"record_id='<r_object_id>' and " +
					"stat_h in ('Zaregistrovaný', 'Zaevidovaný') and " +
					"not exists (select record_id from cs_v_last_statush_change where stat_h ='Vrácený k dopracování' and record_id = '<r_object_id>') " +
					"order by cs_modify_date desc ENABLE(RETURN_TOP 1)";
							
			String dql2 = "" +
					"select cs_modifier from cs_v_last_statush_change " +
					"where " +
					"record_id='<r_object_id>' and " +
					"stat_h in ('Zaregistrovaný', 'Zaevidovaný') and " +
					"exists (select record_id from cs_v_last_statush_change where stat_h ='Vrácený k dopracování' and record_id = '<r_object_id>') " +
					"order by cs_modify_date asc ENABLE(RETURN_TOP 1)";			

			
			System.out.println("SQL1: " + dql1);
			System.out.println("SQL2: " + dql2);
		
	}

}
