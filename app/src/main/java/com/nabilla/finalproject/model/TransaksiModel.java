package com.nabilla.finalproject.model;

public class TransaksiModel {
    private String id;
    private String nama;
    private String harga;
    private String imgUrl;
    private String pemesan;
    private String latitude;
    private String longitude;

    public TransaksiModel(String id, String nama, String harga, String imgUrl, String pemesan, String latitude, String longitude) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.imgUrl = imgUrl;
        this.pemesan = pemesan;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public TransaksiModel() {

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

    public String getPemesan() {
        return pemesan;
    }

    public void setPemesan(String pemesan) {
        this.pemesan = pemesan;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}