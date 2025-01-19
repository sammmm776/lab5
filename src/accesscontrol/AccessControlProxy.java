package accesscontrol;
import java.util.HashMap;
import java.util.Map;

class AccessControlProxy implements ResourceProxy {
    private final RequestHandler requestHandler;
    private final Map<String, String> cache;

    public AccessControlProxy(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        this.cache = new HashMap<>();
    }

    @Override
    public String getResource(String user, String resource) {
        if (cache.containsKey(resource)) {
            System.out.println("Returning cached resource for: " + resource);
            return cache.get(resource);
        }

        System.out.println("Processing request for resource: " + resource);
        if (requestHandler.authenticate(user) && requestHandler.authorize(user, resource)) {
            String data = requestHandler.processRequest(resource);
            cache.put(resource, data);
            return data;
        } else {
            System.out.println("Access denied for user: " + user);
            return null;
        }
    }

    private String fetchResource(String user, String resource) {
        System.out.println("Processing request for resource: " + resource);
        if (requestHandler.authenticate(user) && requestHandler.authorize(user, resource)) {
            String data = "Data for " + resource;
            cache.put(resource, data);
            return data;
        } else {
            System.out.println("Access denied for user: " + user);
            return null;
        }
    }
}
