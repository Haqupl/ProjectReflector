package Reflector;

import java.net.Socket;

/**
 * Created by mareckip on 17.05.15.
 */

/**
 * This program just shuts down the remote server.
 *
 * Could be extended to blah blah ShutdownModule ... with some warning to clients, nice process shutdown,
 * waiting for all threads to finish etc.
 */
public class ServerShutdown {
    public static void main(String[] args) throws Exception {
        SocketWrapper sw = new SocketWrapper(new Socket("localhost",11111));
        sw.getObjectOutputStream().writeObject("Exit");
        sw.close();
    }
}
