package fit.se.be_microkernel.core;


import fit.se.be_microkernel.plugin.CmsPlugin;
import fit.se.be_microkernel.plugin.impl.TitleUpperPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.*;
import java.util.*;

@RestController
@RequestMapping("/api/kernel")
@CrossOrigin(origins = "*")
public class CoreController {

    private List<Map<String, String>> posts = new ArrayList<>();

    @Autowired
    private List<CmsPlugin> plugins;

    @GetMapping("/plugins")
    public List<CmsPlugin> getPlugins() { return plugins; }

    @PostMapping("/plugins/{id}/toggle")
    public void togglePlugin(@PathVariable String id) {
        plugins.stream()
                .filter(p -> p.getPluginId().equals(id))
                .findFirst()
                .ifPresent(p -> p.setActive(!p.isActive()));
    }

    @PostMapping("/posts")
    public void createPost(@RequestBody Map<String, String> payload) {
        posts.add(new HashMap<>(payload));
    }

    @GetMapping("/posts")
    public List<Map<String, String>> getPosts() {
        List<Map<String, String>> processedPosts = new ArrayList<>();
        for (Map<String, String> post : posts) {
            String title = post.get("title");
            String content = post.get("content");

            for (CmsPlugin plugin : plugins) {
                if (plugin.isActive()) {
                    // Xử lý nội dung
                    content = plugin.execute(content);

                    // Kiểm tra nếu là Plugin viết hoa tiêu đề thì xử lý tiêu đề luôn
                    if (plugin instanceof TitleUpperPlugin) {
                        title = title.toUpperCase();
                    }
                }
            }
            processedPosts.add(Map.of("title", title, "content", content));
        }
        return processedPosts;
    }
    @PostMapping("/plugins/upload")
    public void uploadPlugin(@RequestParam("file") MultipartFile file, @RequestParam("className") String className) {
        try {
            Path dirPath = Paths.get("uploaded_plugins");
            if (!Files.exists(dirPath)) Files.createDirectories(dirPath);
            Path filePath = dirPath.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            URLClassLoader classLoader = new URLClassLoader(
                    new URL[]{filePath.toUri().toURL()},
                    this.getClass().getClassLoader()
            );

            Class<?> pluginClass = Class.forName(className, true, classLoader);
            CmsPlugin newPlugin = (CmsPlugin) pluginClass.getDeclaredConstructor().newInstance();
            plugins.add(newPlugin);
            System.out.println("Đã nạp Plugin động thành công: " + newPlugin.getName());
        } catch (Exception e) {
            throw new RuntimeException("Lỗi load plugin: " + e.getMessage());
        }
    }
}