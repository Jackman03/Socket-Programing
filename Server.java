import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
public class Server {
    public static void main(String[] args) throws IOException{
        System.out.println("Hello I am the server");
        
        Socket Socket = null;
        InputStreamReader Reader = null;
        OutputStreamWriter Writer = null;
        BufferedReader bufferedReader = null;   //faster
        BufferedWriter bufferedWriter = null;
        ServerSocket ServerSocket = new ServerSocket(6969);


      

        while(true){

            try {
                
                Socket = ServerSocket.accept();

                String ip = ""+Socket.getRemoteSocketAddress();
                System.out.println("Connected by client on: " + ip);
                

                Reader = new InputStreamReader(Socket.getInputStream());
                Writer = new OutputStreamWriter(Socket.getOutputStream());

                bufferedReader = new BufferedReader(Reader);
                bufferedWriter = new BufferedWriter(Writer);


                while(true){

                    String ClientMsg = bufferedReader.readLine();

                    //do logic with client message here

                    //print to server console and send answer to client
                    System.out.println("Received question: " + ClientMsg + ". Send back answer: ");
                    bufferedWriter.write("Answer here: ");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

          

                    if(ClientMsg.equalsIgnoreCase("0/0=")){
                        bufferedWriter.write("Goodbye!");
                        System.out.println("User " + ip + "has terminated connection");
                      
                        break;
                    }

                    Socket.close();
                    Reader.close();
                    Writer.close();
                    
                    bufferedReader.close();
                    bufferedWriter.close();

                 



                }

            } catch (IOException e) {
                e.printStackTrace();
            }   //end try catch

        }   //end while loop
    }
}
