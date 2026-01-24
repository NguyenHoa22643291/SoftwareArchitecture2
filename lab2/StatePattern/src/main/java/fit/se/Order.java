package fit.se;

public class Order {
    private OrderState state;
    public Order() {
        this.state = new NewState();
    }

    public void setState(OrderState state) {
        this.state = state;
    }
    public void process() {
        System.out.println("Trạng thái hiện tại: " + state.getStatusName());
        state.handleAction(this);
    }

}
