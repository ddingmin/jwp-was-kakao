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

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String requestHeader = RequestReaderUtils.readHeader(reader);
            logger.debug(requestHeader);

            HttpRequest httpRequest = new HttpRequest(requestHeader);
            httpRequest.setBody(RequestReaderUtils.readBody(reader, Integer.parseInt(httpRequest.getAttribute("Content-Length"))));

            HttpResponse httpResponse = new HttpResponse();

            logger.debug(httpRequest.getBody());

            Handler handler = RequestHandlerMapper.mapping(httpRequest);
            handler.handling(httpRequest, httpResponse);

            DataOutputStream dos = new DataOutputStream(out);
            response(dos, httpResponse);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private void response(DataOutputStream dos, HttpResponse response) {
        responseStartLine(dos, response.getStartLine());
        responseHeaders(dos, response.getHeaders());
        responseBody(dos, response.getBody());
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

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
