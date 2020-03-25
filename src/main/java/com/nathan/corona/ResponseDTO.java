package com.nathan.corona;

public class ResponseDTO {
    private String country;
    private long totalCase;
    private long totalDeath;
    private long totalRecover;

    public ResponseDTO(String country, long totalCase, long totalDeath, long totalRecover) {
        this.country = country;
        this.totalCase = totalCase;
        this.totalDeath = totalDeath;
        this.totalRecover = totalRecover;
    }

    public String getCountry() {
        return country;
    }

    public long getTotalCase() {
        return totalCase;
    }

    public long getTotalDeath() {
        return totalDeath;
    }

    public long getTotalRecover() {
        return totalRecover;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTotalCase(long totalCase) {
        this.totalCase = totalCase;
    }

    public void setTotalDeath(long totalDeath) {
        this.totalDeath = totalDeath;
    }

    public void setTotalRecover(long totalRecover) {
        this.totalRecover = totalRecover;
    }
}
