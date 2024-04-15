package webserver.http;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StartLineTest {
    @Test
    void method() {
        StartLine startLine = new StartLine("GET / HTTP/1.1");

        assertThat(startLine.getMethod()).isEqualTo(HttpMethod.GET);
    }

    @Test
    void uri() {
        StartLine startLine = new StartLine("GET /users HTTP/1.1");

        assertThat(startLine.getUri()).isEqualTo("/users");
    }

    @Test
    void uri2() {
        StartLine startLine = new StartLine("GET /users?name=mark HTTP/1.1");

        assertThat(startLine.getUri()).isEqualTo("/users?name=mark");
    }

    @Test
    void path() {
        StartLine startLine = new StartLine("GET /users HTTP/1.1");

        assertThat(startLine.getPath()).isEqualTo("/users");
    }

    @Test
    void path2() {
        StartLine startLine = new StartLine("GET /index.html HTTP/1.1");

        assertThat(startLine.getPath()).isEqualTo("/index.html");
    }

    @Test
    void path3() {
        StartLine startLine = new StartLine("GET /users?name=mark HTTP/1.1");

        assertThat(startLine.getPath()).isEqualTo("/users");
    }

    @Test
    void attribute() {
        StartLine startLine = new StartLine("GET /users?name=mark HTTP/1.1");

        assertThat(startLine.getAttribute("name")).isEqualTo("mark");
    }

    @Test
    void attributes() {
        StartLine startLine = new StartLine("GET /users?name=mark&password=1234 HTTP/1.1");

        assertAll(
                () -> assertThat(startLine.getAttribute("name")).isEqualTo("mark"),
                () -> assertThat(startLine.getAttribute("password")).isEqualTo("1234")
        );
    }

    @Test
    void extension() {
        StartLine startLine = new StartLine("GET /index.html HTTP/1.1");

        assertThat(startLine.getExtension().getValue()).isEqualTo(".html");
    }
}
