package fit.se.DecoratorPattern;

public class DiscountDecorator extends PaymentDecorator {
    //ap dung ma giam gia
    public DiscountDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public double getAmount() {
        return super.getAmount() * 0.9; // Giảm 10%
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Mã giảm giá (10%)";
    }
}
