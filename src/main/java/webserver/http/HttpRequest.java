package webserver.http;

import java.util.ArrayList;
import java.util.List;

public class HttpRequest {
    private final RequestLine requestLine;
    private final Headers headers;
    private String body;

    public HttpRequest(String request) {
        String[] split = request.split("\n");
        this.requestLine = new RequestLine(split[0]);

        boolean isHeaders = true;
        List<String> headerList = new ArrayList<>();

        StringBuilder body = new StringBuilder();
        for (int i = 1; i < split.length; i++) {
            if (split[i].isBlank()) {
                isHeaders = false;
                continue;
            }
            if (isHeaders) {
                headerList.add(split[i]);
                continue;
            }
            body.append(split[i]);
        }

        this.headers = new Headers(headerList);
        this.body = body.toString();
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

    public void setBody(String body) {
        this.body = body;
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
