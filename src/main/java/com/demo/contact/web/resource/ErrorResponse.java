package com.demo.contact.web.resource;

public class ErrorResponse {
    public final String url;
    public final String message;

    public ErrorResponse(String url, Exception exception) {
        this.url = url;
        this.message = exception.getMessage();
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }
}
