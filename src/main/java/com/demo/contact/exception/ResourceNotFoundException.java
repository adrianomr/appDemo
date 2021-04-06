package com.demo.contact.exception;

public class ResourceNotFoundException extends RuntimeException{
    private final String resource;
    public ResourceNotFoundException(String resource){
        super(String.format("%s not found", resource));
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
