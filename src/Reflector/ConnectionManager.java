package Reflector;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by mareckip on 5/14/15.
 */
public class ConnectionManager {
    private String server = "localhost";
    private int port = 11111;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public void connect() throws Exception {
        socket = new Socket(server, port);
        System.out.println("connected...");
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    public ObjectInputStream getInputStream() {
        System.out.println("ois=" + ois.toString());
        return ois;
    }

    public ObjectOutputStream getOutputStream() {
        System.out.println("oos=" + oos.toString());
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
