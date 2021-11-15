import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

  public final static int PORT = 13267;      // needs to be changed
  public final static String IPSERVER = "127.0.0.1";  // localhost
  public final static int FILESIZE = 6022386;
  public static String filepath = "./";

  public Client() throws IOException {
    int bytesAlreadyRead;
    int current = 0;
    FileOutputStream fostream = null;
    BufferedOutputStream bostream = null;
    Socket socket = null;

    Scanner sc = new Scanner(System.in);
    System.out.println("File recieved, type something to rename it (leave blank = default name)");
    String name = sc.nextLine();

    filepath = name.equals("") ? "./file-downloaded" : "./" + name;  

    try {
      socket = new Socket(IPSERVER, PORT);
      System.out.println("Connecting...");

      // receive file
      byte [] bytearr  = new byte [FILESIZE];
      InputStream istream = socket.getInputStream();
      fostream = new FileOutputStream(filepath);
      bostream = new BufferedOutputStream(fostream);
      bytesAlreadyRead = istream.read(bytearr, 0, bytearr.length);
      current = bytesAlreadyRead;

      do {
         bytesAlreadyRead = istream.read(bytearr, current, (bytearr.length-current));
         if(bytesAlreadyRead >= 0) current += bytesAlreadyRead;
      } while(bytesAlreadyRead > -1);

      bostream.write(bytearr, 0 , current);
      bostream.flush();
      System.out.println("File " + filepath + " downloaded (" + current + " bytes read)");
    }
    finally {
      if (fostream != null) fostream.close();
      if (bostream != null) bostream.close();
      if (socket != null) socket.close();
    }
  }

}