package webserver.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ExtensionTest {
    @ParameterizedTest
    @ValueSource(strings = {".ttf", ".css", ".html"})
    @DisplayName("지정된 확장자를 가져온다.")
    void from(String extension) {
        assertThat(Extension.from(extension)).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {".ttff", ".ccss", ".kakao"})
    @DisplayName("지정된 확장자가 없다면 null 값을 가져온다.")
    void from_null(String extension) {
        assertThat(Extension.from(extension)).isNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {".TTF", ".CSS", ".SVG"})
    @DisplayName("대문자의 경우에도 지정된 확장자를 가져온다.")
    void from_cap(String extension) {
        assertThat(Extension.from(extension)).isNotNull();
    }
}
