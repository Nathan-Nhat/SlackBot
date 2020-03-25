package com.nathan.corona;

public class ResponsePleaseWait {
    private String response_type;
    private String type;
    private String text;
    public ResponsePleaseWait(){};
    public ResponsePleaseWait(String response_type, String type, String text) {
        this.response_type = response_type;
        this.type = type;
        this.text = text;
    }

    public String getResponse_type() {
        return response_type;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setText(String text) {
        this.text = text;
    }
}
