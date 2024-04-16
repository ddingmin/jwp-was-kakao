package webserver.http;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RequestLineTest {
    @Test
    void method() {
        RequestLine requestLine = new RequestLine("GET / HTTP/1.1");

        assertThat(requestLine.getMethod()).isEqualTo(HttpMethod.GET);
    }

    @Test
    void uri() {
        RequestLine requestLine = new RequestLine("GET /users HTTP/1.1");

        assertThat(requestLine.getUri()).isEqualTo("/users");
    }

    @Test
    void uri2() {
        RequestLine requestLine = new RequestLine("GET /users?name=mark HTTP/1.1");

        assertThat(requestLine.getUri()).isEqualTo("/users?name=mark");
    }

    @Test
    void path() {
        RequestLine requestLine = new RequestLine("GET /users HTTP/1.1");

        assertThat(requestLine.getPath()).isEqualTo("/users");
    }

    @Test
    void path2() {
        RequestLine requestLine = new RequestLine("GET /index.html HTTP/1.1");

        assertThat(requestLine.getPath()).isEqualTo("/index.html");
    }

    @Test
    void path3() {
        RequestLine requestLine = new RequestLine("GET /users?name=mark HTTP/1.1");

        assertThat(requestLine.getPath()).isEqualTo("/users");
    }

    @Test
    void attribute() {
        RequestLine requestLine = new RequestLine("GET /users?name=mark HTTP/1.1");

        assertThat(requestLine.getAttribute("name")).isEqualTo("mark");
    }

    @Test
    void attributes() {
        RequestLine requestLine = new RequestLine("GET /users?name=mark&password=1234 HTTP/1.1");

        assertAll(
                () -> assertThat(requestLine.getAttribute("name")).isEqualTo("mark"),
                () -> assertThat(requestLine.getAttribute("password")).isEqualTo("1234")
        );
    }

    @Test
    void extension() {
        RequestLine requestLine = new RequestLine("GET /index.html HTTP/1.1");

        assertThat(requestLine.getExtension().getValue()).isEqualTo(".html");
    }
}
