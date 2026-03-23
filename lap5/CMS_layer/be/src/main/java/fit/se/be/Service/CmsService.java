package fit.se.be.Service;

import fit.se.be.Entity.Post;
import fit.se.be.Entity.User;
import fit.se.be.Repository.PostRepository;
import fit.se.be.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CmsService {
    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;

    // --- Chức năng Đăng bài & Quản lý nội dung ---

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public Post savePost(Post post) {
        // KIỂM TRA QUAN TRỌNG:
        // Nếu Frontend gửi lên author { id: 1 }, ta cần đảm bảo User ID 1 có trong DB
        if (post.getAuthor() != null && post.getAuthor().getId() != null) {
            Optional<User> existingUser = userRepo.findById(post.getAuthor().getId());
            if (existingUser.isPresent()) {
                post.setAuthor(existingUser.get()); // Gán object User thật từ DB vào Post
            } else {
                throw new RuntimeException("Lỗi: Tác giả với ID " + post.getAuthor().getId() + " không tồn tại!");
            }
        }
        return postRepo.save(post);
    }

    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }

    // --- Chức năng Quản lý User ---

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User saveUser(User user) {
        // Đảm bảo role không bị null để tránh lỗi DB
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("Editor");
        }
        return userRepo.save(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}