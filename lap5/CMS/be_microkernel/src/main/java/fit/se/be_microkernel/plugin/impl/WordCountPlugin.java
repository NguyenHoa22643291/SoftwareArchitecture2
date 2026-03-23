package fit.se.be_microkernel.plugin.impl;

import fit.se.be_microkernel.plugin.CmsPlugin;
import org.springframework.stereotype.Component;

@Component
public class WordCountPlugin implements CmsPlugin {
    private boolean active = true;

    @Override public String getPluginId() { return "PLG-003"; }
    @Override public String getName() { return "Thống kê số từ"; }
    @Override public String getDescription() { return "Đếm và hiển thị số lượng từ ở cuối bài viết"; }

    @Override
    public String execute(String content) {
        if (content == null || content.isEmpty()) return content;

        // Đếm số từ dựa trên khoảng trắng
        int count = content.trim().split("\\s+").length;

        return content + "\n\n--- [Thống kê: " + count + " từ] ---";
    }

    @Override public boolean isActive() { return active; }
    @Override public void setActive(boolean active) { this.active = active; }
}
