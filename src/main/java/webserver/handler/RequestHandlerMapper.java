package webserver.handler;

import webserver.http.HttpRequest;

public class RequestHandlerMapper {
    public static Handler mapping(HttpRequest request) {
        if (request.hasExtension()) {
            return new ResourceHandler();
        }
        return new ServiceHandler();
    }
}
