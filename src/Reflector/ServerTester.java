package Reflector;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mareckip on 5/14/15.
 */

class Backend implements Runnable {
    Map<String,BackendModule> registeredModules;
    //could implement access rights for user roles here, easily
    //map<roleid,set<moduleNames>> allowedModules;  //easily readable from local db

    public void addModule(BackendModule module) {
        registeredModules.put(module.getModuleName(), module);
    }

    public Backend() {
        registeredModules = new HashMap<>();
        addModule(new Pinger());
    }

    // Main event loop of the backend controller
    @Override
    public void run() {
    Serwer prv = new Serwer(11111);
        prv.accept();
        while(true) {
            try {
                ObjectInputStream istream = prv.getObjectInputStream();
                ObjectOutputStream ostream = prv.getObjectOutputStream();
                String command = (String) istream.readObject();
                if (command.equals("Exit")) break;
                if (registeredModules.containsKey(command))
                    registeredModules.get(command).handleBack(istream, ostream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        prv.shutdown();
    }
}



public class ServerTester {
    public static void main(String[] args) throws Exception {
        new Backend().run();
    }
}
