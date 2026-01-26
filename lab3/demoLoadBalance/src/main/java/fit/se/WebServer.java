package fit.se;

public interface WebServer {
    void handleRequest(String requestData);
    String getName();
}
