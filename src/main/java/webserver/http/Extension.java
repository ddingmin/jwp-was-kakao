package webserver.http;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Extension {
    CSS(".css", MIME.CSS, ResourceType.STATIC),
    EOT(".eot", MIME.EOT, ResourceType.STATIC),
    HTML(".html", MIME.HTML, ResourceType.TEMPLATE),
    ICO(".ico", MIME.ICON, ResourceType.TEMPLATE),
    JPEG(".jpeg", MIME.JPEG, ResourceType.STATIC),
    JPG(".jpg", MIME.JPEG, ResourceType.STATIC),
    JS(".js", MIME.JAVASCRIPT, ResourceType.STATIC),
    PNG(".png", MIME.PNG, ResourceType.STATIC),
    SVG(".svg", MIME.SVG, ResourceType.STATIC),
    TTF(".ttf", MIME.TTF, ResourceType.STATIC),
    WOFF(".woff", MIME.WOFF, ResourceType.STATIC),
    WOFF2(".woff2", MIME.WOFF2, ResourceType.STATIC);

    private static final Map<String, Extension> valueMap = Arrays.stream(values()).collect(Collectors.toMap(
            it -> it.getValue(),
            it -> it
    ));
    private final String value;
    private final MIME mime;
    private final ResourceType resourceType;

    Extension(String value, MIME mime, ResourceType resourceType) {
        this.value = value;
        this.mime = mime;
        this.resourceType = resourceType;
    }

    public static Extension from(String extension) {
        try {
            return valueMap.get(extension.toLowerCase());
        } catch (Exception e) {
            return null;
        }
    }

    public String getValue() {
        return value;
    }

    public MIME getMime() {
        return mime;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public String getBasicFilePath() {
        return resourceType.getBasicFilePath();
    }

    public String getContentType() {
        return mime.getContentType();
    }
}
