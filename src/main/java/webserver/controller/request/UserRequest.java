package webserver.controller.request;

import webserver.http.HttpRequest;

public class UserRequest {
    private String userId;
    private String name;
    private String password;
    private String email;

    public UserRequest() {
    }

    private UserRequest(String userId, String name, String password, String email) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public static UserRequest from(HttpRequest request) {
        return new UserRequest(request.getAttribute("name"),
                request.getAttribute("userId"),
                request.getAttribute("password"),
                request.getAttribute("email"));
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
