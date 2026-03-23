package fit.se.be.Controller;

import fit.se.be.Entity.Post;
import fit.se.be.Entity.User;
import fit.se.be.Service.CmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
// origins = "*" để tránh lỗi CORS khi bạn đổi port hoặc chạy Docker
@CrossOrigin(origins = "*")
public class CmsController {

    @Autowired
    private CmsService cmsService;

    // --- QUẢN LÝ BÀI VIẾT (POSTS) ---

    @GetMapping("/posts")
    public List<Post> posts() {
        return cmsService.getAllPosts();
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody Post p) {
        // Kiểm tra xem phía FE có gửi Author kèm ID lên không
        if (p.getAuthor() == null || p.getAuthor().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        // Lưu bài viết
        Post savedPost = cmsService.savePost(p);
        return ResponseEntity.ok(savedPost);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> delPost(@PathVariable Long id) {
        cmsService.deletePost(id);
        return ResponseEntity.ok().build();
    }

    // --- QUẢN LÝ NGƯỜI DÙNG (USERS) ---

    @GetMapping("/users")
    public List<User> users() {
        return cmsService.getAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        // Đảm bảo luôn có role khi tạo mới để không bị lỗi null ở DB
        if (user.getRole() == null) {
            user.setRole("Editor");
        }
        return cmsService.saveUser(user);
    }
}