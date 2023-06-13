import java.io.*;
import java.net.*;

public class DirectoryListingServer implements Runnable {
  ServerSocket Socket;
  String nodeName;

  public DirectoryListingServer() throws IOException {
    Socket = new ServerSocket(3255);
  }

  public static void main(String[] args) throws Exception {
    Thread listingServer = new Thread(new DirectoryListingServer());
    listingServer.start();
  }

  @Override
  public void run() {
    while (true) {
      try {
        Socket connectionSocket = Socket.accept();
        System.out.println("Client Connected");

        DataOutputStream outputToClient = new DataOutputStream(connectionSocket.getOutputStream());
        BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        //  clientSentence = inputFromClient.readLine();

        String[] pathNames;
        File file = new File("./src");
        pathNames = file.list();
        for (String pathName : pathNames) {
          nodeName = pathName + ", ";
          outputToClient.writeBytes(nodeName);
        }
        connectionSocket.close();
      } catch (Exception e) {
      }
    }
  }
}
