package com.gmail.gerlei.szilard.cards.view.widget;

@SuppressWarnings("unused")
class CardImage {

    private int resId;
    private String name;

    CardImage(int resId, String name) {
        this.resId = resId;
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
