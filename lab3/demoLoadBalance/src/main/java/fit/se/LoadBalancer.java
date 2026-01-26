package fit.se;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LoadBalancer {
    private List<WebServer> servers = new ArrayList<>();
    private AtomicInteger counter = new AtomicInteger(0); // Để đếm vòng lặp an toàn

    // Thêm server vào cụm (Cluster)
    public void addServer(WebServer server) {
        servers.add(server);
    }

    // Thuật toán Round Robin để chọn Server
    public WebServer getNextServer() {
        if (servers.isEmpty()) return null;

        // Lấy chỉ số tiếp theo và dùng phép chia lấy dư để quay vòng
        int index = counter.getAndIncrement() % servers.size();
        return servers.get(index);
    }

    // Tiếp nhận request và điều phối
    public void dispatch(String request) {
        WebServer server = getNextServer();
        if (server != null) {
            server.handleRequest(request);
        } else {
            System.out.println("Không có server nào hoạt động!");
        }
    }
}
