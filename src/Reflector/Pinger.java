package Reflector;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by mareckip on 5/14/15.
 */

//Assume: client initiates; so: "handle" is backend; explicite call to front methods is frontend.

public class Pinger implements BackendModule, FrontendModule {
    ObjectInputStream istream;
    ObjectOutputStream ostream;


    @Override
    public void handleBack(ObjectInputStream istream, ObjectOutputStream ostream) {
        try {
            this.istream = istream;
            this.ostream = ostream;
            System.out.println("<<<pinger backend handler>>>");
            String method = (String)istream.readObject();
            //@Controller
            if (method.equals("your ip?")) getHostIp_Back();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleFront(ObjectInputStream istream, ObjectOutputStream ostream) {
        this.istream = istream;
        this.ostream = ostream;

    }

    //backend methods private (called by handler)
    //frontend methods public (called by the client)

    ///////////////////////////////////////////
    // HOST IP
    ///////////////////////////////////////////
    public void getHostIp_Front() throws Exception {
        ostream.writeObject("your ip?");    //1st communication is always method name
        System.out.println((String) istream.readObject());
    }
    ///////////////////////////////////////////
    private void getHostIp_Back() throws Exception {
        ostream.writeObject("10.0.0.1");
    }
    ///////////////////////////////////////////

    ///////////////////////////////////////////
    // LATENCY to given host
    ///////////////////////////////////////////
    public void getHostLatency_Front() throws Exception {
        ostream.writeObject("host latency to");
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
