package com.vincent.springboot.webflux.exception;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

/**
 * 自定义异常错误信息
 * @author vincent.li
 */
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(request, includeStackTrace);

        if (getError(request) instanceof GlobalException) {
            GlobalException ex = (GlobalException) getError(request);
            map.put("exception", ex.getClass().getSimpleName());
            map.put("message", ex.getMessage());
            map.put("status", ex.getStatus().value());
            map.put("error", ex.getStatus().getReasonPhrase());

            return map;
        }

        map.put("exception", "SystemException");
        map.put("status", "500");
        return map;
    }
}
