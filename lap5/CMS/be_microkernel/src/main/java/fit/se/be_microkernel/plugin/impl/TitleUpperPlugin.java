package fit.se.be_microkernel.plugin.impl;



import fit.se.be_microkernel.plugin.CmsPlugin;
import org.springframework.stereotype.Component;

@Component
public class TitleUpperPlugin implements CmsPlugin {
    private boolean active = true;

    @Override public String getPluginId() { return "PLG-002"; }
    @Override public String getName() { return "Viết hoa tiêu đề"; }
    @Override public String getDescription() { return "Tự động chuyển tiêu đề sang chữ IN HOA"; }

    @Override
    public String execute(String content) {
        // Plugin này không xử lý content nên trả về nguyên bản
        return content;
    }

    // Ghi đè logic đặc biệt: Microkernel có thể gọi riêng nếu cần,
    // hoặc bạn có thể xử lý trực tiếp trong CoreController
    public String executeTitle(String title) {
        return title != null ? title.toUpperCase() : "";
    }

    @Override public boolean isActive() { return active; }
    @Override public void setActive(boolean active) { this.active = active; }
}