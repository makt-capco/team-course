package com.capco.teamcourse.exception.component;


import com.capco.teamcourse.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ExceptionResponse handleNotFoundException(final NotFoundException ex, final HttpServletRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setPath(request.getRequestURI());

        LOGGER.error(ex.getMessage());

        return exceptionResponse;

    }
}
