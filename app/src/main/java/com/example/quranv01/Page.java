package com.example.quranv01;

import java.util.ArrayList;

public class Page {
    public ArrayList<Aya> ayas;
    public int pageNumber;

    public Page(ArrayList<Aya> ayas, int pageNumber) {
        this.ayas = ayas;
        this.pageNumber = pageNumber;
    }

    public ArrayList<Aya> getAyas() {
        return ayas;
    }

    public void setAyas(ArrayList<Aya> ayas) {
        this.ayas = ayas;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
