package com.nabilla.finalproject.model;

public class RotiModel {
    private String id;
    private String nama;
    private String harga;
    private String imgUrl;

    public RotiModel(String id, String nama, String harga, String imgUrl) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.imgUrl = imgUrl;
    }

    public RotiModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}

