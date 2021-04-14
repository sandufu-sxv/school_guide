package com.jiang.school_guide.core;

import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.common.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Author evildoer
 * @Description Exception handler of controller
 * @Date 21:36 2019/11/14
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.OK)
    public ServerResponse handleConflictException(ConflictException e) {
        return ServerResponse.createByErrorCodeMessage(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler({UniqueException.class, EmptyException.class, TimeException.class})
    @ResponseStatus(HttpStatus.OK)
    public ServerResponse handleUniqueException(CommonException e) {
        return ServerResponse.createByErrorCodeMessage(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public ServerResponse handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        return ServerResponse.createByErrorCodeMessage(HttpStatus.EXPECTATION_FAILED.value(), e.getMessage());
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ServerResponse handleGlobalException(Exception e) {
//        System.out.println(e.getMessage());
//        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//        return ServerResponse.createByErrorCodeMessage(status.value(), e.getMessage());
//    }

    private <T> ServerResponse<T> handleBaseException(Throwable t) {
        Assert.notNull(t, "Throwable must not be null");
        log.error("Captured an exception", t);
        return ServerResponse.createByErrorMessage(t.getMessage());
    }
}

