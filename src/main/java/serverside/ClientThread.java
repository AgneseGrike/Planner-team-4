package serverside;

import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable {
    Socket sock;
    InputStream is;
    OutputStream os;
    public ClientThread(Socket socket){
        this.sock = socket;
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

            }
    @Override
    public void run() {
        System.out.println("Starting thread");
        PrintWriter pw = new PrintWriter(os);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while(true){
            try {
                String line = br.readLine();
                System.out.println(line);
                if(!line.contains(""))
                pw.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
