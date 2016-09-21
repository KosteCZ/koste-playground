package cz.koscak.jan.core;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ClientTest {

    private static final String END = "*****END*****";
    private static final String STOP = "*****STOP*****";
    
	private static Socket socket;
    private static PrintWriter printWriter;
     
    public static void main(String[] args) {
    	//serverImplemetation1(); // OK
    	serverImplemetation2(); // OK
    }
    
    public static void serverImplemetation1(){
    
        try {
        	
            socket = new Socket("localhost", 63400);
            socket.setKeepAlive(true);
            
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("Hello Socket");
            printWriter.println("EYYYYYAAAAAAAA!!!!");           
            printWriter.println(END);           
            printWriter.flush();
            
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line + "\n");
            }
            System.out.println("Response: ");
            System.out.println(response);
            
            printWriter.close();
            bufferedReader.close();
            socket.isInputShutdown();
            socket.close();
            
        } catch(Exception e) {

            System.out.println("CLIENT ERROR: " + e);

        }
    }
    
    public static void serverImplemetation2(){
        
        try {
        	
            socket = new Socket("localhost", 63400);
            socket.setKeepAlive(true);
            
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            int count = 0;
            boolean stop = false;
            
            while(!stop) {
            	
            	count++;
            
	            printWriter.println("Hello Socket");
	            printWriter.println("EYYYYYAAAAAAAA!!!!"); 
	            if (count < 3) {
	            	printWriter.println(END);
	            } else {
	            	printWriter.println(STOP);
	            }
	            printWriter.flush();
	            
	            StringBuilder response = new StringBuilder();
	            String line;
	            while ((line = bufferedReader.readLine()) != null) {
	                response.append(line + "\n");
	            	if (line.startsWith(STOP)){
	            		stop = true;
	            		break;
	            	}
	            	if (line.startsWith(END)){
	            		break;
	            	}
	            }
	            System.out.println("Response: ");
	            System.out.println(response);
            
            }
            
            printWriter.close();
            bufferedReader.close();
            socket.isInputShutdown();
            socket.close();
            
        } catch(Exception e) {

            System.out.println("CLIENT ERROR: " + e);

        }
    }
}
