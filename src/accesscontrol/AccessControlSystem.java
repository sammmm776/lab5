package accesscontrol;

public class AccessControlSystem {
    public static void main(String[] args) {
        RequestHandler requestHandler = new WebRequestHandler();
        ResourceProxy proxy = new AccessControlProxy(requestHandler);

        String user = "user 1";

        System.out.println(proxy.getResource(user, "resource 1"));

        System.out.println(proxy.getResource(user, "resource 1"));

        System.out.println(proxy.getResource(user, "resource 2"));
    }
}
