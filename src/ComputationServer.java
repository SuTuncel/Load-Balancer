import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ComputationServer implements Runnable {
  DataInputStream dis;
  ServerSocket welcomeSocket;

  public ComputationServer() throws Exception {
    welcomeSocket = new ServerSocket(7770);

  }

  public static void main(String[] args) throws Exception {

    Thread computationServer = new Thread(new ComputationServer());
    computationServer.start();
  }


  @Override
  public void run() {
    Socket connectionSocket = null;

    while (true) {
      try {
        connectionSocket = welcomeSocket.accept();
        dis = new DataInputStream(connectionSocket.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Client Connected Calculation server");

        int sleepTime = dis.readInt();
        try {
          Thread.sleep(sleepTime);
//          double temp = 0;
          int temp = 0;
          for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
              for (int k = 0; k < 10; k++) {
//                temp += (Math.sqrt(k) * j) / i;
                temp += (k * j * 1.0) / i;
              }
            }
          }
          System.out.println(temp);
          System.out.println("Calculation completed: " + temp);

          connectionSocket.close();
        } catch (Exception e) {
        }


      } catch (Exception e) {
        throw new RuntimeException(e);
      }


    }

  }
}
