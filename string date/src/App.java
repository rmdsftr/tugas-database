import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== APLIKASI PENJUALAN ===");
            System.out.println("1. Input Transaksi");
            System.out.println("2. Tampilkan Transaksi");
            System.out.println("3. Update Total Bayar");
            System.out.println("4. Hapus Transaksi");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu (0-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    Penjualan penjualan = new Penjualan();
                    Database.insertTransaksi(penjualan);
                    break;
                case 2:
                    Database.retrieveTransaksi();
                    break;
                case 3:
                    System.out.print("Masukkan nomor faktur yang ingin diupdate: ");
                    String fakturUpdate = scanner.nextLine();
                    System.out.print("Masukkan total bayar baru: ");
                    int newTotalBayar = scanner.nextInt();
                    Database.updateTransaksi(fakturUpdate, newTotalBayar);
                    break;
                case 4:
                    System.out.print("Masukkan nomor faktur yang ingin dihapus: ");
                    String fakturDelete = scanner.nextLine();
                    Database.deleteTransaksi(fakturDelete);
                    break;
                case 0:
                    System.out.println("Terima kasih! Keluar dari aplikasi.");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
            }
        }
    }
}
