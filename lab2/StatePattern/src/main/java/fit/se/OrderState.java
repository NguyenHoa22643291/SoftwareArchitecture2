package fit.se;

public interface OrderState {
    void handleAction(Order order);
    String getStatusName();
}
