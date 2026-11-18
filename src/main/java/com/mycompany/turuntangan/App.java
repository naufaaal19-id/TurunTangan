package com.mycompany.turuntangan;

import java.util.ArrayList;
import java.util.HashSet;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private final ArrayList<Relawan> daftarRelawan = new ArrayList<>();
    private final ArrayList<Kegiatan> daftarKegiatan = new ArrayList<>();
    private final HashSet<Kegiatan> sudahDitutup = new HashSet<>();

    private final ComboBox<String> cbRelawan = new ComboBox<>();
    private final ComboBox<String> cbKegiatan = new ComboBox<>();
    private final ListView<String> listRekap = new ListView<>();

    @Override
    public void start(Stage stage) {
        Tab tabRelawan = new Tab("Relawan", buatFormRelawan());
        Tab tabKegiatan = new Tab("Kegiatan", buatFormKegiatan());
        Tab tabDaftar = new Tab("Pendaftaran", buatFormPendaftaran());
        Tab tabRekap = new Tab("Rekap", buatRekap());

        TabPane tabs = new TabPane(tabRelawan, tabKegiatan, tabDaftar, tabRekap);
        tabs.getTabs().forEach(t -> t.setClosable(false));

        refreshData();

        stage.setTitle("TurunTangan - Manajemen Relawan");
        stage.setScene(new Scene(tabs, 620, 440));
        stage.show();
    }

    // ---------- Tab Relawan ----------

    private GridPane buatFormRelawan() {
        TextField tfNama = new TextField();
        TextField tfEmail = new TextField();
        TextField tfKeahlian = new TextField();
        Button btnDaftar = new Button("Daftarkan Relawan");

        btnDaftar.setOnAction(e -> {
            String nama = tfNama.getText().trim();
            String email = tfEmail.getText().trim();
            String keahlian = tfKeahlian.getText().trim();

            // Validasi 1: kolom tidak boleh kosong
            if (nama.isEmpty() || email.isEmpty() || keahlian.isEmpty()) {
                tampilAlert(Alert.AlertType.WARNING, "Semua kolom wajib diisi!");
                return;
            }

            // Validasi 2: email harus berakhiran @gmail.com
            if (!email.endsWith("@gmail.com") || email.length() <= 10) {
                tampilAlert(Alert.AlertType.WARNING, "Email harus berakhiran @gmail.com!");
                return;
            }

            daftarRelawan.add(new Relawan(nama, email, keahlian));
            refreshData();
            tfNama.clear();
            tfEmail.clear();
            tfKeahlian.clear();
            tampilAlert(Alert.AlertType.INFORMATION, "Relawan " + nama + " berhasil terdaftar!");
        });

        GridPane grid = buatGrid();
        grid.addRow(0, new Label("Nama"), tfNama);
        grid.addRow(1, new Label("Email"), tfEmail);
        grid.addRow(2, new Label("Keahlian"), tfKeahlian);
        grid.add(btnDaftar, 1, 3);
        return grid;
    }

    // ---------- Tab Kegiatan ----------

    private GridPane buatFormKegiatan() {
        TextField tfNama = new TextField();
        TextField tfLokasi = new TextField();
        TextField tfKuota = new TextField();
        TextField tfDurasi = new TextField();
        Button btnBuat = new Button("Buat Kegiatan");

        btnBuat.setOnAction(e -> {
            String nama = tfNama.getText().trim();
            String lokasi = tfLokasi.getText().trim();

            // Validasi 1: kolom tidak boleh kosong
            if (nama.isEmpty() || lokasi.isEmpty()
                    || tfKuota.getText().trim().isEmpty() || tfDurasi.getText().trim().isEmpty()) {
                tampilAlert(Alert.AlertType.WARNING, "Semua kolom wajib diisi!");
                return;
            }

            // Validasi 2: kuota dan durasi harus angka (try-catch)
            try {
                int kuota = Integer.parseInt(tfKuota.getText().trim());
                int durasi = Integer.parseInt(tfDurasi.getText().trim());

                // Validasi 3: harus lebih dari 0
                if (kuota <= 0 || durasi <= 0) {
                    tampilAlert(Alert.AlertType.WARNING, "Kuota dan durasi harus lebih dari 0!");
                    return;
                }

                daftarKegiatan.add(new Kegiatan(nama, lokasi, kuota, durasi));
                refreshData();
                tfNama.clear();
                tfLokasi.clear();
                tfKuota.clear();
                tfDurasi.clear();
                tampilAlert(Alert.AlertType.INFORMATION, "Kegiatan " + nama + " berhasil dibuat!");
            } catch (NumberFormatException ex) {
                tampilAlert(Alert.AlertType.ERROR, "Kuota dan durasi harus berupa angka (contoh: 300)!");
            }
        });

        GridPane grid = buatGrid();
        grid.addRow(0, new Label("Nama kegiatan"), tfNama);
        grid.addRow(1, new Label("Lokasi"), tfLokasi);
        grid.addRow(2, new Label("Kuota"), tfKuota);
        grid.addRow(3, new Label("Durasi (jam)"), tfDurasi);
        grid.add(btnBuat, 1, 4);
        return grid;
    }

    // ---------- Tab Pendaftaran ----------

    private VBox buatFormPendaftaran() {
        cbRelawan.setPromptText("Pilih relawan");
        cbKegiatan.setPromptText("Pilih kegiatan");
        cbRelawan.setPrefWidth(320);
        cbKegiatan.setPrefWidth(320);

        Button btnDaftar = new Button("Daftarkan ke Kegiatan");
        Button btnTutup = new Button("Tutup Kegiatan dan Hitung Jam");

        btnDaftar.setOnAction(e -> {
            int r = cbRelawan.getSelectionModel().getSelectedIndex();
            int k = cbKegiatan.getSelectionModel().getSelectedIndex();
            if (r < 0 || k < 0) {
                tampilAlert(Alert.AlertType.WARNING, "Pilih relawan dan kegiatan terlebih dahulu!");
                return;
            }
            if (daftarKegiatan.get(k).daftar(daftarRelawan.get(r))) {
                refreshData();
                tampilAlert(Alert.AlertType.INFORMATION, "Berhasil mendaftar!");
            } else {
                tampilAlert(Alert.AlertType.WARNING, "Gagal: kuota penuh atau relawan sudah terdaftar.");
            }
        });

        btnTutup.setOnAction(e -> {
            int k = cbKegiatan.getSelectionModel().getSelectedIndex();
            if (k < 0) {
                tampilAlert(Alert.AlertType.WARNING, "Pilih kegiatan yang ingin ditutup!");
                return;
            }
            Kegiatan kegiatan = daftarKegiatan.get(k);
            if (sudahDitutup.contains(kegiatan)) {
                tampilAlert(Alert.AlertType.WARNING, "Kegiatan ini sudah ditutup sebelumnya.");
                return;
            }
            kegiatan.tutupKegiatan();
            sudahDitutup.add(kegiatan);
            refreshData();
            tampilAlert(Alert.AlertType.INFORMATION, "Kegiatan ditutup, jam kontribusi sudah ditambahkan.");
        });

        VBox box = new VBox(12, new Label("Relawan"), cbRelawan,
                new Label("Kegiatan"), cbKegiatan, btnDaftar, btnTutup);
        box.setPadding(new Insets(20));
        return box;
    }

    // ---------- Tab Rekap (dengan fitur hapus) ----------

    private VBox buatRekap() {
        Button btnHapus = new Button("Hapus Relawan Terpilih");

        btnHapus.setOnAction(e -> {
            int index = listRekap.getSelectionModel().getSelectedIndex();
            if (index < 0) {
                tampilAlert(Alert.AlertType.WARNING, "Pilih dulu relawan yang ingin dihapus!");
                return;
            }

            Relawan target = daftarRelawan.get(index);

            // Dialog konfirmasi sebelum menghapus
            Alert konfirmasi = new Alert(Alert.AlertType.CONFIRMATION,
                    "Hapus relawan " + target.getNama() + "?", ButtonType.OK, ButtonType.CANCEL);
            konfirmasi.setHeaderText(null);
            if (konfirmasi.showAndWait().get() != ButtonType.OK) {
                return;
            }

            // Keluarkan juga dari semua kegiatan
            for (Kegiatan k : daftarKegiatan) {
                k.getPeserta().remove(target);
            }
            daftarRelawan.remove(target);
            refreshData();
            tampilAlert(Alert.AlertType.INFORMATION, "Relawan berhasil dihapus.");
        });

        VBox box = new VBox(10, new Label("Rekap Jam Kontribusi Relawan"), listRekap, btnHapus);
        box.setPadding(new Insets(20));
        return box;
    }

    // ---------- Fungsi bantu ----------

    private GridPane buatGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        return grid;
    }

    private void tampilAlert(Alert.AlertType tipe, String pesan) {
        Alert alert = new Alert(tipe);
        alert.setTitle("TurunTangan");
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }

    // Perbarui ComboBox dan daftar rekap setiap ada perubahan data
    private void refreshData() {
        cbRelawan.getItems().clear();
        for (Relawan r : daftarRelawan) {
            cbRelawan.getItems().add(r.getNama());
        }

        cbKegiatan.getItems().clear();
        for (Kegiatan k : daftarKegiatan) {
            cbKegiatan.getItems().add(k.getNama() + " (" + k.getPeserta().size() + "/" + k.getKuota() + ")");
        }

        listRekap.getItems().clear();
        for (Relawan r : daftarRelawan) {
            listRekap.getItems().add(r.getNama() + " | " + r.getEmail() + " | " + r.getKeahlian()
                    + " | Jam kontribusi: " + r.getJamKontribusi());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}