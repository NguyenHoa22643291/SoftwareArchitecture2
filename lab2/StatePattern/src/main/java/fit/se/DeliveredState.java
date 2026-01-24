package fit.se;

public class DeliveredState implements OrderState {
    @Override
    public void handleAction(Order order) {
        System.out.println("[XỬ LÝ]: Đã giao hàng thành công. Cập nhật hệ thống: Hoàn tất đơn hàng.");
    }

    @Override
    public String getStatusName() {
        return "ĐÃ GIAO";
    }
}