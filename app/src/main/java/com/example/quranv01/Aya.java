package com.example.quranv01;

public class Aya {
    public String text;
    public int ayaNumber;
    public int pageNumber;

    public Aya(String text, int number, int page) {
        this.text = text;
        this.ayaNumber = number;
        this.pageNumber = page;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumber() {
        return ayaNumber;
    }

    public void setNumber(int number) {
        this.ayaNumber = number;
    }

    public int getPage() {
        return pageNumber;
    }

    public void setPage(int page) {
        this.pageNumber = page;
    }
}
