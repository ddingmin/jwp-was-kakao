package webserver.session;

import java.util.UUID;

public class SessionIdGenerator {
    public static String generate() {
        return String.valueOf(UUID.randomUUID());
    }
}
