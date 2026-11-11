package com.mycompany.turuntangan;

public class Relawan extends Pengguna {
    private String keahlian;
    private int jamKontribusi;

    public Relawan(String nama, String email, String keahlian) {
        super(nama, email);
        this.keahlian = keahlian;
        this.jamKontribusi = 0;
    }

    public String getKeahlian() { return keahlian; }
    public void setKeahlian(String keahlian) { this.keahlian = keahlian; }

    public int getJamKontribusi() { return jamKontribusi; }

    public void tambahJam(int jam) {
        if (jam > 0) {
            this.jamKontribusi += jam;
        } else {
            System.out.println("Jam harus lebih dari 0!");
        }
    }

    @Override
    public String getPeran() { return "Relawan"; }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("   Keahlian: " + keahlian + " | Jam kontribusi: " + jamKontribusi);
    }
}