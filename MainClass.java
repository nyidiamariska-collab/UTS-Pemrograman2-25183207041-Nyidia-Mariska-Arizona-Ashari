import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
    private static final Scanner input = new Scanner(System.in);
    private static final ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();

    public static void main(String[] args) {
        // Data contoh agar fitur dapat langsung diuji.
        daftarKendaraan.add(new Mobil("BMW", "AE 1532 RY", 650000, 2, 10));
        daftarKendaraan.add(new Motor("Fazzio", "AG 2228 YZ", 120000, 2, "Matic"));

        int pilihan;
        do {
            tampilkanMenu();
            pilihan = bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    tambahKendaraan();
                    break;
                case 2:
                    tampilkanKendaraan();
                    break;
                case 3:
                    ubahData();
                    break;
                case 4:
                    hitungBiaya();
                    break;
                case 5:
                    detailKendaraan();
                    break;
                case 0:
                    System.out.println("Terima Kasih Telah Menggunakan Aplikasi Rental Kendaraan, Semoga Perjalanan Anda Menyenangkan");
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia. Silakan coba lagi.");
            }
        } while (pilihan != 0);
    }

    private static void tampilkanMenu() {
        System.out.println("\n==================================");
        System.out.println("       RENTAL KENDARAAN");
        System.out.println("==================================");
        System.out.println("1. Tambah Kendaraan");
        System.out.println("2. Tampilkan Kendaraan");
        System.out.println("3. Ubah Data");
        System.out.println("4. Hitung Biaya");
        System.out.println("5. Detail Kendaraan");
        System.out.println("0. Keluar");
        System.out.println("----------------------------------");
    }

    private static void tambahKendaraan() {
        System.out.println("\n--- TAMBAH KENDARAAN ---");
        System.out.println("1. Mobil");
        System.out.println("2. Motor");
        int jenis = bacaInt("Pilih jenis kendaraan: ");

        if (jenis != 1 && jenis != 2) {
            System.out.println("Jenis kendaraan tidak valid.");
            return;
        }

        try {
            System.out.print("Nama kendaraan : ");
            String nama = input.nextLine();
            System.out.print("Nomor plat     : ");
            String plat = input.nextLine();
            double harga = bacaDouble("Harga sewa/hari (Rp): ");
            int lama = bacaInt("Lama sewa (hari): ");

            Kendaraan kendaraan;
            if (jenis == 1) {
                int kursi = bacaInt("Jumlah kursi: ");
                kendaraan = new Mobil(nama, plat, harga, lama, kursi);
            } else {
                System.out.println("Jenis motor: Matic / Manual / Sport");
                System.out.print("Masukkan jenis motor: ");
                String jenisMotor = input.nextLine();
                kendaraan = new Motor(nama, plat, harga, lama, jenisMotor);
            }

            daftarKendaraan.add(kendaraan);
            System.out.println("Kendaraan berhasil ditambahkan.");
        } catch (IllegalArgumentException e) {
            System.out.println("Gagal menambahkan data: " + e.getMessage());
        }
    }

    private static void tampilkanKendaraan() {
        System.out.println("\n--- DAFTAR KENDARAAN ---");
        if (daftarKendaraan.isEmpty()) {
            System.out.println("Belum ada kendaraan yang tersimpan.");
            return;
        }

        System.out.printf("%-4s %-10s %-22s %-15s %-15s %-15s%n",
                "No.", "Jenis", "Nama", "Nomor Plat", "Harga/Hari", "Lama Sewa");
        System.out.println("--------------------------------------------------------------------------------");
        for (int i = 0; i < daftarKendaraan.size(); i++) {
            Kendaraan k = daftarKendaraan.get(i);
            System.out.printf("%-4d %-10s %-22s %-15s Rp%-13.0f %-15d%n",
                    i + 1, k.getJenisKendaraan(), k.getNamaKendaraan(),
                    k.getNomorPlat(), k.getHargaSewa(), k.getLamaSewa());
        }
    }

    private static void ubahData() {
        System.out.println("\n--- UBAH DATA KENDARAAN ---");
        Kendaraan k = pilihKendaraan();
        if (k == null) return;

        try {
            System.out.println("Kosongkan nama/plat jika tidak ingin mengubahnya.");
            System.out.print("Nama baru [" + k.getNamaKendaraan() + "]: ");
            String nama = input.nextLine();
            if (!nama.trim().isEmpty()) k.setNamaKendaraan(nama);

            System.out.print("Plat baru [" + k.getNomorPlat() + "]: ");
            String plat = input.nextLine();
            if (!plat.trim().isEmpty()) k.setNomorPlat(plat);

            System.out.println("Masukkan 0 jika harga/lama sewa tidak diubah.");
            double harga = bacaDouble("Harga sewa baru (Rp): ");
            if (harga != 0) k.setHargaSewa(harga);

            int lama = bacaInt("Lama sewa baru (hari): ");
            if (lama != 0) k.setLamaSewa(lama);

            if (k instanceof Mobil) {
                Mobil mobil = (Mobil) k;
                int kursi = bacaInt("Jumlah kursi baru (0 = tetap): ");
                if (kursi != 0) mobil.setJumlahKursi(kursi);
            } else if (k instanceof Motor) {
                Motor motor = (Motor) k;
                System.out.print("Jenis motor baru (kosong = tetap): ");
                String jenis = input.nextLine();
                if (!jenis.trim().isEmpty()) motor.setJenisMotor(jenis);
            }

            System.out.println("Data kendaraan berhasil diperbarui.");
        } catch (IllegalArgumentException e) {
            System.out.println("Perubahan tidak dapat dilakukan: " + e.getMessage());
        }
    }

    private static void hitungBiaya() {
        System.out.println("\n--- HITUNG BIAYA SEWA ---");
        Kendaraan k = pilihKendaraan();
        if (k == null) return;

        System.out.printf("Kendaraan : %s (%s)%n", k.getNamaKendaraan(), k.getNomorPlat());
        System.out.printf("Perhitungan: Rp%,.0f x %d hari%n",
                k.getHargaSewa(), k.getLamaSewa());
        System.out.printf("Total biaya: Rp%,.0f%n", k.hitungBiaya());
    }

    private static void detailKendaraan() {
        System.out.println("\n--- DETAIL KENDARAAN ---");
        Kendaraan k = pilihKendaraan();
        if (k != null) {
            System.out.println("----------------------------------");
            k.tampilkanInfo();
            System.out.println("----------------------------------");
        }
    }

    private static Kendaraan pilihKendaraan() {
        if (daftarKendaraan.isEmpty()) {
            System.out.println("Belum ada data kendaraan.");
            return null;
        }

        tampilkanKendaraan();
        int nomor = bacaInt("Pilih nomor kendaraan: ");
        if (nomor < 1 || nomor > daftarKendaraan.size()) {
            System.out.println("Nomor kendaraan tidak ditemukan.");
            return null;
        }
        return daftarKendaraan.get(nomor - 1);
    }

    private static int bacaInt(String pesan) {
        while (true) {
            System.out.print(pesan);
            String teks = input.nextLine();
            try {
                return Integer.parseInt(teks.trim());
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka bulat yang benar.");
            }
        }
    }

    private static double bacaDouble(String pesan) {
        while (true) {
            System.out.print(pesan);
            String teks = input.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(teks);
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang benar.");
            }
        }
    }
}
