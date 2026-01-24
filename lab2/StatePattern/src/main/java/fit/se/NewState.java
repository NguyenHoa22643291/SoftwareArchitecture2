package fit.se;

public class NewState implements OrderState{
    @Override
    public void handleAction(Order order) {
        System.out.println("Order is now in New State.");
        // Transition to the next state if needed
        order.setState(new ProcessingState());
    }

    @Override
    public String getStatusName() {
        return "New";
    }
}
