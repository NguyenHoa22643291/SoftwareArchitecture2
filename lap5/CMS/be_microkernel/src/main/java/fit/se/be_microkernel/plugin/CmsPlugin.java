package fit.se.be_microkernel.plugin;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"active"}) // Ẩn bớt để tránh lỗi JSON khi serialize
public interface CmsPlugin {
    String getPluginId();
    String getName();
    String getDescription();

    // Logic xử lý nội dung
    String execute(String content);

    // Quản lý trạng thái Bật/Tắt
    boolean isActive();
    void setActive(boolean active);
}