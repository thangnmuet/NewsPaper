package com.nip.newspaper.core.base;

/**
 * Created by nguyenminhthang on 5/28/15.
 */
public abstract class ContentBase {

    public static final int TYPE_ONLY_TEXT = 0;
    public static final int TYPE_WITH_IMAGE = 1;

    private String text;
    private String imagelink;
    private int type;

    public ContentBase() {
    }

    public ContentBase(String text, String imagelink, int type) {
        this.text = text;
        this.imagelink = imagelink;
        this.type = type;
    }

    @Override
    public String toString() {
        return "ContentBase{" +
                "text='" + text + '\'' +
                ", imagelink='" + imagelink + '\'' +
                ", type=" + type +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
