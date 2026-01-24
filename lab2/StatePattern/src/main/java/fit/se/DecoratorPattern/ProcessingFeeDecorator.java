package fit.se.DecoratorPattern;

public class ProcessingFeeDecorator extends PaymentDecorator{
    //cong them phi xu ly 2$
    public ProcessingFeeDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public double getAmount() {
        return super.getAmount() + 2.0; // Cộng thêm 2$ phí
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Phí xử lý (2$)";
    }
}
