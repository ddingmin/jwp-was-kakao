package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.RequestReaderUtils;
import webserver.handler.Handler;
import webserver.handler.RequestHandlerMapper;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private final Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String requestHeader = RequestReaderUtils.readHeader(reader);
            logger.debug(requestHeader);

            HttpRequest httpRequest = new HttpRequest(requestHeader);
            httpRequest.setBody(RequestReaderUtils.readBody(reader, Integer.parseInt(httpRequest.getAttribute("Content-Length"))));
            logger.debug(httpRequest.getBody());

            HttpResponse httpResponse = new HttpResponse();
            Handler handler = RequestHandlerMapper.mapping(httpRequest);
            handler.handle(httpRequest, httpResponse);

            response(httpResponse);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private void response(HttpResponse response) {
        try (OutputStream out = connection.getOutputStream()) {
            DataOutputStream dos = new DataOutputStream(out);
            responseStartLine(dos, response.getStartLine());
            responseHeaders(dos, response.getHeaders());
            responseBody(dos, response.getBody());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private void responseStartLine(DataOutputStream dos, String startLine) {
        try {
            dos.writeBytes(startLine + "\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void responseHeaders(DataOutputStream dos, List<String> headers) {
        headers.forEach(it -> {
            try {
                dos.writeBytes(it);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        });
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.writeBytes("\r\n");
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
