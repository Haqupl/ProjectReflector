package Reflector;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mareckip on 5/14/15.
 */


class SerwerWorker implements Runnable {
    ReflectorModule module;
    SocketWrapper sw;

    public SerwerWorker(ReflectorModule module, SocketWrapper sw) {
        this.module = module;
        this.sw = sw;
    }

    @Override
    public void run() {
        try {
            module.bind(sw);
            module.handle();
            module.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class Backend implements Runnable {
    Map<String, ReflectorModule> registeredModules;

    public void addModule(ReflectorModule module) {
        registeredModules.put(module.getModuleName(), module);
    }

    public Backend() {
        registeredModules = new HashMap<>();
        addModule(new Pinger());
    }

    // Main event loop of the backend controller
    @Override
    public void run() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(11111);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while (true) {
            try {
                System.out.println("Server waiting on accept...");
                SocketWrapper sw = new SocketWrapper(ss.accept());
                ObjectInputStream ois = sw.getObjectInputStream();
                System.out.println("Serwer reading command...");
                String command = (String) ois.readObject();
                System.out.println("Command:" + command);
                if (command.equals("Exit")) break;
                if (registeredModules.containsKey(command)) {
                    ReflectorModule module = registeredModules.get(command);
                    new Thread(new SerwerWorker(module,sw)).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
}



public class ServerTester {
    public static void main(String[] args) throws Exception {
        new Backend().run();
    }
}
