package serverside;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ManagerServer {
    public static int port=8989;
    public static ServerSocket ss;
    public static void main(String[]args){
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){

            Socket sck = null;
            try {
               sck= ss.accept();
               if(sck!=null){

               }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
