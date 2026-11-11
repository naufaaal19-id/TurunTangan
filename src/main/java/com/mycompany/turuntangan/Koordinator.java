package com.mycompany.turuntangan;

public class Koordinator extends Pengguna {
    private int jumlahKegiatan;

    public Koordinator(String nama, String email) {
        super(nama, email);
        this.jumlahKegiatan = 0;
    }

    public int getJumlahKegiatan() { return jumlahKegiatan; }

    public void tambahKegiatan() { jumlahKegiatan++; }

    @Override
    public String getPeran() { return "Koordinator"; }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("   Kegiatan dikelola: " + jumlahKegiatan);
    }
}