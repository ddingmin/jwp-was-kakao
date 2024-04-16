package webserver.http.parser;

import webserver.http.RequestLine;

public class HttpRequestParser {
    public static RequestLine parseRequestLine(String line) {
        String firstLine = line.split("\n")[0];
        String[] parts = line.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("잘못된 request line 입니다. {" + firstLine + "}");
        }
        return new RequestLine(parts[0], parts[1], parts[2]);
    }
}
