package accesscontrol;
import java.util.HashMap;
import java.util.Map;

class WebRequestHandler extends RequestHandler {
    private final Map<String, String> users;

    public WebRequestHandler() {
        users = new HashMap<>();
        users.put("admin", "all");
        users.put("user 1", "resource 1");
    }

    @Override
    protected boolean authenticate(String user) {
        boolean result = users.containsKey(user);
        System.out.println("Authenticating user: " + user + " => " + result);
        return result;
    }

    @Override
    protected boolean authorize(String user, String resource) {
        boolean result = "all".equalsIgnoreCase(users.get(user)) ||
                resource.equalsIgnoreCase(users.get(user));
        System.out.println("Authorizing user: " + user + " for resource: " + resource + " => " + result);
        return result;
    }

    @Override
    protected String processRequest(String resource) {
        return "Data for " + resource;
    }
}
