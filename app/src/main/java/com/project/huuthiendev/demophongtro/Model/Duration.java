package com.project.huuthiendev.demophongtro.Model;

/**
 * Created by HUUTHIENDEV on 10/9/2016.
 */

public class Duration {
    private String text;
    private int value;

    public Duration(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
