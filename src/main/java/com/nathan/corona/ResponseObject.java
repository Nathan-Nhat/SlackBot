package com.nathan.corona;

public class ResponseObject {
    private String countryEnglishName;
    private long confirmedCount;
    private long deadCount;
    private long curedCount;

    public ResponseObject(){};
    public ResponseObject(String countryEnglishName, long confirmedCount, long deadCount, long curedCount) {
        this.countryEnglishName = countryEnglishName;
        this.confirmedCount = confirmedCount;
        this.deadCount = deadCount;
        this.curedCount = curedCount;
    }

    public String getCountryEnglishName() {
        return countryEnglishName;
    }

    public long getConfirmedCount() {
        return confirmedCount;
    }

    public long getDeadCount() {
        return deadCount;
    }

    public long getCuredCount() {
        return curedCount;
    }

    public void setCountryEnglishName(String countryEnglishName) {
        this.countryEnglishName = countryEnglishName;
    }

    public void setConfirmedCount(long confirmedCount) {
        this.confirmedCount = confirmedCount;
    }

    public void setDeadCount(long deadCount) {
        this.deadCount = deadCount;
    }

    public void setCuredCount(long curedCount) {
        this.curedCount = curedCount;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "countryEnglishName='" + countryEnglishName + '\'' +
                ", confirmedCount=" + confirmedCount +
                ", deadCount=" + deadCount +
                ", curedCount=" + curedCount +
                '}';
    }

}
