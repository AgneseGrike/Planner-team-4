package serverside;

import ch.qos.logback.core.net.server.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ManagerServer {
    public static int port=8989;
    public static ServerSocket ss;
    public static List<ClientThread> threads ;
    public static void main(String[]args){
        threads = new LinkedList<>();
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){

            Socket sck;
            try {
               sck= ss.accept();
               if(sck!=null){

                   ClientThread ct = new ClientThread(sck);
                   threads.add(ct);
                   Thread t = new Thread(ct);
               }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
