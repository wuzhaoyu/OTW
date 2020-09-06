package com.wzy.exception.handle;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 类功能说明: API异常反馈
 * 类修改者	创建日期2019/9/5
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@Data
public class ApiError {

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    private String message;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(Integer status, String message) {
        this();
        this.status = status;
        this.message = message;
    }


}
