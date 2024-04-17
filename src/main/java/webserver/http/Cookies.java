package webserver.http;

import webserver.http.parser.KeyValueParser;

import java.util.Map;

import static webserver.http.parser.KeyValueParser.COOKIE_DELIMITER;
import static webserver.http.parser.KeyValueParser.KEY_VALUE_DELIMITER;

public class Cookies {
    private final Map<String, String> cookies;

    public Cookies(String cookies) {
        this.cookies = KeyValueParser.parse(cookies, COOKIE_DELIMITER, KEY_VALUE_DELIMITER);
    }

    public String get(String key) {
        return cookies.get(key);
    }
}
