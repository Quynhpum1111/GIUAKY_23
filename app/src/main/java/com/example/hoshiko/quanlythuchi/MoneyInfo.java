package com.example.hoshiko.quanlythuchi;

/**
 * Created by DUNG on 4/2/2018.
 */

public class MoneyInfo {
    private int mID;
    private String mNoidung;
    private String mSotien;
    private int mHinhthuc;

    public MoneyInfo(){};

    public MoneyInfo(int ID, String Noidung, String Sotien, int Hinhthuc){
        mID = ID;
        mNoidung = Noidung;
        mSotien = Sotien;
        mHinhthuc = Hinhthuc;

    }

    public MoneyInfo(String Noidung, String Sotien, int Hinhthuc){
        mNoidung = Noidung;
        mSotien = Sotien;
        mHinhthuc = Hinhthuc;

    }

    public int getID () {return mID; };
    public String getNoidung () {return mNoidung; };
    public String getSotien () {return mSotien; };
    public int getHinhthuc () { return mHinhthuc; };


    public void setID(int ID) {this.mID = ID; };
    public void setNoidung(String Noidung) {this.mNoidung = Noidung; };
    public void setSotien (String Sotien) {this.mSotien = Sotien; };
    public void setHinhthuc (int Hinhthuc) {this.mHinhthuc = Hinhthuc; };


}
