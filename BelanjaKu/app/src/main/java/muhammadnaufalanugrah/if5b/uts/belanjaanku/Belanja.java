package muhammadnaufalanugrah.if5b.uts.belanjaanku;

public class Belanja {
    private String id, nama, kategori, harga;

    public Belanja(String id, String nama, String kategori, String harga) {
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
    }

    public Belanja(String nama, String kategori, String harga) {
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getKategori() {
        return kategori;
    }

    public String getHarga() {
        return harga;
    }
}
