package fit.se.StrategyPattern;

public class Product {
    private String name;
    private double price;
    private TaxStrategy taxStrategy;

    public Product(String name, double price, TaxStrategy taxStrategy) {
        this.name = name;
        this.price = price;
        this.taxStrategy = taxStrategy;
    }

    public void setTaxStrategy(TaxStrategy taxStrategy) {
        this.taxStrategy = taxStrategy;
    }

    public double calculateTotal() {
        double tax = taxStrategy.calculateTax(price);
        return price + tax;
    }

    public void showInfo() {
        System.out.println("Sản phẩm: " + name);
        System.out.println("Giá gốc: " + price);
        System.out.println("Thuế: " + taxStrategy.calculateTax(price));
        System.out.println("Tổng cộng: " + calculateTotal());
    }
}
