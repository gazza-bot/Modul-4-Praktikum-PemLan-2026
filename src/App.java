import java.util.Scanner;

public class App {
    final static SistemTransaksi system = new SistemTransaksi();
    final static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Selamat datang, " + system.getNama());
        int pilihan;
        do {
            System.out.println("\n=== Menu Swalayan Tiny ===");
            System.out.println("1. Pembelian");
            System.out.println("2. Top Up Saldo");
            System.out.println("3. Cek Saldo");
            System.out.println("0. Keluar");
            System.out.print("Pilihan : ");
            pilihan = Integer.parseInt(input.nextLine());

            switch (pilihan) {
                case 1 -> pembelian();
                case 2 -> topup();
                case 3 -> system.displaySaldo();
                case 0 -> System.out.println("Terima kasih telah menggunakan layanan Swalayan Tiny.");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    private static void pembelian() {
        System.out.print("Masukkan nominal pembelian : ");
        double bill = Double.parseDouble(input.nextLine());
        system.transaksi(bill);
    }

    private static void topup() {
        System.out.print("Masukkan nominal top up : ");
        double tambahan = Double.parseDouble(input.nextLine());
        system.topUpSaldo(tambahan);
    }
}