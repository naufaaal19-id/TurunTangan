package com.mycompany.turuntangan;

import java.util.ArrayList;

public class Kegiatan {
    private String nama;
    private String lokasi;
    private int kuota;
    private int durasiJam;
    private ArrayList<Relawan> peserta = new ArrayList<>();

    public Kegiatan(String nama, String lokasi, int kuota, int durasiJam) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.kuota = kuota;
        this.durasiJam = durasiJam;
    }

    public String getNama() { return nama; }
    public String getLokasi() { return lokasi; }
    public int getKuota() { return kuota; }
    public int getDurasiJam() { return durasiJam; }
    public ArrayList<Relawan> getPeserta() { return peserta; }

    public boolean daftar(Relawan r) {
        if (peserta.size() >= kuota) {
            System.out.println("Kuota penuh!");
            return false;
        }
        if (peserta.contains(r)) {
            System.out.println("Relawan sudah terdaftar!");
            return false;
        }
        peserta.add(r);
        return true;
    }

    // Tutup kegiatan: jam kontribusi otomatis ditambahkan ke semua peserta
    public void tutupKegiatan() {
        for (Relawan r : peserta) {
            r.tambahJam(durasiJam);
        }
        System.out.println("Kegiatan ditutup. " + peserta.size()
                + " relawan mendapat +" + durasiJam + " jam.");
    }

    public void tampilkanInfo() {
        System.out.println(nama + " @ " + lokasi + " | Kuota: "
                + peserta.size() + "/" + kuota + " | Durasi: " + durasiJam + " jam");
    }
}