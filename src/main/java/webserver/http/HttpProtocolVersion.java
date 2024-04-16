package webserver.http;

public enum HttpProtocolVersion {
    HTTP1_1("HTTP/1.1"),
    HTTP2("HTTP/2");

    private final String value;

    HttpProtocolVersion(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
