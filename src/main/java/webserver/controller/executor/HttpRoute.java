package webserver.controller.executor;

import webserver.http.HttpMethod;

public enum HttpRoute {
    RESOURCE(null, HttpMethod.GET),
    HOME("/", HttpMethod.GET),
    USER_REGISTER("/user/create", HttpMethod.POST),
    USER_LOGIN("/user/login", HttpMethod.POST);

    private final String path;
    private final HttpMethod method;

    HttpRoute(String path, HttpMethod method) {
        this.path = path;
        this.method = method;
    }

    public static HttpRoute from(String path, HttpMethod method, boolean hasExtension) {
        if (hasExtension) {
            return RESOURCE;
        }
        for (HttpRoute route : values()) {
            if (path.equals(route.getPath()) && method.equals(route.getMethod())) {
                return route;
            }
        }
        throw new IllegalArgumentException("올바르지 않은 요청입니다.");
    }

    public String getPath() {
        return path;
    }

    public HttpMethod getMethod() {
        return method;
    }
}
