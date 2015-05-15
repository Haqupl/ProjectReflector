package Reflector;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by mareckip on 5/14/15.
 */



public interface BackendModule {
    //method called by backend when request for this module is made
    public void handleBack(ObjectInputStream istream, ObjectOutputStream ostream);
    public String getModuleName();
}
