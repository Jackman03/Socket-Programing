import java.io.*;
import java.net.*;
import java.util.*;

public class Client{
    public static void main(String[] args) throws  IOException{
        

        Socket ClienSocket = null;

        InputStreamReader Reader = null;
        OutputStreamWriter Writer = null;
        BufferedReader bufferedReader = null;   //faster
        BufferedWriter bufferedWriter = null;
        String HostAddress = "127.0.0.1";
       

        try {
            ClienSocket = new Socket(HostAddress,6969);
            System.out.println("Starting client session on " + ClienSocket.getLocalAddress());

            Reader = new InputStreamReader(ClienSocket.getInputStream());
            Writer = new OutputStreamWriter(ClienSocket.getOutputStream());

            bufferedReader = new BufferedReader(Reader);
            bufferedWriter = new BufferedWriter(Writer);

            Scanner scanner = new Scanner(System.in);

            //until the connection is closed
            while (true) { 

                String SendMsg = scanner.nextLine();    //Message to send to server
                bufferedWriter.write(SendMsg);  //sends message to server
                bufferedWriter.newLine();   //does not send the new line
                bufferedWriter.flush(); //clears the stream

                //wait for reply
                System.out.println("Answer from server: " + bufferedReader.readLine());

                if(SendMsg.equalsIgnoreCase("0/0=")){
                    break;
                }
            }


            
        } catch (IOException e) {
            System.out.println("Failed to connect to "+ HostAddress);
            e.printStackTrace();
        }

        finally{

            try {

                if(ClienSocket != null){
                    ClienSocket.close();
                }
                if(Reader != null){
                    Reader.close();
                }

                if(Writer != null){
                    Writer.close();
                }

                if(bufferedReader != null){
                    bufferedReader.close();
                }

                if(bufferedWriter != null){
                    bufferedWriter.close();
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            

        }






        
    }
}