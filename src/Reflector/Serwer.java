package Reflector;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mareckip on 5/14/15.
 */
public class Serwer {
    int port = 1111;
    ServerSocket srvSocket = null;
    Socket socket = null;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    public Serwer(int port) {
        this.port = port;
    }

    public void accept() {
        System.out.println("Start server accept...");
        try {
            srvSocket = new ServerSocket(port);
            socket = srvSocket.accept();
            System.out.println("accepted");
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectInputStream getObjectInputStream() {
        return ois;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return oos;
    }

    public void shutdown() {
        try {
            if (ois!=null) ois.close();
            if (oos!=null) oos.close();
            if (socket!=null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
