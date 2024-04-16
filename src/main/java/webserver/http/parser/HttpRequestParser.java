package webserver.http.parser;

import webserver.http.Headers;
import webserver.http.RequestLine;

import java.util.Arrays;
import java.util.stream.Collectors;

public class HttpRequestParser {

    public static final String LINE_BREAK = "\n";
    public static final String SPACE = " ";

    public static RequestLine parseRequestLine(String lines) {
        String firstLine = lines.split(LINE_BREAK)[0];
        String[] parts = firstLine.split(SPACE);

        if (parts.length != 3) {
            throw new IllegalArgumentException("잘못된 request line 입니다. {" + firstLine + "}");
        }
        return new RequestLine(parts[0], parts[1], parts[2]);
    }

    public static Headers parseHeaders(String lines) {
        String[] parts = lines.split(LINE_BREAK);

        return new Headers(Arrays.stream(parts, 1, parts.length)
                .collect(Collectors.toList()));
    }
}
