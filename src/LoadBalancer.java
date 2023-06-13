import java.io.*;
import java.net.*;

public class LoadBalancer implements Runnable {
  String consoleEntryByClient;
  String portNumberToHandleRequest;
  ServerSocket initialSocket;

  public LoadBalancer() throws Exception {
    initialSocket = new ServerSocket(9876);
  }

  public static void main(String[] args) throws Exception {
    Thread server = new Thread(new LoadBalancer());
    server.start();
  }

  @Override
  public void run() {
    while (true) {
      try {
        Socket connectionSocket = initialSocket.accept();
        System.out.println("Client Connected to Load Balancer");
        BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outputToClient = new DataOutputStream(connectionSocket.getOutputStream());
        consoleEntryByClient = inputFromClient.readLine();

        if (consoleEntryByClient.equals("1")) {
          System.out.println("Directory listing process started...");
          portNumberToHandleRequest = "3255";
        } else if (consoleEntryByClient.equals("2")) {
          System.out.println("File transfer process started...");
          portNumberToHandleRequest = "3556";
        } else if (consoleEntryByClient.equals("3")) {
          System.out.println("Computation process started...");
          portNumberToHandleRequest = "7770";
        } else {
          System.out.println("Quit");
          portNumberToHandleRequest = "q";
        }
        outputToClient.writeBytes(portNumberToHandleRequest);
        connectionSocket.close();

      } catch (Exception e) {
      }
    }

  }
}