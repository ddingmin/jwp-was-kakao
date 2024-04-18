package webserver.controller;

import webserver.http.HttpResponse;
import webserver.http.HttpStatus;

public class HomeController {

    public HttpResponse home() {
        HttpResponse response = new HttpResponse();
        response.setStatusCode(HttpStatus.FOUND);
        response.addHeader("Location", "/index.html");
        return response;
    }
}
