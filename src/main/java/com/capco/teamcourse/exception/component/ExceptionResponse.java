package com.capco.teamcourse.exception.component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {

    private String message;
    private String path;
    private Long timestamp;


}
