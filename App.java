import java.util.Scanner;
import java.io.*;

/**
 * Firstly it has to indentify whether it needs to run as Server or Client
 */
class App{
    Scanner sc = new Scanner(System.in);
    public App(){
        startup();
    }

    private void startup(){
        System.out.println("--File Trasfer--");
        System.out.println("Do you want to send o recieve files? [SEND/receive]");

        String response = sc.nextLine().toLowerCase();

        if(response == null){
            response="send";
        }if(response.equalsIgnoreCase("send")){
            try {
                new Server();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(response.equalsIgnoreCase("receive")){
            try {
                new Client();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.err.println("Expected 'send' or 'receive'");
            System.err.println("Exiting");
            System.out.println(response);
            System.exit(1);
        }
        
    }


    public static void main(String[] args) {
        new App();
    }
}