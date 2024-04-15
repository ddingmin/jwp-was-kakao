package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

class RequestReaderUtilsTest {
    @ParameterizedTest
    @ValueSource(strings = {"GET", "POST", "Hello,\nWorld"})
    @DisplayName("request header를 읽는다.")
    void readHeader(String string) throws IOException {
        String requestHeader = RequestReaderUtils.readHeader(toReader(string));

        assertThat(requestHeader).isEqualTo(string);
    }

    @ParameterizedTest
    @ValueSource(strings = {"I am Body", ""})
    @DisplayName("request body를 읽는다.")
    void readBody(String string) throws IOException {
        String requestBody = RequestReaderUtils.readBody(toReader(string), string.length());

        assertThat(requestBody).isEqualTo(string);
    }

    private BufferedReader toReader(String string) {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(string.getBytes())));
    }
}
