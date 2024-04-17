package webserver.http.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static webserver.http.parser.KeyValueParser.KEY_VALUE_DELIMITER;
import static webserver.http.parser.KeyValueParser.QUERY_DELIMITER;

class KeyValueParserTest {
    @Test
    @DisplayName("키, 밸류로 이루어진 값을 파싱한다.")
    void key_value() {
        String input = "key=value";
        Map<String, String> parsed = KeyValueParser.parse(input, QUERY_DELIMITER, KEY_VALUE_DELIMITER);

        assertThat(parsed.get("key")).isEqualTo("value");
    }

    @Test
    @DisplayName("키, 밸류로 이루어진 값들을 파싱한다.")
    void key_values() {
        String input = "key=value&key2=value2";
        Map<String, String> parsed = KeyValueParser.parse(input, QUERY_DELIMITER, KEY_VALUE_DELIMITER);

        assertAll(
                () -> assertThat(parsed.get("key")).isEqualTo("value"),
                () -> assertThat(parsed.get("key2")).isEqualTo("value2")
        );
    }

    @Test
    @DisplayName("빈 경우에도 에러가 발생하지 않는다.")
    void empty() {
        String input = "";
        Map<String, String> parsed = KeyValueParser.parse(input, QUERY_DELIMITER, KEY_VALUE_DELIMITER);
    }

    @Test
    @DisplayName("key=v=v2의 경우 모든 value 값이 담긴다")
    void unsupported() {
        String input = "key=value=value2&key2=value2=value3=value4";
        Map<String, String> parsed = KeyValueParser.parse(input, QUERY_DELIMITER, KEY_VALUE_DELIMITER);

        assertAll(
                () -> assertThat(parsed.get("key")).isEqualTo("value=value2"),
                () -> assertThat(parsed.get("key2")).isEqualTo("value2=value3=value4")
        );
    }
}
