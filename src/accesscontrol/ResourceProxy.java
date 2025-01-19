package accesscontrol;

interface ResourceProxy {
    String getResource(String user, String resource);
}
