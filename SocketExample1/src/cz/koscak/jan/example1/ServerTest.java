package cz.koscak.jan.example1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerTest {

	private static final int SERVER_PORT = 63400;
	private static final int CLIENT_MAXIMUM_THRESHOLD = 3;
	private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static ArrayList<UserSocketThread> users = new ArrayList<UserSocketThread>();

    public static void main(String[] args) {
    	serverImplemetation3(); // OK
    }
    
    
    public static void serverImplemetation3(){
    	
    	System.out.println("Wait for client to connect on port: " + SERVER_PORT + " ...");
    	
        try {
        	
            serverSocket = new ServerSocket(SERVER_PORT);	
            
            while(true) {
				
                // Get a client trying to connect
                try {
                	
                	clientSocket = serverSocket.accept();
	            	
	            	UserSocketThread userSocketThread = new UserSocketThread(clientSocket);
	            	
	            	users.add(userSocketThread);
	        	
	                // Remove all disconnected clients
	                for(int i = 0; i < users.size();i++) {
	                    // Check connection, remove on dead
	                    if (!users.get(i).isConnected()) {
	                        System.out.println(users.get(i).getId() + " removed due to lack of connection.");
	                        users.remove(i);
	                    }
	                }
	
	                if (users.size() <= CLIENT_MAXIMUM_THRESHOLD) {
	                
	                	new Thread(userSocketThread).start();
	                
	                } else {
	                	
	                	System.out.println("Maximum (" + CLIENT_MAXIMUM_THRESHOLD + ") client count limit reached.");
	                	users.remove(userSocketThread);
	                	userSocketThread.purge(UserSocketThread.ERROR + " Connection closed due to: Maximum client count limit reached.");
	                	
	                }
            	
                } catch(IOException e) {
                    System.out.println("Could not get a client.");
                }
        	       	        	
			}
            
        } catch(IOException e) {
            System.err.println("SERVER ERROR: " + e);
        } finally {
        	try {
				serverSocket.close();
			} catch (IOException e) {
				System.err.println("SERVER ERROR (closing): " + e);
			}
        }

    }
    
}
