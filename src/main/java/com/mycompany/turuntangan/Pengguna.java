package com.mycompany.turuntangan;

public class Pengguna {
    private String nama;
    private String email;

    public Pengguna(String nama, String email) {
        this.nama = nama;
        this.email = email;
    }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPeran() { return "Pengguna"; }

    public void tampilkanInfo() {
        System.out.println("[" + getPeran() + "] " + nama + " (" + email + ")");
    }
}