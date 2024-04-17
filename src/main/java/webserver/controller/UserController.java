package webserver.controller;

import webserver.controller.request.UserRequest;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;
import webserver.http.parser.KeyValueParser;
import webserver.service.UserService;

import java.util.Map;

import static webserver.http.parser.KeyValueParser.KEY_VALUE_DELIMITER;
import static webserver.http.parser.KeyValueParser.QUERY_DELIMITER;

public class UserController implements Controller {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public String register(UserRequest request) {
        userService.register(request);
        return "/index.html";
    }

    public String login(UserRequest request) {
        if (userService.login(request)) {
            return "/index.html";
        }
        return "/user/login_failed.html";
    }

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        if (request.getPath().equals("/user/create")) {
            Map<String, String> userParameters = KeyValueParser.parse(request.getBody(), QUERY_DELIMITER, KEY_VALUE_DELIMITER);
            UserRequest userRequest = new UserRequest(userParameters.get("userId"),
                    userParameters.get("name"),
                    userParameters.get("password"),
                    userParameters.get("email"));

            String target = register(userRequest);
            response.setStatusCode(HttpStatus.FOUND);
            response.addHeader("Location", target);
            return;
        }
        if (request.getPath().equals("/user/login")) {
            Map<String, String> userParameters = KeyValueParser.parse(request.getBody(), KEY_VALUE_DELIMITER, QUERY_DELIMITER);
            UserRequest userRequest = new UserRequest();
            userRequest.setUserId(userParameters.get("userId"));
            userRequest.setPassword(userParameters.get("password"));

            String target = login(userRequest);
            response.setStatusCode(HttpStatus.FOUND);
            response.addHeader("Location", target);
        }
    }
}
