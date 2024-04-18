package webserver.controller.executor;

import webserver.controller.HomeController;
import webserver.controller.ResourceController;
import webserver.controller.UserController;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.service.UserService;

import java.util.Map;
import java.util.function.Function;

public class ControllerExecutor {

    private static final Map<HttpRoute, Function<HttpRequest, HttpResponse>> ROUTE_FUNCTION_MAP = Map.of(
            HttpRoute.RESOURCE, request -> new ResourceController().getResourcePath(request),
            HttpRoute.HOME, request -> new HomeController().home(),
            HttpRoute.USER_REGISTER, request -> new UserController(new UserService()).register(request),
            HttpRoute.USER_LOGIN, request -> new UserController(new UserService()).login(request)
    );

    public static HttpResponse execute(HttpRequest httpRequest) {
        HttpRoute httpRoute = HttpRoute.from(httpRequest.getPath(), httpRequest.getMethod(), httpRequest.hasExtension());
        return ROUTE_FUNCTION_MAP.get(httpRoute).apply(httpRequest);
    }
}
