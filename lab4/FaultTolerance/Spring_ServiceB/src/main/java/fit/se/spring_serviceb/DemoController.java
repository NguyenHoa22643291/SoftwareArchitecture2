package fit.se.spring_serviceb;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RestController
public class DemoController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/test-fault")
    @CircuitBreaker(name = "myService", fallbackMethod = "myFallback")
    @Retry(name = "myService")
    public String callNodeJs() {
        System.out.println("Đang gọi sang Node.js...");
        return restTemplate.getForObject("http://localhost:3000/target", String.class);
    }

    // Hàm này chạy khi Node.js bị lỗi hoặc mạch đang ngắt (Open)
    public String myFallback(Exception e) {
        return "Hệ thống đang bận (Fallback) - Lỗi: " + e.getMessage();
    }
}