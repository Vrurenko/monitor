package model;

public class Answer extends Question {
    private String URL;
    private Status responseTime;
    private Status responseCode;
    private Status responseLength;
    private Status substringEntry;

    public Answer() {
    }

    public Answer(long id, String URL, int cooldown, int responseTimeOK, int responseTimeWARNING, int expectedResponseCode, int minResponseLength, int maxResponseLength, String substring, String URL1, Status responseTime, Status responseCode, Status responseLength, Status substringEntry) {
        super(id, URL, cooldown, responseTimeOK, responseTimeWARNING, expectedResponseCode, minResponseLength, maxResponseLength, substring);
        this.URL = URL1;
        this.responseTime = responseTime;
        this.responseCode = responseCode;
        this.responseLength = responseLength;
        this.substringEntry = substringEntry;
    }

    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public void setURL(String URL) {
        this.URL = URL;
    }

    public Status getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Status responseTime) {
        this.responseTime = responseTime;
    }

    public Status getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Status responseCode) {
        this.responseCode = responseCode;
    }

    public Status getResponseLength() {
        return responseLength;
    }

    public void setResponseLength(Status responseLength) {
        this.responseLength = responseLength;
    }

    public Status getSubstringEntry() {
        return substringEntry;
    }

    public void setSubstringEntry(Status substringEntry) {
        this.substringEntry = substringEntry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Answer answer = (Answer) o;

        if (!getURL().equals(answer.getURL())) return false;
        if (getResponseTime() != answer.getResponseTime()) return false;
        if (getResponseCode() != answer.getResponseCode()) return false;
        if (getResponseLength() != answer.getResponseLength()) return false;
        return getSubstringEntry() == answer.getSubstringEntry();

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getURL().hashCode();
        result = 31 * result + getResponseTime().hashCode();
        result = 31 * result + getResponseCode().hashCode();
        result = 31 * result + getResponseLength().hashCode();
        result = 31 * result + getSubstringEntry().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "URL='" + URL + '\'' +
                ", responseTime=" + responseTime +
                ", responseCode=" + responseCode +
                ", responseLength=" + responseLength +
                ", substringEntry=" + substringEntry +
                '}';
    }
}
