package webserver.http.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import webserver.http.Headers;
import webserver.http.HttpMethod;
import webserver.http.HttpProtocolVersion;
import webserver.http.RequestLine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HttpRequestParserTest {
    @ParameterizedTest
    @CsvSource(value = {"POST / HTTP/1.1, POST, /, /, HTTP/1.1",
            "GET /index.html HTTP/1.1, GET, /index.html, /index.html, HTTP/1.1",
            "GET /user/login?name=mark HTTP/1.1, GET, /user/login, /user/login?name=mark, HTTP/1.1"})
    @DisplayName("요청 시작 라인을 받아 파싱한다")
    void request_line(String line, String method, String path, String uri, String version) {
        RequestLine requestLine = HttpRequestParser.parseRequestLine(line);

        assertAll(
                () -> assertThat(requestLine.getMethod()).isEqualTo(HttpMethod.valueOf(method)),
                () -> assertThat(requestLine.getPath()).isEqualTo(path),
                () -> assertThat(requestLine.getUri()).isEqualTo(uri),
                () -> assertThat(requestLine.getVersion()).isEqualTo(HttpProtocolVersion.from(version))
        );
    }

    @Test
    @DisplayName("요청 시작 라인이 잘못되면, 에러를 던진다")
    void request_line_error() {
        String requestLine = "wrong line";
        assertThrows(IllegalArgumentException.class, () -> HttpRequestParser.parseRequestLine(requestLine));
    }

    @Test
    void headers() {
        Headers requestHeaders = HttpRequestParser.parseHeaders(
                "POST / HTTP/1.1\n" +
                        "Host: localhost:8000\n" +
                        "Accept: text/html\n" +
                        "Connection: keep-alive"
        );

        assertAll(
                () -> assertThat(requestHeaders.get("Host")).isEqualTo("localhost:8000"),
                () -> assertThat(requestHeaders.get("Accept")).isEqualTo("text/html"),
                () -> assertThat(requestHeaders.get("Connection")).isEqualTo("keep-alive")
        );
    }

    @Test
    void empty_header() {
        Headers requestHeaders = HttpRequestParser.parseHeaders("POST / HTTP/1.1");

        System.out.println(requestHeaders.toString());
    }
}
