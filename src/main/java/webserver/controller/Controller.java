package webserver.controller;

import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

public interface Controller {
    void doGet(HttpRequest request, HttpResponse response);

    void doPost(HttpRequest request, HttpResponse response);
}
