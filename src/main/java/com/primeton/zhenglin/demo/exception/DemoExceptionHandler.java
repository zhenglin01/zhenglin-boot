package com.primeton.zhenglin.demo.exception;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.primeton.zhenglin.demo.util.ResponseResult;

/**
 * 统一异常处理
 * @author Lion
 */
@ControllerAdvice
public class DemoExceptionHandler {
	private static Logger log = LoggerFactory.getLogger(DemoExceptionHandler.class);

    //捕捉到的异常
    @SuppressWarnings("rawtypes")
	@ExceptionHandler(value = DemoException.class)
    public ResponseEntity<ResponseResult> handleServiceException(DemoException exception)
    {
        log.error(exception.getMessage(),exception);

        return new ResponseEntity<>(new ResponseResult(exception), exception.getHttpStatus());
    }

    //其他异常
    @SuppressWarnings("rawtypes")
	@ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseResult> handleServerException(Exception exception)
    {
        log.error("系统错误：",exception);

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        Class exceptionClazz = exception.getClass();
        if (Objects.equals(MissingServletRequestParameterException.class, exceptionClazz))
        {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        else if (Objects.equals(HttpRequestMethodNotSupportedException.class, exceptionClazz))
        {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(new ResponseResult(exception), httpStatus);
    }
}
