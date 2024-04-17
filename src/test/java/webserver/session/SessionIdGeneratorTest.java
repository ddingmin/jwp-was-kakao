package webserver.session;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SessionIdGeneratorTest {
    @Test
    void generate() {
        String sessionId = SessionIdGenerator.generate();

        assertNotNull(sessionId);
        assertEquals(36, sessionId.length());
    }
}
