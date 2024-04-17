package webserver.http;

import webserver.http.parser.KeyValueParser;

import java.util.HashMap;
import java.util.Map;

import static webserver.http.parser.KeyValueParser.KEY_VALUE_DELIMITER;
import static webserver.http.parser.KeyValueParser.QUERY_DELIMITER;

public class RequestLine {
    private final HttpMethod method;
    private final String uri;
    private final String path;
    private final HttpProtocolVersion version;
    private final Extension extension;
    private Map<String, String> attributes = new HashMap<>();

    public RequestLine(String method, String uri, String version) {
        this.method = HttpMethod.valueOf(method.toUpperCase());
        this.uri = uri;
        this.path = uri.split("\\?")[0];
        this.version = HttpProtocolVersion.from(version.toUpperCase());
        this.extension = initExtension();
        initAttributes();
    }

    private Extension initExtension() {
        if (hasExtension()) {
            return Extension.from(path.substring(path.lastIndexOf(".")));
        }
        return null;
    }

    private void initAttributes() {
        if (uri.split("\\?").length == 2) {
            String queryString = uri.split("\\?")[1];
            initAttribute(queryString);
        }
    }

    private void initAttribute(String queryString) {
        this.attributes = KeyValueParser.parse(queryString, QUERY_DELIMITER, KEY_VALUE_DELIMITER);
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getPath() {
        return path;
    }

    public HttpProtocolVersion getVersion() {
        return version;
    }

    public String getAttribute(String key) {
        return attributes.get(key);
    }

    public boolean hasExtension() {
        return path.contains(".");
    }

    public Extension getExtension() {
        return this.extension;
    }

    @Override
    public String toString() {
        return "StartLine{" +
                "method='" + method + '\'' +
                ", uri='" + uri + '\'' +
                ", path='" + path + '\'' +
                ", extension=" + extension +
                ", attributes=" + attributes +
                '}';
    }
}
