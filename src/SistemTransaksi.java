public class SistemTransaksi {
    final private String nama = "Albert Supriyanto";
    final private String noPelanggan = "7477123781";
    final private String PIN = "1111";
    private String jenisPelanggan;
    private String idUnique;
    private double cashback;
    private double cashbackNormal;
    private int saldo = 100000000;

    public boolean auth(String noPelanggan, String PIN){
        return noPelanggan.equals(this.noPelanggan) && PIN.equals(this.PIN);
    }

    private void jenisRek() {
        idUnique = noPelanggan.substring(0, 2);
        switch (idUnique) {
            case "38" -> {
                this.jenisPelanggan = "Silver";
                this.cashback = 0.05;
                this.cashbackNormal = 1;
            }
            case "56" -> {
                this.jenisPelanggan = "Gold";
                this.cashback = 0.07;
                this.cashbackNormal = 0.02;
            }
            case "74" -> {
                this.jenisPelanggan = "Platinum";
                this.cashback= 0.1;
                this.cashbackNormal = 0.05;
            }
            default -> this.jenisPelanggan = "invalid";
        }
    }

    public void transaksi(int bill){
        this.jenisRek();
        switch(this.jenisPelanggan){
            case ("Silver") ->{
                if(bill >= 1000000){
                    this.saldo -= (bill - (bill * cashback));
                } else {
                    this.saldo -= (bill - (bill * cashbackNormal));
                }
            }
            case ("Gold") ->{
                if(bill >= 1000000){
                    this.saldo -= (bill - (bill * cashback));
                } else {
                    this.saldo -= (bill - (bill * cashbackNormal));
                }
            }
            case ("Platinom") ->{
                if(bill >= 1000000){
                    this.saldo -= (bill - (bill * cashback));
                } else {
                    this.saldo -= (bill - (bill * cashbackNormal));
                }
            }

            default ->{
                System.out.println("Jenis Rekening invalid, tidak bisa melakukan pembayaran");
            }
        }
    }

    public void topUpSaldo(int saldo){
        if(saldo <= 10000){
            System.out.println("Saldo Kurang");
        }else {
            this.saldo = saldo;
        }
    }

    public void displaySaldo(){
        System.out.println("==================");
        System.out.println("Nama Pemilik Rekening : " + this.nama);
        System.out.println("Saldo : Rp. " + this.saldo);
        System.out.println("==================");
    }

    public String getNama(){
        return this.nama;
    }
}
