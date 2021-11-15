import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {

  public final static int PORT = 13267;  // you may change this
  public static String filepath = "";  // you may change this

  public Server() throws IOException {
    FileInputStream fistream = null;
    BufferedInputStream bistream = null;
    OutputStream ostream = null;
    ServerSocket ss = null;
    Socket socket = null;

    Scanner sc = new Scanner(System.in);
    System.out.println("Input file path");
    filepath = sc.nextLine();

    try {
      ss = new ServerSocket(PORT);
      while (true) {
        System.out.println("Waiting...");

        try {
          socket = ss.accept();
          System.out.println("Accepted connection : " + socket);

          // send file
          File myFile = new File (filepath);
          byte [] mybytearray  = new byte [(int)myFile.length()];

          fistream = new FileInputStream(myFile);
          bistream = new BufferedInputStream(fistream);
          bistream.read(mybytearray,0,mybytearray.length);
          ostream = socket.getOutputStream();

          System.out.println("Sending " + filepath + "(" + mybytearray.length + " bytes)");

          ostream.write(mybytearray,0,mybytearray.length);
          ostream.flush();

          System.out.println("Done.");
        }
        finally {
          if (bistream != null) bistream.close();
          if (ostream != null) ostream.close();
          if (socket!=null) socket.close();
        }
      }
    }
    finally {
      if (ss != null) ss.close();
    }
  }
}
