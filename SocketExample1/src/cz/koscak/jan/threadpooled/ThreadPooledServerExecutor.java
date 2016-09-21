package cz.koscak.jan.threadpooled;

public class ThreadPooledServerExecutor {
	
	public ThreadPooledServerExecutor() {
	}
	
	// Send requests in browser to:   http://localhost:9000/
	//
	// Example of response: "WorkerRunnable: Thread Pooled Server - 2016-09-08T10:59:28.093 Name: 14, Id: 14"
	
	public static void main(String[] args){
	
		ThreadPooledServer server = new ThreadPooledServer(9000);
		new Thread(server).start();
	
		try {
		    Thread.sleep(20 * 1000); // Server only runs for this specific time !
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		System.out.println("Stopping Server");
		server.stop();
	
	}
	
}
