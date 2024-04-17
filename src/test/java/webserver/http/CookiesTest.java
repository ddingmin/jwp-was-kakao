package webserver.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CookiesTest {
    @Test
    @DisplayName("쿠키를 가져온다.")
    void cookie_key_value() {
        Cookies cookies = new Cookies("key=value; key2=value2");

        assertEquals("value", cookies.get("key"));
        assertEquals("value2", cookies.get("key2"));
    }

    @ParameterizedTest
    @CsvSource(value = {"key=value; Path=/, /", "key=value; Path=/path, /path", "key=value; jsessionid=1234; Path=/path, /path"})
    @DisplayName("Path를 가져온다.")
    void cookie_path(String cookieLine, String path) {
        Cookies cookies = new Cookies(cookieLine);

        assertEquals(path, cookies.getPath());
    }

    @ParameterizedTest
    @CsvSource(value = {"key=value;, /", "key=value; jsessionid=1234;, /"})
    @DisplayName("Path가 없는 경우 기본값인 /로 설정한다.")
    void cookie_empty_path(String cookieLine, String path) {
        Cookies cookies = new Cookies(cookieLine);

        assertEquals(path, cookies.getPath());
    }
}
