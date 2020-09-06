package com.wzy.exception.handle;

import com.wzy.exception.BadRequestException;
import com.wzy.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 类功能说明: 全局异常类
 * 类修改者	创建日期2019/9/5
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    public ResponseEntity handleException(Throwable e){

        log.error(ThrowableUtil.getStackTrace(e));

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(),e.getMessage());

        return buildResponseEntity(apiError);
    }

    /**
     *
     * @return
     */
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity badRequestExceptionException(BadRequestException e){

        log.error(ThrowableUtil.getStackTrace(e));

        ApiError apiError = new ApiError(e.getStatus(), e.getMessage());

        return buildResponseEntity(apiError);
    }
    /**
     * @Author wzy
     * @Description 统一返回
     * @Date:   2019/9/5 20:44
     **/
    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError){
          return new ResponseEntity<>(apiError,HttpStatus.valueOf(apiError.getStatus()));

    }
}
