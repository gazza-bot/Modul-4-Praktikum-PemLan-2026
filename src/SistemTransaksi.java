import java.util.Scanner;

public class SistemTransaksi {
    final static Scanner input = new Scanner(System.in);
    final private String nama = "Albert Supriyanto";
    final private String noPelanggan = "7477123781";
    final private String PIN = "1111";
    private String jenisPelanggan;
    private String idUnique;
    private double cashback;
    private double cashbackNormal;
    private double saldo = 100000;
    private boolean blocked = false;
    private int authAttempts = 0;

    private boolean auth(String noPelanggan, String PIN) {
        if (blocked) {
            System.out.println("Akun Anda telah diblokir.");
            return false;
        }
        if (noPelanggan.equals(this.noPelanggan) && PIN.equals(this.PIN)) {
            authAttempts = 0;
            return true;
        }
        authAttempts++;
        if (authAttempts >= 3) {
            blocked = true;
            System.out.println("Akun Anda telah diblokir karena 3x kesalahan autentifikasi.");
        } else {
            System.out.println("Autentifikasi gagal! Percobaan ke-" + authAttempts + " dari 3.");
        }
        return false;
    }

    private void jenisRek() {
        idUnique = noPelanggan.substring(0, 2);
        switch (idUnique) {
            case "38" -> {
                this.jenisPelanggan = "Silver";
                this.cashback = 0.05;
                this.cashbackNormal = 0;
            }
            case "56" -> {
                this.jenisPelanggan = "Gold";
                this.cashback = 0.07;
                this.cashbackNormal = 0.02;
            }
            case "74" -> {
                this.jenisPelanggan = "Platinum";
                this.cashback = 0.1;
                this.cashbackNormal = 0.05;
            }
            default -> this.jenisPelanggan = "invalid";
        }
    }

    public void transaksi(double bill) {
        if (blocked) {
            System.out.println("Akun Anda telah diblokir.");
            return;
        }

        System.out.print("Masukkan Nomor Pelanggan : ");
        String nopel = input.nextLine();
        System.out.print("Masukkan PIN : ");
        String pin = input.nextLine();

        if (!auth(nopel, pin)) {
            return;
        }

        jenisRek();
        if (jenisPelanggan.equals("invalid")) {
            System.out.println("Jenis rekening tidak valid, transaksi tidak dapat dilakukan.");
            return;
        }

        double biaya;
        if (bill >= 1000000) {
            biaya = bill - (bill * cashback);
        } else {
            biaya = bill - (bill * cashbackNormal);
        }

        if (saldo - biaya < 10000) {
            System.out.println("Saldo tidak mencukupi. Transaksi gagal!");
            return;
        }

        saldo -= biaya;
        System.out.println("Transaksi berhasil!");
        System.out.printf("Cashback yang diterima : Rp. %.2f%n", bill - biaya);
        displaySaldo();
    }

    public void topUpSaldo(double tambahan) {
        if (blocked) {
            System.out.println("Akun Anda telah diblokir.");
            return;
        }

        System.out.print("Masukkan Nomor Pelanggan : ");
        String nopel = input.nextLine();
        System.out.print("Masukkan PIN : ");
        String pin = input.nextLine();

        if (!auth(nopel, pin)) {
            return;
        }

        if (tambahan <= 0) {
            System.out.println("Nominal top up tidak valid.");
            return;
        }

        saldo += tambahan;
        System.out.println("Top up berhasil!");
        displaySaldo();
    }

    public void displaySaldo() {
        System.out.println("==================");
        System.out.println("Nama Pemilik Rekening : " + nama);
        System.out.printf("Saldo : Rp. %.2f%n", saldo);
        System.out.println("==================");
    }

    public String getNama() {
        return nama;
    }

    public boolean isBlocked() {
        return blocked;
    }
}