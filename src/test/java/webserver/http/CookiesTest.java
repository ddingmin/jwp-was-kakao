package webserver.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CookiesTest {
    @Test
    void cookie_key_value() {
        Cookies cookies = new Cookies("key=value; key2=value2");

        assertEquals("value", cookies.get("key"));
        assertEquals("value2", cookies.get("key2"));
    }
}
