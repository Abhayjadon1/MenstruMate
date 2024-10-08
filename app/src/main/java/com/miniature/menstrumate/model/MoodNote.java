package com.miniature.menstrumate.model;

public class MoodNote {
    private int id;
    private String value;

    public MoodNote(int id2, String value2) {
        this.id = id2;
        this.value = value2;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id2) {
        this.id = id2;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return "Settings [id=" + this.id + ", value=" + this.value + "]";
    }
}
