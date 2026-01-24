package fit.se;

import fit.se.StrategyPattern.ConsumptionTax;
import fit.se.StrategyPattern.LuxuryTax;
import fit.se.StrategyPattern.Product;
import fit.se.StrategyPattern.VATTax;

public class Main {
    public static void main(String[] args) {
//        System.out.println("--- MÔ PHỎNG LUỒNG ĐƠN HÀNG THÀNH CÔNG ---");
//        Order order1 = new Order();
//        order1.process(); // Chạy NewState -> Chuyển sang Processing
//        order1.process(); // Chạy ProcessingState -> Chuyển sang Delivered
//        order1.process(); // Chạy DeliveredState
//
//        System.out.println("\n--- MÔ PHỎNG ĐƠN HÀNG BỊ HỦY ---");
//        Order order2 = new Order();
//        order2.setState(new CancelledState()); // Ép sang trạng thái Hủy
//        order2.process();

        //Strategy ap dung cho bai thue
        // 1. Sản phẩm áp dụng thuế VAT
        Product laptop = new Product("Laptop", 1000, new VATTax());
        laptop.showInfo();
        System.out.println("-----------------");

        // 2. Sản phẩm áp dụng thuế Xa xỉ
        Product xeHoi = new Product("Xe hơi", 50000, new LuxuryTax());
        xeHoi.showInfo();
        System.out.println("-----------------");

        // 3. Thay đổi chiến lược thuế linh hoạt
        System.out.println("Thay đổi Laptop từ thuế VAT sang thuế Tiêu thụ:");
        laptop.setTaxStrategy(new ConsumptionTax());
        laptop.showInfo();
    }
}