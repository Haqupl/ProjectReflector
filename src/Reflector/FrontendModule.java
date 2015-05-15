package Reflector;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by mareckip on 5/14/15.
 */
public interface FrontendModule {
    //method called by front when request for this module is made
    public void handleFront(ObjectInputStream istream, ObjectOutputStream ostream);
    public String getModuleName();
}
