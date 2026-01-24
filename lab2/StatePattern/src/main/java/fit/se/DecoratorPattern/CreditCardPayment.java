package fit.se.DecoratorPattern;

public class CreditCardPayment implements Payment {
    //phuong thuc thanh toan bang the tin dung
    private double amount;
    public CreditCardPayment(double amount) {
        this.amount = amount;
    }
    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public String getDescription() {
        return "Credit Card";
    }
}
