package org.dongchao.web.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * 全局的异常处理类,处理非AuthcationException
 * Created by zhaodongchao on 2017/5/30.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 404 异常
     * <p/>
     * 后续根据不同的需求定制即可
     */
    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String processNotFoundException(NativeWebRequest request, Exception e) {
        return "error/404";
    }
}
