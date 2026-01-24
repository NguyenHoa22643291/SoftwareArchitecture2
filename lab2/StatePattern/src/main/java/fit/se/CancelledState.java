package fit.se;

public class CancelledState implements OrderState {
    @Override
    public void handleAction(Order order) {
        System.out.println("[XỬ LÝ]: Đơn hàng bị hủy. Đang tiến hành hoàn tiền cho khách hàng...");
    }

    @Override
    public String getStatusName() {
        return "ĐÃ HỦY";
    }
}