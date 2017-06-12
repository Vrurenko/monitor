package model;

public class Question {

    private long id;
    private String URL;
    private int cooldown;
    private int responseTimeOK;
    private int responseTimeWARNING;
    private int expectedResponseCode;
    private int minResponseLength;
    private int maxResponseLength;
    private String substring;

    public Question() {
    }

    public Question(long id, String URL, int cooldown, int responseTimeOK, int responseTimeWARNING, int expectedResponseCode, int minResponseLength, int maxResponseLength, String substring) {
        this.id = id;
        this.URL = URL;
        this.cooldown = cooldown;
        this.responseTimeOK = responseTimeOK;
        this.responseTimeWARNING = responseTimeWARNING;
        this.expectedResponseCode = expectedResponseCode;
        this.minResponseLength = minResponseLength;
        this.maxResponseLength = maxResponseLength;
        this.substring = substring;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getResponseTimeOK() {
        return responseTimeOK;
    }

    public void setResponseTimeOK(int responseTimeOK) {
        this.responseTimeOK = responseTimeOK;
    }

    public int getResponseTimeWARNING() {
        return responseTimeWARNING;
    }

    public void setResponseTimeWARNING(int responseTimeWARNING) {
        this.responseTimeWARNING = responseTimeWARNING;
    }

    public int getExpectedResponseCode() {
        return expectedResponseCode;
    }

    public void setExpectedResponseCode(int expectedResponseCode) {
        this.expectedResponseCode = expectedResponseCode;
    }

    public int getMinResponseLength() {
        return minResponseLength;
    }

    public void setMinResponseLength(int minResponseLength) {
        this.minResponseLength = minResponseLength;
    }

    public int getMaxResponseLength() {
        return maxResponseLength;
    }

    public void setMaxResponseLength(int maxResponseLength) {
        this.maxResponseLength = maxResponseLength;
    }

    public String getSubstring() {
        return substring;
    }

    public void setSubstring(String substring) {
        this.substring = substring;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (getId() != question.getId()) return false;
        if (getCooldown() != question.getCooldown()) return false;
        if (getResponseTimeOK() != question.getResponseTimeOK()) return false;
        if (getResponseTimeWARNING() != question.getResponseTimeWARNING()) return false;
        if (getExpectedResponseCode() != question.getExpectedResponseCode()) return false;
        if (getMinResponseLength() != question.getMinResponseLength()) return false;
        if (getMaxResponseLength() != question.getMaxResponseLength()) return false;
        if (!getURL().equals(question.getURL())) return false;
        return getSubstring().equals(question.getSubstring());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getURL().hashCode();
        result = 31 * result + getCooldown();
        result = 31 * result + getResponseTimeOK();
        result = 31 * result + getResponseTimeWARNING();
        result = 31 * result + getExpectedResponseCode();
        result = 31 * result + getMinResponseLength();
        result = 31 * result + getMaxResponseLength();
        result = 31 * result + getSubstring().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", URL='" + URL + '\'' +
                ", cooldown=" + cooldown +
                ", responseTimeOK=" + responseTimeOK +
                ", responseTimeWARNING=" + responseTimeWARNING +
                ", expectedResponseCode=" + expectedResponseCode +
                ", minResponseLength=" + minResponseLength +
                ", maxResponseLength=" + maxResponseLength +
                ", substring='" + substring + '\'' +
                '}';
    }
}
