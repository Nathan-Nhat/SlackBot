package com.nathan.corona;

import java.util.List;

public class ResponseSlack {
    private String channel;
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
}
