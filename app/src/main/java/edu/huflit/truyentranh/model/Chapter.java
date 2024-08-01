package edu.huflit.truyentranh.model;

import java.io.Serializable;

public class Chapter implements Serializable {
    private int id;
    private int idTruyen;
    private String nameChapter;

    private String url;

    public Chapter(int id, int idTruyen, String nameChapter, String url) {
        this.id = id;
        this.idTruyen = idTruyen;
        this.nameChapter = nameChapter;
        this.url = url;
    }

    public Chapter(int idTruyen, String nameChapter, String url) {
        this.idTruyen = idTruyen;
        this.nameChapter = nameChapter;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTruyen() {
        return idTruyen;
    }

    public void setIdTruyen(int idTruyen) {
        this.idTruyen = idTruyen;
    }

    public String getNameChapter() {
        return nameChapter;
    }

    public void setNameChapter(String nameChapter) {
        this.nameChapter = nameChapter;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
