package webserver.http;

public enum ResourceType {
    TEMPLATE("./templates"),
    STATIC("./static");

    private final String basicFilePath;

    ResourceType(String basicFilePath) {
        this.basicFilePath = basicFilePath;
    }

    public String getBasicFilePath() {
        return basicFilePath;
    }
}
