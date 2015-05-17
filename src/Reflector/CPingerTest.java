package Reflector;

import Reflector.Pinger;
import Reflector.SocketWrapper;

import java.net.Socket;

/**
 * Elementary test of the pinger module;
 * If run against SPingerTest, no command should be send (comment-out the .sendModuleNameToServer())
 * If run against ServerTester, the command should be sent.
 *
 * ToDo: some waiting for server to come online + some timeouts.
 */

public class CPingerTest {
    public static void main(String[] args) throws Exception {
        SocketWrapper sw = new SocketWrapper(new Socket("localhost", 11111));
        Pinger pinger = new Pinger(sw);
//        pinger.sendModuleNameToServer();  //needed for ServerTester server
        pinger.getHostLatency_Front();
        sw.getObjectOutputStream().writeObject("Exit");
        pinger.close();
    }
}
