package accesscontrol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccessControlSystemTest {

    private WebRequestHandler requestHandler;
    private AccessControlProxy proxy;

    @BeforeEach
    void setUp() {
        requestHandler = new WebRequestHandler();
        proxy = new AccessControlProxy(requestHandler);
    }

    @Test
    void testValidResourceAccess() {
        String user = "user 1";
        String resource = "resource 1";

        String result = proxy.getResource(user, resource);
        assertEquals("Data for resource 1", result);
    }

    @Test
    void testCachedResourceAccess() {
        String user = "user 1";
        String resource = "resource 1";

        proxy.getResource(user, resource); // First access
        String result = proxy.getResource(user, resource); // Cached access

        assertEquals("Data for resource 1", result);
    }

    @Test
    void testUnauthorizedAccess() {
        String user = "user 1";
        String resource = "resource 2";

        String result = proxy.getResource(user, resource);
        assertNull(result);
    }

    @Test
    void testAuthenticationFailure() {
        String user = "unknown";
        String resource = "resource 1";

        String result = proxy.getResource(user, resource);
        assertNull(result);
    }
}

