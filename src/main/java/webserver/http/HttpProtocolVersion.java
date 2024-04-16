package webserver.http;

import java.util.Arrays;

public enum HttpProtocolVersion {
    HTTP1_1("HTTP/1.1"),
    HTTP2("HTTP/2");

    private final String value;

    HttpProtocolVersion(String value) {
        this.value = value;
    }

    public static HttpProtocolVersion from(String value) {
        return Arrays.stream(HttpProtocolVersion.values())
                .filter(it -> it.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 버전이 존재하지 않습니다. {" + value + "}"));
    }

    public String getValue() {
        return value;
    }
}
