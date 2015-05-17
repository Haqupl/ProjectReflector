package Reflector;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by mareckip on 17.05.15.
 */

/**
 * Proper management of the streams, which are 1:1 to socket.
 *
 * This wrapper should also capture SSLSockets, and just provide Object[Input/Output]Strems to
 * ReflectorModule's.
 */

public class SocketWrapper implements Closeable {
    Socket socket = null;
    ObjectInputStream objectInputStream = null;
    ObjectOutputStream objectOutputStream = null;


    public SocketWrapper(Socket socket) {
        this.socket = socket;
        getStreams();
    }


    @Override
    public void close() throws IOException {
        if (socket!=null && !socket.isClosed()) socket.close();
    }

    private void getStreams() {
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }
}
