package Reflector;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by mareckip on 5/14/15.
 */

//Assume: client initiates; so: "handle" is backend;
// explicite call to front methods is frontend.

public class Pinger extends ReflectorModule {
    public Pinger(SocketWrapper socket) {
        super(socket);
    }

    public Pinger() {
        super();
    }

    @Override
    public void handle() throws Exception {
        String method = (String)istream.readObject();
        //@Controller
        if (method.equals("getMyIp")) getHostIp_Back();
        if (method.equals("getLatencyTo")) getHostLatency_Back();
    }

    //backend methods private (called by handle())
    //frontend methods public (called by the client)

    ///////////////////////////////////////////
    // HOST IP
    ///////////////////////////////////////////
    public void getHostIp_Front() throws Exception {    //front part is public
        ostream.writeObject("getMyIp");    //1st communication is always method name
        System.out.println((String) istream.readObject());
    }
    ///////////////////////////////////////////
    private void getHostIp_Back() throws Exception {    //back part is private, called by "handle()"
        ostream.writeObject("10.0.0.1");
    }
    ///////////////////////////////////////////

    ///////////////////////////////////////////
    // LATENCY to given host
    ///////////////////////////////////////////
    public void getHostLatency_Front() throws Exception {
        ostream.writeObject("getLatencyTo"); //1st communication is always method name
        ostream.writeObject("10.0.0.3");
        System.out.println((String)istream.readObject());

    }
    ///////////////////////////////////////////
    private void getHostLatency_Back() throws Exception {
        String ip = (String) istream.readObject();
        ostream.writeObject("12ms");
    }
    ///////////////////////////////////////////

    @Override
    public String getModuleName() {
        return "Pinger";
    }
}
