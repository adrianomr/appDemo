package com.demo.contact.web;


import com.demo.contact.exception.ResourceNotFoundException;
import com.demo.contact.web.resource.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
class GlobalControllerExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleConflict(HttpServletRequest req, Exception ex) {
        return new ErrorResponse(req.getRequestURL().toString(), ex);
    }
}
