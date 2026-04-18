package com.example.phim;

public class Music {
    private String name;
    private String imageName;
    private int luotBan;

    public Music(String name, String imageName, int luotBan) {
        this.name = name;
        this.imageName = imageName;
        this.luotBan = luotBan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getLuotBan() {
        return luotBan;
    }

    public void setGenre(int luotBan) {
        this.luotBan = luotBan;
    }
}