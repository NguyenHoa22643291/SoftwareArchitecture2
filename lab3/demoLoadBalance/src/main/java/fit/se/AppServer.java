package fit.se;

public class AppServer implements WebServer{
    private String name;
    public AppServer(String name) { this.name = name; }

    @Override
    public void handleRequest(String requestData) {
        System.out.println("[" + name + "] đang xử lý dữ liệu: " + requestData);
    }

    @Override
    public String getName() { return name; }
}
