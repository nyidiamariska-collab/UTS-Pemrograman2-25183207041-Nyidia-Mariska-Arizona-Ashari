public class Motor extends Kendaraan {
    private String jenisMotor;

    public Motor(String namaKendaraan, String nomorPlat, double hargaSewa, int lamaSewa, String jenisMotor) {
        super(namaKendaraan, nomorPlat, hargaSewa, lamaSewa);
        setJenisMotor(jenisMotor);
    }

    public String getJenisMotor() {
        return jenisMotor;
    }

    public void setJenisMotor(String jenisMotor) {
        if (jenisMotor == null) {
            throw new IllegalArgumentException("Jenis motor harus diisi.");
        }

        String jenis = jenisMotor.trim();
        if (jenis.equalsIgnoreCase("Matic")) {
            this.jenisMotor = "Matic";
        } else if (jenis.equalsIgnoreCase("Manual")) {
            this.jenisMotor = "Manual";
        } else if (jenis.equalsIgnoreCase("Sport")) {
            this.jenisMotor = "Sport";
        } else {
            throw new IllegalArgumentException(
                "Jenis motor hanya boleh Matic, Manual, atau Sport."
            );
        }
    }

    public String getJenisKendaraan() {
        return "Motor";
    }

    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis Motor     : " + jenisMotor);
    }
}

