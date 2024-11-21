import java.util.ArrayList;
import java.util.Scanner;

// Kelas Kamar
class Kamar {
    private int nomorKamar;
    private String tipeKamar; // Single, Double, Suite
    private double hargaPerMalam;
    private boolean status; // true: tersedia, false: dipesan

    public Kamar(int nomorKamar, String tipeKamar, double hargaPerMalam) {
        this.nomorKamar = nomorKamar;
        this.tipeKamar = tipeKamar;
        this.hargaPerMalam = hargaPerMalam;
        this.status = true;
    }

    public void tampilkanInfoKamar() {
        System.out.println("Nomor Kamar: " + nomorKamar + ", Tipe: " + tipeKamar + 
                           ", Harga/Malam: " + hargaPerMalam + 
                           ", Status: " + (status ? "Tersedia" : "Dipesan"));
    }

    public boolean isTersedia() {
        return status;
    }

    public void reservasi() {
        if (status) {
            status = false;
            System.out.println("Kamar " + nomorKamar + " berhasil dipesan.");
        } else {
            System.out.println("Kamar " + nomorKamar + " sudah dipesan.");
        }
    }

    public void kosongkan() {
        status = true;
        System.out.println("Kamar " + nomorKamar + " telah dikosongkan.");
    }

    public int getNomorKamar() {
        return nomorKamar;
    }
}

// Kelas Tamu
class Tamu {
    private String nama;
    private String nomorIdentitas;
    private String kontak;

    public Tamu(String nama, String nomorIdentitas, String kontak) {
        this.nama = nama;
        this.nomorIdentitas = nomorIdentitas;
        this.kontak = kontak;
    }

    public void tampilkanInfoTamu() {
        System.out.println("Nama: " + nama + ", Nomor Identitas: " + nomorIdentitas + ", Kontak: " + kontak);
    }

    public String getNama() {
        return nama;
    }
}

// Kelas Hotel
class Hotel {
    private ArrayList<Kamar> daftarKamar = new ArrayList<>();
    private ArrayList<Tamu> daftarTamu = new ArrayList<>();

    public void tambahKamar(Kamar kamar) {
        daftarKamar.add(kamar);
        System.out.println("Kamar nomor " + kamar.getNomorKamar() + " berhasil ditambahkan.");
    }

    public void tambahTamu(Tamu tamu) {
        daftarTamu.add(tamu);
        System.out.println("Tamu " + tamu.getNama() + " berhasil terdaftar.");
    }

    public void daftarKamarTersedia() {
        System.out.println("\n=== Daftar Kamar Tersedia ===");
        for (Kamar kamar : daftarKamar) {
            if (kamar.isTersedia()) {
                kamar.tampilkanInfoKamar();
            }
        }
    }

    public ArrayList<Kamar> getDaftarKamar() {
        return daftarKamar;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Olyvia Shalsa Kusuma Hapsari");
        System.out.println("32602300106");
        System.out.println("Ujian Tengah Semester\n");
        
        Hotel hotel = new Hotel();

        // Tambahkan beberapa kamar default
        hotel.tambahKamar(new Kamar(101, "Single", 500000));
        hotel.tambahKamar(new Kamar(102, "Double", 750000));
        hotel.tambahKamar(new Kamar(103, "Suite", 1200000));

        // Tambahkan beberapa tamu default
        hotel.tambahTamu(new Tamu("Ali", "123456789", "08123456789"));
        hotel.tambahTamu(new Tamu("Budi", "987654321", "08987654321"));

        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== Menu Hotel ===");
            System.out.println("1. Tambah Kamar");
            System.out.println("2. Tambah Tamu");
            System.out.println("3. Tampilkan Kamar Tersedia");
            System.out.println("4. Pesan Kamar");
            System.out.println("5. Kosongkan Kamar");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nomor kamar: ");
                    int nomorKamar = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan buffer
                    System.out.print("Masukkan tipe kamar (Single/Double/Suite): ");
                    String tipeKamar = scanner.nextLine();
                    System.out.print("Masukkan harga per malam: ");
                    double hargaPerMalam = scanner.nextDouble();
                    scanner.nextLine(); // Membersihkan buffer
                    hotel.tambahKamar(new Kamar(nomorKamar, tipeKamar, hargaPerMalam));
                    break;

                case 2:
                    System.out.print("Masukkan nama tamu: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan nomor identitas: ");
                    String nomorIdentitas = scanner.nextLine();
                    System.out.print("Masukkan kontak: ");
                    String kontak = scanner.nextLine();
                    hotel.tambahTamu(new Tamu(nama, nomorIdentitas, kontak));
                    break;

                case 3:
                    hotel.daftarKamarTersedia();
                    break;

                case 4:
                    System.out.print("Masukkan nomor kamar yang ingin dipesan: ");
                    int nomorPesan = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan buffer
                    for (Kamar kamar : hotel.getDaftarKamar()) {
                        if (kamar.getNomorKamar() == nomorPesan) {
                            kamar.reservasi();
                            break;
                        }
                    }
                    break;

                case 5:
                    System.out.print("Masukkan nomor kamar yang ingin dikosongkan: ");
                    int nomorKosongkan = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan buffer
                    for (Kamar kamar : hotel.getDaftarKamar()) {
                        if (kamar.getNomorKamar() == nomorKosongkan) {
                            kamar.kosongkan();
                            break;
                        }
                    }
                    break;

                case 0:
                    System.out.println("Terima kasih telah menggunakan aplikasi manajemen hotel.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);

        scanner.close();
    }
}
