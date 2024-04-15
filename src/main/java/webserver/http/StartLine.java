package webserver.http;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StartLine {
    private final HttpMethod method;
    private final String uri;
    private final String path;
    private final Extension extension;
    private Map<String, String> attributes = new HashMap<>();

    public StartLine(String line) {
        String[] split = line.split(" ");
        this.method = HttpMethod.valueOf(split[0].toUpperCase());
        this.uri = split[1];
        this.path = uri.split("\\?")[0];
        this.extension = initExtension();
        if (uri.split("\\?").length == 2) {
            String queryString = uri.split("\\?")[1];
            initAttributes(queryString);
        }
    }

    private Extension initExtension() {
        if (hasExtension()) {
            return Extension.from(path.substring(path.lastIndexOf(".")));
        }
        return null;
    }

    private void initAttributes(String queryString) {
        this.attributes = Arrays.stream(queryString.split("&"))
                .map(it -> it.split("="))
                .collect(Collectors.toMap(
                        it -> it[0],
                        it -> it[1]
                ));
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
