package webserver.session;

import java.util.HashMap;
import java.util.Map;

public class SessionDB {
    private static final Map<String, String> sessions = new HashMap<>();

    public static String findBySessionId(String sessionId) {
        return sessions.get(sessionId);
    }

    public static void add(String sessionId, String userId) {
        sessions.put(sessionId, userId);
    }

    public static void removeSession(String sessionId) {
        sessions.remove(sessionId);
    }
}
