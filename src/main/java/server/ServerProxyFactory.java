
package server;

public class ServerProxyFactory {

    private static IServerProxy theInstance;

    private ServerProxyFactory() {
    }

    public static synchronized IServerProxy getInstance() {

        if (theInstance == null) {
            theInstance = new DBServerProxy();
        }

        return theInstance;
    }

}
