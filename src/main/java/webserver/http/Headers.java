package webserver.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Headers {
    public static final String CONTENT_LENGTH = "Content-Length";
    private final Map<String, String> headers = new HashMap<>();
    private final Cookies cookies;

    public Headers(List<String> headers) {
        headers.stream()
                .map(it -> it.split(":", 2))
                .forEach(it -> this.headers.put(it[0], it[1].trim()));
        this.cookies = new Cookies(headers.stream()
                .map(it -> it.split(":", 2))
                .filter(it -> it[0].equals("Cookie"))
                .map(it -> it[1])
                .findFirst()
                .orElse(null));
    }

    public String get(String key) {
        return headers.get(key);
    }

    public int getContentLength() {
        if (headers.containsKey(CONTENT_LENGTH)) {
            return Integer.parseInt(headers.get(CONTENT_LENGTH));
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Headers{" +
                "headers=" + headers +
                '}';
    }
}
