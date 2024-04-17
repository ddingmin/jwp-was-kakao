package webserver.http.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyValueParser {

    public static final String KEY_VALUE_DELIMITER = "=";
    public static final String COOKIE_DELIMITER = ";";
    public static final String QUERY_DELIMITER = "&";

    public static Map<String, String> parse(String input, String delimiter, String keyValueDelimiter) {
        try {
            return Arrays.stream(input.split(delimiter))
                    .map(it -> it.split(keyValueDelimiter, 2))
                    .collect(Collectors.toMap(
                            it -> it[0].trim(),
                            it -> it[1].trim()
                    ));
        } catch (Exception e) {
            return new HashMap<>();
        }
    }
}
