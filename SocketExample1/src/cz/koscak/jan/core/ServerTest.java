package cz.koscak.jan.core;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class ServerTest {

    private static final String END = "*****END*****";
    private static final String STOP = "*****STOP*****";
	private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static BufferedReader bufferedReader;
    private static String inputLine;
    private static PrintWriter printWriter;

    public static void main(String[] args) {
    	//serverImplemetation1(); // OK
    	serverImplemetation2(); // OK
    }
    
    public static void serverImplemetation1(){

        while(true) {
    	
	    	// Wait for client to connect on 63400 	
        	System.out.println("Wait for client to connect on port: 63400 ...");
	
	        try {
	
	            serverSocket = new ServerSocket(63400);	
	            clientSocket = serverSocket.accept();
	
	            // Create a reader	
	            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	
	        	System.out.println("---START---");

	        	// Get the client message	
	            while((inputLine = bufferedReader.readLine()) != null ) {	
	            	System.out.println(inputLine);	  
	            	if (inputLine.startsWith(END)){
	            		break;
	            	}
	            }
	            
	        	System.out.println("----END-----");
	        	
	            printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
	            printWriter.println("OK OK OK");
	            printWriter.println("OH YEAH!!!");           
	            printWriter.close();
	 	            
	        	bufferedReader.close();
	        	
	        	clientSocket.close();	        	
	        	serverSocket.close();
	
	        } catch(IOException e) {
	            System.out.println("SERVER ERROR: " + e);
	        }
        
        }
    }
    
    public static void serverImplemetation2(){
    	
    	System.out.println("Wait for client to connect on port: 63400 ...");
    	
        try {
            serverSocket = new ServerSocket(63400);	
            clientSocket = serverSocket.accept();
            // Create a reader	
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            boolean stop = false;
            
            while(!stop) {
            
	        	System.out.println("---START---");
	        	// Get the client message	
	            while((inputLine = bufferedReader.readLine()) != null ) {	
	            	System.out.println(inputLine);
	            	if (inputLine.startsWith(STOP)){
	            		stop = true;
	            		break;
	            	}
	            	if (inputLine.startsWith(END)){
	            		break;
	            	}
	            }  
	        	System.out.println("----END-----");
	        	
	            printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
	            printWriter.println("OK OK OK");
	            printWriter.println("OH YEAH!!!"); 
	            if (!stop) {
	            	printWriter.println(END);
	            } else {
	            	printWriter.println(STOP);
	            }
	            printWriter.flush();
            
            }
            
            printWriter.close();
 	            
        	bufferedReader.close();
        	
        	clientSocket.close();	        	
        	serverSocket.close();

        } catch(IOException e) {
            System.out.println("SERVER ERROR: " + e);
        }

    }
    
}
