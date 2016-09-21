package cz.koscak.jan.example1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserSocketThread implements Runnable, ProtocolMessages {

	protected Socket clientSocket = null;
    private BufferedReader bufferedReader;
    private String inputLine;
    private PrintWriter printWriter;
    private Thread runningThread;
    private String threadId;
    private boolean connected = false;

    public UserSocketThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.connected = true;
    }
	
	public void run() {
		
        synchronized(this){
            this.runningThread = Thread.currentThread();
            threadId = String.valueOf(runningThread.getId());
        }
		
		try {
			
			// Create a reader	
			bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	        
	        boolean stop = false;
	            
            System.out.println(/*threadId + " " + ""*/);

            while(!stop) {
	            
	        	System.out.println(threadId + " " + " > Request:  recieving...");
	        	
	        	// Get the client message	
	            while((inputLine = bufferedReader.readLine()) != null ) {	
	            	System.out.println(threadId + " " + " - " + inputLine);
	            	if (inputLine.startsWith(STOP)){
	            		stop = true;
	            		break;
	            	}
	            	if (inputLine.startsWith(END)){
	            		break;
	            	}
	            }  
	        	System.out.println(threadId + " " + " > Request:  recieved.");
	        	
	        	if (inputLine == null) {
	        		System.out.println(threadId + " " + "Connection closed due to: Lost connection from client.");
		        	stop = true;
	            	printWriter.println(ERROR + " Connection closed due to: Lost connection from client.");
		            printWriter.flush();
		            break;
	        	}
	        	
	        	
	        	System.out.println(threadId + " " + " < Response: sending...");
	        	
	        	printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
	            printWriter.println(threadId + " " + "OK OK OK");
	            System.out.println(threadId + " " + " - " + "OK OK OK");
	            
	            Thread.sleep(4 * 1000);
	            
	            printWriter.println(threadId + " " + "OH YEAH!!!"); 
	            System.out.println(threadId + " " + " - " + "OH YEAH!!!");
	            
	            System.out.println(threadId + " " + " < Response: sent.");
	        	
	            
	            if (!stop) {
	            	printWriter.println(END);
	            } else {
	            	printWriter.println(STOP);
	            }
	            printWriter.flush();
	            System.out.println(/*threadId + " " + ""*/);
            
            }
            
            connected = false;
            
            printWriter.close();
 	            
        	bufferedReader.close();
        	
        	clientSocket.close();
						
		} catch (IOException | InterruptedException e) {
			System.out.println("UserSocketThread - exception: " + e);
		}
		
	}
	
    /**
     * Purges this user from connection.
     */
    public void purge() {
    	purge(null);
    }
    
    public void purge(String message) {
        // Close everything
        try {
        	
        	if (message != null) {
                System.out.println("PURGE_ERR_MSG: " + message);
                printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        		printWriter.println(message);
        		printWriter.flush();
        		printWriter.close();
        	}
        	
            connected = false;
            clientSocket.close();
        } catch(IOException e) {
            System.out.println("Could not purge " + clientSocket + ".");
        }
    }
    
    /**
     * Gets the connection status of this user.
     *
     * @return  If this user is still connected.
     */
    public boolean isConnected() {
        return connected;
    }
    
    /**
     * Gets this user thread id.
     *
     * @return  User thread id.
     */
    public String getId() {
        return threadId;
    } 
	
}
