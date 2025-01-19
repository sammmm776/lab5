package accesscontrol;

abstract class RequestHandler {
    public final void handleRequest(String user, String resource) {
        if (authenticate(user)) {
            if (authorize(user, resource)) {
                String data = processRequest(resource);
                logAccess(user, resource);
                System.out.println("Resource Data: " + data);
            } else {
                System.out.println("Access denied for user: " + user);
            }
        } else {
            System.out.println("Authentication failed for user: " + user);
        }
    }

    protected abstract boolean authenticate(String user);

    protected abstract boolean authorize(String user, String resource);

    protected abstract String processRequest(String resource);

    private void logAccess(String user, String resource) {
        System.out.println("Access logged for user: " + user + " to resource: " + resource);
    }
}

