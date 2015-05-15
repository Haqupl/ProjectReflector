package Reflector;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by mareckip on 5/14/15.
 */
public class ClientTester {
    public static void main(String[] args) throws Exception {
        ConnectionManager manager = new ConnectionManager();
        manager.connect();
        ObjectInputStream istream = manager.getInputStream();
        ObjectOutputStream ostream = manager.getOutputStream();

        //Important: client and server sides of "PingerBack" class must do exactly the opposite
        //otherwise stuff hangs...

        //things below should be replaced by "new Pinger(is,os).run()"
        ostream.writeObject("Pinger");

        //module name could actually be hidden (dynamic choice of available modules)
        Pinger pinger = new Pinger();
        pinger.handleFront(istream, ostream);
        pinger.getHostIp_Front();
//
//        ostream.writeObject("192.168.1.1");
//        String ans = (String) istream.readObject();
//        System.out.println(ans);
        ostream.writeObject("Exit");

        manager.shutdown();
    }
}
