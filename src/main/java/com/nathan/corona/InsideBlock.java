package com.nathan.corona;

public class InsideBlock {
    private String type;
    private InsideText text;
    public InsideBlock(){};
    public InsideBlock(String type, InsideText text) {
        this.type = type;
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public InsideText getText() {
        return text;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setText(InsideText text) {
        this.text = text;
    }
}
