package fit.se;
public class Main {
    public static void main(String[] args) {
        System.out.println("--- MÔ PHỎNG LUỒNG ĐƠN HÀNG THÀNH CÔNG ---");
        Order order1 = new Order();
        order1.process(); // Chạy NewState -> Chuyển sang Processing
        order1.process(); // Chạy ProcessingState -> Chuyển sang Delivered
        order1.process(); // Chạy DeliveredState

        System.out.println("\n--- MÔ PHỎNG ĐƠN HÀNG BỊ HỦY ---");
        Order order2 = new Order();
        order2.setState(new CancelledState()); // Ép sang trạng thái Hủy
        osrder2.process();
    }
}