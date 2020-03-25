package com.nathan.corona;

import java.util.List;

public class ResponseSlack {
    private String channel;
    private String response_type;
    private List<InsideBlock> blocks;

    public ResponseSlack(){};
    public ResponseSlack(String channel, List<InsideBlock> blocks) {
        this.channel = channel;
        this.blocks = blocks;
    }

    public String getChannel() {
        return channel;
    }

    public List<InsideBlock> getBlocks() {
        return blocks;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setBlocks(List<InsideBlock> blocks) {
        this.blocks = blocks;
    }

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }
}
