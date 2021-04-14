package com.jiang.school_guide.common.exception;

import org.springframework.http.HttpStatus;

public class UploadException extends CommonException {

    public UploadException(String message) {
        super(message);
    }

    public UploadException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}