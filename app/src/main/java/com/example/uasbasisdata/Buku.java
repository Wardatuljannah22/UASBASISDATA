package com.example.uasbasisdata;

public class Buku {
    String id, judulbuku, penulis, penerbit, stok;

    public Buku(){
    }

    public Buku(String judulbuku, String penulis, String penerbit, String stok) {
        this.judulbuku = judulbuku;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.stok = stok;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudulbuku() {
        return judulbuku;
    }

    public void setJudulbuku(String judulbuku) {
        this.judulbuku = judulbuku;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }
}
