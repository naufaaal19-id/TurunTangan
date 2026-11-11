# TurunTangan

Aplikasi manajemen relawan dan kegiatan sosial berbasis Java.
Proyek akhir mata kuliah **Pemrograman Berorientasi Objek (PBO)**, S1 Sistem Informasi, Universitas Lampung.

| | |
|---|---|
| **Nama** | Muhammad Naufal Rofiqurrohim |
| **NPM** | 2517052016 |
| **Kelas** | Sistem Informasi A |

> **Status:** versi awal (Tugas 4), berjalan di terminal dengan input `Scanner`.

## Latar Belakang

Pendaftaran, pembagian tugas, dan penghitungan jam kontribusi relawan sering dilakukan manual lewat grup chat dan catatan, sehingga tidak rapi, jadwal bisa bentrok, dan kontribusi sulit didokumentasikan. TurunTangan dibuat untuk mempermudah pengelolaan relawan dan kegiatan sosial, serta mencatat jam kontribusi relawan secara otomatis.

## Fitur pada Versi Ini

1. **Registrasi relawan**: nama, email, dan keahlian.
2. **Buat kegiatan** (koordinator): nama, lokasi, kuota, dan durasi jam.
3. **Pendaftaran kegiatan**: relawan mendaftar ke kegiatan, dengan pengecekan kuota dan pendaftaran ganda.
4. **Tutup kegiatan**: jam kontribusi otomatis ditambahkan ke semua peserta.
5. **Rekap relawan**: menampilkan keahlian dan total jam kontribusi tiap relawan.

## Penerapan Materi PBO

| Materi | Penerapan |
|---|---|
| Input `Scanner` dan control flow | Menu interaktif dengan `Scanner`, `while`, `switch`, `if/else`, dan `for` di `TurunTangan.java` |
| Class dan Object | 6 class, dengan objek dibuat memakai `new` di method `main` |
| Encapsulation | Semua field `private`, diakses lewat getter dan setter, dan `tambahJam()` memvalidasi nilai |
| Inheritance | `Relawan`, `Koordinator`, dan `Admin` mewarisi `Pengguna` memakai `extends`, `super`, dan `@Override` |

## Struktur Class

```
Pengguna            (superclass: nama, email)
├── Relawan         (keahlian, jamKontribusi)
├── Koordinator     (jumlahKegiatan)
└── Admin

Kegiatan            (nama, lokasi, kuota, durasiJam, daftar peserta)
TurunTangan         (main class: menu Scanner)
```

Lokasi source code: `src/main/java/com/mycompany/turuntangan/`

## Cara Menjalankan

**Prasyarat:** JDK 17 atau lebih baru dan Maven (atau NetBeans).

**Lewat NetBeans**

1. Buka project (**File → Open Project**).
2. Klik kanan `TurunTangan.java` → **Run File** (Shift+F6).
3. Masukkan input di panel **Output**.

**Lewat terminal**

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.mycompany.turuntangan.TurunTangan"
```

## Contoh Penggunaan

Saat diminta angka (menu, kuota, durasi, nomor), ketik **angkanya saja** (contoh: `300`, bukan `300 peserta`).

Urutan uji yang disarankan: `1` (daftar relawan, dua kali) → `2` (buat kegiatan) → `3` (daftarkan relawan, dua kali) → `4` (tutup kegiatan) → `5` (lihat rekap) → `6` (keluar).

## Rencana Pengembangan

- [ ] Login dan tampilan per peran (Relawan, Koordinator, Admin)
- [ ] Rekomendasi penugasan relawan otomatis (keahlian, ketersediaan, dan beban tugas)
- [ ] Presensi kegiatan
- [ ] Penyimpanan data ke file (File I/O dan Serialization)
- [ ] Laporan, statistik, dan ekspor CSV
- [ ] Antarmuka grafis (JavaFX)

## Catatan

Data koordinator pada versi ini masih berupa data awal di dalam kode (`TurunTangan.java`). Data relawan dan kegiatan hanya tersimpan di memori, sehingga hilang saat program ditutup.
