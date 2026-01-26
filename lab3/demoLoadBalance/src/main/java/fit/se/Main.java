package fit.se;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LoadBalancer lb = new LoadBalancer();

        // Giả lập việc mở rộng lên nhiều server (More Servers)
        lb.addServer(new AppServer("Server_Chinh_1"));
        lb.addServer(new AppServer("Server_Phu_2"));
        lb.addServer(new AppServer("Server_Backup_3"));

        System.out.println("--- Bắt đầu gửi4 6 requests liên tiếp ---");
        for (int i = 1; i <= 6; i++) {
            lb.dispatch("Gói tin số " + i);
        }
        //=>khac phuc loi qua nhieu truy cap cung mot luc
    }
}