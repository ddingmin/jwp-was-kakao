package webserver.handler;

import webserver.controller.Controller;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;

public class HomeController implements Controller {

    public String home() {
        return "/index.html";
    }

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        if (request.getPath().equals("/")) {
            response.setStatusCode(HttpStatus.FOUND);
            response.addHeader("Location", home());
        }
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
    }
}
