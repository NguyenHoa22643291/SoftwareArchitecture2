package fit.se.StrategyPattern;

public class LuxuryTax implements TaxStrategy{
    @Override
    public double calculateTax(double price) {
        return price * 0.25; // 15% luxury tax
    }
}
