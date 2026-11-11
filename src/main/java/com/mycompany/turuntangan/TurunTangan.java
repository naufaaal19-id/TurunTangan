package com.mycompany.turuntangan;

import java.util.ArrayList;
import java.util.Scanner;

public class TurunTangan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Relawan> daftarRelawan = new ArrayList<>();
        ArrayList<Kegiatan> daftarKegiatan = new ArrayList<>();
        Koordinator koordinator = new Koordinator("Naufal", "naufal@mail.com");
        boolean isRunning = true;

        System.out.println("=====================================");
        System.out.println("   SELAMAT DATANG DI TURUNTANGAN");
        System.out.println("=====================================");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Registrasi Relawan");
            System.out.println("2. Buat Kegiatan (Koordinator)");
            System.out.println("3. Daftar ke Kegiatan");
            System.out.println("4. Tutup Kegiatan & Hitung Jam");
            System.out.println("5. Lihat Rekap Relawan");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu (1-6): ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> {
                    System.out.print("Nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Keahlian: ");
                    String keahlian = scanner.nextLine();
                    daftarRelawan.add(new Relawan(nama, email, keahlian));
                    System.out.println("Relawan berhasil terdaftar!");
                }
                case 2 -> {
                    System.out.print("Nama kegiatan: ");
                    String nama = scanner.nextLine();
                    System.out.print("Lokasi: ");
                    String lokasi = scanner.nextLine();
                    System.out.print("Kuota: ");
                    int kuota = scanner.nextInt();
                    System.out.print("Durasi (jam): ");
                    int durasi = scanner.nextInt();
                    scanner.nextLine();
                    daftarKegiatan.add(new Kegiatan(nama, lokasi, kuota, durasi));
                    koordinator.tambahKegiatan();
                    System.out.println("Kegiatan berhasil dibuat!");
                }
                case 3 -> {
                    if (daftarRelawan.isEmpty() || daftarKegiatan.isEmpty()) {
                        System.out.println("Harus ada relawan dan kegiatan dulu.");
                        break;
                    }
                    System.out.println("Pilih relawan:");
                    for (int i = 0; i < daftarRelawan.size(); i++) {
                        System.out.println((i + 1) + ". " + daftarRelawan.get(i).getNama());
                    }
                    int r = scanner.nextInt() - 1;
                    System.out.println("Pilih kegiatan:");
                    for (int i = 0; i < daftarKegiatan.size(); i++) {
                        System.out.print((i + 1) + ". ");
                        daftarKegiatan.get(i).tampilkanInfo();
                    }
                    int k = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (r >= 0 && r < daftarRelawan.size() && k >= 0 && k < daftarKegiatan.size()) {
                        if (daftarKegiatan.get(k).daftar(daftarRelawan.get(r))) {
                            System.out.println("Berhasil mendaftar!");
                        }
                    } else {
                        System.out.println("Pilihan tidak valid.");
                    }
                }
                case 4 -> {
                    if (daftarKegiatan.isEmpty()) {
                        System.out.println("Belum ada kegiatan.");
                        break;
                    }
                    for (int i = 0; i < daftarKegiatan.size(); i++) {
                        System.out.print((i + 1) + ". ");
                        daftarKegiatan.get(i).tampilkanInfo();
                    }
                    System.out.print("Kegiatan yang ditutup: ");
                    int k = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (k >= 0 && k < daftarKegiatan.size()) {
                        daftarKegiatan.get(k).tutupKegiatan();
                    } else {
                        System.out.println("Pilihan tidak valid.");
                    }
                }
                case 5 -> {
                    koordinator.tampilkanInfo();
                    if (daftarRelawan.isEmpty()) {
                        System.out.println("Belum ada relawan.");
                    }
                    for (Relawan r : daftarRelawan) {
                        r.tampilkanInfo();
                    }
                }
                case 6 -> {
                    System.out.println("Terima kasih!");
                    isRunning = false;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();
    }
}