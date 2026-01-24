package fit.se.DecoratorPattern;

public abstract class PaymentDecorator implements Payment {
    protected Payment decoratedPayment;

    public PaymentDecorator(Payment decoratedPayment) {
        this.decoratedPayment = decoratedPayment;
    }

    @Override
    public double getAmount() {
        return decoratedPayment.getAmount();
    }
    @Override
    public String getDescription() {
        return decoratedPayment.getDescription();
    }
}
