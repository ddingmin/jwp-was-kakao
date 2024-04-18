package webserver.http;

import utils.Encoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpResponse {
    private HttpProtocolVersion version;
    private HttpStatus statusCode;
    private Map<String, String> headers = new HashMap<>();
    private byte[] body = new byte[0];

    public HttpResponse() {
    }

    private HttpResponse(HttpStatus statusCode, Map<String, String> headers, byte[] body) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }

    public static HttpResponse NOT_FOUND() {
        return new HttpResponse(HttpStatus.NOT_FOUND, new HashMap<>(), new byte[0]);
    }

    public String getStartLine() {
        return HttpProtocolVersion.HTTP1_1.getValue() + " " + statusCode.getValue() + " " + statusCode.getMessage();
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    // TODO: View 쪽으로 넘기기
    public List<String> getHeaders() {
        return headers.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue() + "\r\n")
                .collect(Collectors.toList());
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        headers.put("Content-Length", String.valueOf(body.length));
        this.body = body;
    }

    public void setBody(String body) {
        setBody(Encoder.toBytes(body));
    }
}
