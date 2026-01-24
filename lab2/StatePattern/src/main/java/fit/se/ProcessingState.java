package fit.se;

public class ProcessingState implements OrderState{
    @Override
    public void handleAction(Order order) {
        System.out.println("[XỬ LÝ]: Đang đóng gói hàng hóa và bàn giao cho đơn vị vận chuyển...");
        order.setState(new DeliveredState());
    }

    @Override
    public String getStatusName() {
        return "ĐANG XỬ LÝ";
    }
}
