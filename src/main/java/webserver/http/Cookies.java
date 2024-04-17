package webserver.http;

import webserver.http.parser.KeyValueParser;

import java.util.Map;

import static webserver.http.parser.KeyValueParser.COOKIE_DELIMITER;
import static webserver.http.parser.KeyValueParser.KEY_VALUE_DELIMITER;

public class Cookies {
    private final Map<String, String> cookies;
    private final String path;

    public Cookies(String cookies) {
        this.cookies = KeyValueParser.parse(cookies, COOKIE_DELIMITER, KEY_VALUE_DELIMITER);
        if (this.cookies.containsKey("Path")) {
            this.path = this.cookies.get("Path");
        } else {
            this.path = "/";
        }
    }

    public String get(String key) {
        return cookies.get(key);
    }

    public String getPath() {
        return path;
    }
}
