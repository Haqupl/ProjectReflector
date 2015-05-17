package Reflector;

import java.net.ServerSocket;

/**
 * Elementary server;
 * Reads no commands; just expects to be called via Pinger.
 */

public class SPingerTest {
    public static void main(String[] args) throws Exception{
        ServerSocket ss = new ServerSocket(11111);
        SocketWrapper sw = new SocketWrapper(ss.accept());
        ReflectorModule pinger = new Pinger();
        //...
        pinger.bind(sw);
        pinger.handle();    //blocking; will handle whole communication; should go on separate thread
        pinger.close();

    }
}
