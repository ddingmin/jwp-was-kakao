package webserver.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpRequestTest {
    @Test
    void request_resttemplate() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @ParameterizedTest
    @CsvSource(value = {"GET / HTTP/1.1, GET", "POST / HTTP/1.1, POST"})
    @DisplayName("http request startLine의 메소드를 저장한다.")
    void request_get_http_method(String requestRaw, String method) {
        HttpRequest request = new HttpRequest(requestRaw);

        assertThat(request.getMethod()).isEqualTo(HttpMethod.valueOf(method));
    }

    @ParameterizedTest
    @CsvSource(value = {"GET /index.html HTTP/1.1, /index.html", "GET / HTTP/1.1, /"})
    @DisplayName("http request startLine의 경로를 저장한다.")
    void request_path(String requestRaw, String path) {
        HttpRequest request = new HttpRequest(requestRaw);

        assertThat(request.getPath()).isEqualTo(path);
    }

    @ParameterizedTest
    @CsvSource(value = {"GET /index.html HTTP/1.1, true", "GET / HTTP/1.1, false", "GET /menu/favicon.ico HTTP/1.1, true"})
    @DisplayName("http request startLine의 경로의 확장자를 저장한다.")
    void has_extension(String requestRaw, boolean hasExtension) {
        HttpRequest request = new HttpRequest(requestRaw);

        assertThat(request.hasExtension()).isEqualTo(hasExtension);
    }

    @Test
    @DisplayName("http request body가 없는 경우 빈 body를 저장한다.")
    void no_body(){
        HttpRequest request = new HttpRequest("GET uri HTTP/1.1\nkey:value\n\n");

        assertThat(request.getBody()).isEqualTo("");
    }

    @Test
    @DisplayName("http request body를 저장한다.")
    void body(){
        HttpRequest request = new HttpRequest("GET uri HTTP/1.1\nkey: value\n\nbody");

        assertThat(request.getBody()).isEqualTo("body");
    }
}
