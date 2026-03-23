package fit.se.be.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT") // Để lưu nội dung dài
    private String content;
    @ManyToOne(fetch = FetchType.EAGER) // Nhiều bài viết thuộc về một User
    @JoinColumn(name = "user_id")
    private User author;
}