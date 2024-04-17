package webserver.http;

public enum MIME {
    HTML("text/html", "text"),
    CSS("text/css", "text"),
    JAVASCRIPT("text/javascript", "text"),
    EOT("application/vnd.ms-fontobject", "application"),
    ICON("image/vnd.microsoft.icon", "image"),
    JPEG("image/jpeg", "image"),
    SVG("image/svg+xml", "image"),
    PNG("image/png", "image"),
    TTF("font/ttf", "font"),
    WOFF("font/woff", "font"),
    WOFF2("font/woff2", "font");

    private final String contentType;
    private final String type;

    MIME(String contentType, String type) {
        this.contentType = contentType;
        this.type = type;
    }

    public String getContentType() {
        return contentType;
    }

    public String getType() {
        return type;
    }
}
