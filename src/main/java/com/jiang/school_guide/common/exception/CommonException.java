package com.jiang.school_guide.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


/**
 * Base exception of the project
 */
public abstract class CommonException extends RuntimeException {

    /**
     * Error errorData.
     */
    private Object errorData;


    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    @NonNull
    public abstract HttpStatus getStatus();

    @Nullable
    public Object getErrorData() {
        return errorData;
    }

    /**
     * Sets error errorData.
     *
     * @param errorData error data
     * @return page exception.
     */
    @NonNull
    public CommonException setErrorData(@Nullable Object errorData) {
        this.errorData = errorData;
        return this;
    }
}
