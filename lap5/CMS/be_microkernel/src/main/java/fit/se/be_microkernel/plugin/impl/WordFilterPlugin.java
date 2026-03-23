package fit.se.be_microkernel.plugin.impl;

import fit.se.be_microkernel.plugin.CmsPlugin;
import org.springframework.stereotype.Component;

@Component
public class WordFilterPlugin implements CmsPlugin {
    private boolean active = true;

    @Override public String getPluginId() { return "PLG-001"; }
    @Override public String getName() { return "Lọc từ nhạy cảm"; }
    @Override public String getDescription() { return "Tự động đổi 'badword' thành '***'"; }

    @Override
    public String execute(String content) {
        if (content == null) return "";
        return content.replaceAll("(?i)badword", "***");
    }

    @Override public boolean isActive() { return active; }
    @Override public void setActive(boolean active) { this.active = active; }
}