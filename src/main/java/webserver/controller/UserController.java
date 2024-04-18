package webserver.controller;

import webserver.controller.request.UserRequest;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;
import webserver.service.UserService;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public HttpResponse register(HttpRequest request) {
        UserRequest userRequest = UserRequest.from(request);
        userService.register(userRequest);

        HttpResponse response = new HttpResponse();
        response.addHeader("Location", "/index.html");
        response.setStatusCode(HttpStatus.FOUND);
        return response;
    }

    public HttpResponse login(HttpRequest request) {
        UserRequest userRequest = UserRequest.from(request);

        HttpResponse response = new HttpResponse();
        response.addHeader("Location", "/user/login_failed.html");
        if (userService.login(userRequest)) {
            response.addHeader("Location", "/index.html");
            return response;
        }
        response.setStatusCode(HttpStatus.FOUND);
        return response;
    }
}
