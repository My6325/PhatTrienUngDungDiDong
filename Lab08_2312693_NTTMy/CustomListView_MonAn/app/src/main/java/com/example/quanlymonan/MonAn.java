package com.example.quanlymonan;

public class MonAn {
    private final int id;
    private final String ten;
    private final String moTa;
    private final int gia;
    private final int hinhResId;
    private final String hinhUri;

    // Private master constructor — tránh lặp 5 dòng assignment
    private MonAn(int id, String ten, String moTa, int gia, int hinhResId, String hinhUri) {
        this.id = id; this.ten = ten; this.moTa = moTa;
        this.gia = gia; this.hinhResId = hinhResId; this.hinhUri = hinhUri;
    }

    /** Ảnh drawable mẫu */
    public MonAn(int id, String ten, String moTa, int gia, int hinhResId) {
        this(id, ten, moTa, gia, hinhResId, null);
    }

    /** Ảnh từ gallery (content URI string) */
    public MonAn(int id, String ten, String moTa, int gia, String hinhUri) {
        this(id, ten, moTa, gia, 0, hinhUri);
    }

    public int getId()         { return id; }
    public String getTen()     { return ten; }
    public String getMoTa()    { return moTa; }
    public int getGia()        { return gia; }
    public int getHinhResId()  { return hinhResId; }
    public String getHinhUri() { return hinhUri; }
}
