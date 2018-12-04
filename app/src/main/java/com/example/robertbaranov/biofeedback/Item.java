package com.example.robertbaranov.biofeedback;

public class Item {
    private String mTitle;
    private String mDate;
    private int mImageResource;
    public Item() {
        mTitle = "";
        mDate = "";
        mImageResource = 0;
    }
    public void setTitle(String title) {
        mTitle = title;
    }
    public void setDate(String date) {
        mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }
    public String getDate() {
        return mDate;
    }
    public void setImageResource(int imageResource) {
        mImageResource = imageResource;
    }
    public int getImageResource() {
        return mImageResource;
    }

}

