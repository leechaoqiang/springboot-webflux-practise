package com.vincent.springboot.webflux.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 定义一个全局自定义异常
 * @author vincent.li
 */
public class GlobalException extends ResponseStatusException {

    public GlobalException(HttpStatus status, String message) {
        super(status, message);
    }

    public GlobalException(HttpStatus status, String message, Throwable e) {
        super(status, message, e);
    }
}
