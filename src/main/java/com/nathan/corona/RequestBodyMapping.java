package com.nathan.corona;

public class RequestBodyMapping {
    private String response_url;

    public RequestBodyMapping(){};
    public RequestBodyMapping(String response_url) {
        this.response_url = response_url;
    }

    public String getResponse_url() {
        return response_url;
    }

    public void setResponse_url(String response_url) {
        this.response_url = response_url;
    }
}
