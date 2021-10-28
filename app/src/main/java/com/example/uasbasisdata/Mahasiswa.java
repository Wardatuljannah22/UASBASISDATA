package com.example.uasbasisdata;

public class Mahasiswa {
    String nim, nama, jurusan, jenisKelamin, alamat;
    boolean completed;

    public Mahasiswa(){

    }

    public Mahasiswa(String nim, String nama, String jurusan, String jenisKelamin, String alamat, boolean completed) {
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.completed = completed;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
