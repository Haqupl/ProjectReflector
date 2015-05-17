package Reflector;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by mareckip on 5/14/15.
 */
public class ReflectorModule {
    Socket socket = null;
    ObjectOutputStream ostream = null;  //created/destroyed in pair always
    ObjectInputStream istream = null;


    public ReflectorModule(SocketWrapper socket) {
        this.socket = socket.getSocket();
        ostream = socket.getObjectOutputStream();
        istream = socket.getObjectInputStream();
    }

    public ReflectorModule() {
        //1) Method provided in order to store modules in server's map of modules
        //2) can't be used before bind() called
        //3) after module name is provided by client, server must call bind() with
        //      client's socket.
    }

    public void bind(SocketWrapper socket) {
        this.socket = socket.getSocket();
        ostream = socket.getObjectOutputStream();
        istream = socket.getObjectInputStream();
    }

    //Called by client - server is listening for this name
    public void sendModuleNameToServer() {
        try {
            System.out.println("writing module name:" + getModuleName());
            ostream.writeObject(getModuleName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getModuleName() {return "NoName";}
    //Server's method; reads method name
    public void handle() throws Exception {}
    public void close() {
        try {
            if (ostream!=null) ostream.close();
            if (istream!=null) istream.close();
            if (socket!=null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
