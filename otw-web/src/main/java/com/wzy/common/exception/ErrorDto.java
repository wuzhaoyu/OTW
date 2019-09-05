package com.wzy.common.exception;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 类功能说明: 错误
 * 类修改者	创建日期2019/8/22
 * 修改说明
 * @author com.wzy
 * @version V1.0
 **/
@Data
@Accessors(chain = true)
public class ErrorDto {
    private int errorCode;
    private String tips;
}
