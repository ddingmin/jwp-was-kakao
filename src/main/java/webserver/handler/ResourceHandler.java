package webserver.handler;

import utils.FileIoUtils;
import webserver.http.Extension;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;

public class ResourceHandler implements Handler {
    @Override
    public void handle(HttpRequest httpRequest, HttpResponse httpResponse) {
        try {
            Extension extension = httpRequest.getExtension();
            httpResponse.setStatusCode(HttpStatus.OK);
            httpResponse.addHeader("Content-Type", extension.getContentType());
            httpResponse.setBody(FileIoUtils.loadFileFromClasspath(extension.getBasicFilePath() + httpRequest.getPath()));
        } catch (Exception e) {
            httpResponse.setStatusCode(HttpStatus.NOT_FOUND);
        }
    }
}
