package utils;

import java.nio.charset.StandardCharsets;

public class Encoder {
    public static byte[] toBytes(String string) {
        return string.getBytes(StandardCharsets.UTF_8);
    }
}
