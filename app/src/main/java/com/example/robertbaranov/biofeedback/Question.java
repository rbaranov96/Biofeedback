package com.example.robertbaranov.biofeedback;

public class Question {
    private int mTextResId;
    public int getTextResId() { return mTextResId; }
    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }
    public Question(int TextResId) {
        mTextResId = TextResId;
    }
}
