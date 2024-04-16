package webserver.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Headers {
    private final Map<String, String> headers = new HashMap<>();

    public Headers(List<String> headers) {
        this.headers.put("Content-Length", "0");
        headers.stream()
                .map(it -> it.split(":", 2))
                .forEach(it -> this.headers.put(it[0], it[1].trim()));
    }

    public String get(String key) {
        return headers.get(key);
    }

    @Override
    public String toString() {
        return "Headers{" +
                "headers=" + headers +
                '}';
    }
}
