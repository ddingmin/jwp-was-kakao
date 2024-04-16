package webserver.http;

public class HttpRequest {
    private final RequestLine requestLine;
    private final Headers headers;
    private String body;

    public HttpRequest(RequestLine requestLine, Headers headers, String body) {
        this.requestLine = requestLine;
        this.headers = headers;
        this.body = body;
    }

    public HttpMethod getMethod() {
        return requestLine.getMethod();
    }

    public String getPath() {
        return requestLine.getPath();
    }

    public Extension getExtension() {
        return requestLine.getExtension();
    }

    public boolean hasExtension() {
        return requestLine.hasExtension();
    }

    public String getBody() {
        return body;
    }


    public String getAttribute(String key) {
        return headers.get(key);
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "startLine=" + requestLine +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}
