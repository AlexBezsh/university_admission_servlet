package com.bezshtanko.university_admission_servlet.exception;

public class FacultyNotExistsException extends RuntimeException {

    public FacultyNotExistsException() {
        super();
    }

    public FacultyNotExistsException(String message) {
        super(message);
    }

    public FacultyNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FacultyNotExistsException(Throwable cause) {
        super(cause);
    }

    protected FacultyNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
