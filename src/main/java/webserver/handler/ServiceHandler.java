package webserver.handler;

import webserver.controller.Controller;
import webserver.controller.UserController;
import webserver.http.HttpMethod;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;
import webserver.service.UserService;

public class ServiceHandler implements Handler {

    @Override
    public void handling(HttpRequest httpRequest, HttpResponse httpResponse) {
        if (httpRequest.getPath().startsWith("/user")) {
            Controller controller = new UserController(new UserService());
            executor(controller, httpRequest, httpResponse);
        } else if (httpRequest.getPath().startsWith("/")) {
            Controller controller = new HomeController();
            executor(controller, httpRequest, httpResponse);
        } else {
            httpResponse.setStatusCode(HttpStatus.NOT_FOUND);
        }
        if (httpResponse.getStatusCode() == null) {
            httpResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void executor(Controller controller, HttpRequest httpRequest, HttpResponse httpResponse) {
        if (httpRequest.getMethod().equals(HttpMethod.GET)) {
            controller.doGet(httpRequest, httpResponse);
        }
        if (httpRequest.getMethod().equals(HttpMethod.POST)) {
            controller.doPost(httpRequest, httpResponse);
        }
    }
}
