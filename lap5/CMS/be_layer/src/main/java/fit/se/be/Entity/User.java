package fit.se.be.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "cms_users") // Tránh trùng tên bảng 'user' của hệ thống
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;
    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties("author")
    private List<Post> posts;
}