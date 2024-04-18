package webserver.controller;

import utils.FileIoUtils;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;

public class ResourceController {
    public HttpResponse getResourcePath(HttpRequest request) {
        try {
            HttpResponse response = new HttpResponse();

            response.addHeader("Content-Type", request.getExtension().getMime().getContentType());
            String path = request.getExtension().getBasicFilePath() + request.getPath();
            response.setBody(FileIoUtils.loadFileFromClasspath(path));
            response.setStatusCode(HttpStatus.OK);
            return response;
        } catch (Exception e) {
            return HttpResponse.NOT_FOUND();
        }
    }
}
