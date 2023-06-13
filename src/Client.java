import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client implements Runnable {
  public static void main(String[] args) throws IOException {
    Thread connection = new Thread(new Client());
    connection.start();
  }

  @Override
  public void run() {
    while (true) {
      try {
        String inputFromConsole;
        String givenPort;
        BufferedReader inputFromUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("localhost", 9876);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.println("Enter the number of the process you want to execute:");
        System.out.println("1. Directory Listing");
        System.out.println("2. File Transfer");
        System.out.println("3. Computation");

        inputFromConsole = inputFromUser.readLine();
        outToServer.writeBytes(inputFromConsole + '\n');

        givenPort = inputFromServer.readLine();

        if (inputFromConsole.equals("1")) {
          clientSocket = new Socket("localhost", Integer.parseInt(givenPort));
          inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
          givenPort = inputFromServer.readLine();
          System.out.println("Value received from server: " + givenPort);
          clientSocket.close();

        } else if (inputFromConsole.equals("2")) {
          clientSocket = new Socket("localhost", Integer.parseInt(givenPort));
          DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          byte[] b = new byte[200002];
          System.out.print("Enter name of txt file (Hint: Test.txt): ");
          String fileName = br.readLine();
          dos.writeBytes(fileName);

          InputStream is = clientSocket.getInputStream();
          FileOutputStream fos = new FileOutputStream("./src/Assets/" + fileName);
          is.read(b, 0, b.length);
          fos.write(b, 0, b.length);
        } else if (inputFromConsole.equals("3")) {

          clientSocket = new Socket("localhost", Integer.parseInt(givenPort));

          DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          System.out.println("Enter Number (ms): ");
          Scanner scanner = new Scanner(System.in);
          int x = scanner.nextInt();
          dos.writeInt(x);
          clientSocket.close();
        }
        clientSocket.close();
      } catch (Exception e) {
      }
    }
  }
}