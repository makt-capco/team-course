package com.capco.teamcourse.exception;

public class TeamCourseException extends RuntimeException {

    public TeamCourseException() {
    }

    public TeamCourseException(String message, Throwable cause) {
        super(message, cause);
    }

    public TeamCourseException(String message) {
        super(message);
    }

    public TeamCourseException(Throwable cause) {
        super(cause);
    }
}
