package com.example.newgankio.Model;

public class KnowladgeItem {
    private String kTitle;
    private String kAbout;

    public KnowladgeItem(String kTitle, String kAbout){
        this.kTitle = kTitle;
        this.kAbout = kAbout;
    }

    public String getkTitle() {
        return kTitle;
    }

    public String getkAbout() {
        return kAbout;
    }
}
