package cz.koscak.jan.threadpooled;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

public class WorkerRunnable implements Runnable{

    protected Socket clientSocket = null;
    protected String serverText   = null;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }

    public void run() {
        try {
            InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            LocalDateTime time = LocalDateTime.now();
            Thread thread = Thread.currentThread();
            String threadInfo = "Name: " + thread.getId() + ", Id: " + thread.getId();
            output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " + this.serverText + " - " +	time + " " + threadInfo).getBytes());
            output.close();
            input.close();
            System.out.println("Request processed: " + time + " - " + threadInfo);
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}
