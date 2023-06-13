import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileTransferServer implements Runnable {
  ServerSocket serverSocket;

  public FileTransferServer() throws IOException {
    serverSocket = new ServerSocket(3556);
  }

  public static void main(String[] args) throws Exception {
    Thread fileServer = new Thread(new FileTransferServer());
    fileServer.start();
  }


  @Override
  public void run() {

    while (true) {
      try {
        Socket connectionSocket = serverSocket.accept();
        System.out.println("Client forwarded to File Server.");
//        BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
//        DataOutputStream outputToClient = new DataOutputStream(connectionSocket.getOutputStream());

//        String fileName = inputFromClient.readLine();
        //System.out.println(fileName);
//        FileInputStream fis = new FileInputStream("./src/" + fileName);
        FileInputStream fis = new FileInputStream("./src/Test.txt");
        byte[] bytes = new byte[2000];
        fis.read(bytes, 0, bytes.length);
        OutputStream os = connectionSocket.getOutputStream();
        os.write(bytes, 0, bytes.length);

      } catch (Exception e) {
      }
    }

  }
}
