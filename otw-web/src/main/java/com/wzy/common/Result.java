package com.wzy.common;

import lombok.Data;

/**
 * @author polobao
 */
@Data
public class Result {
    private int code;

    private String msg;

    private Object data;

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public static Result create(int code, String msg) {
        return new Result().setCode(code).setMsg(msg);
    }

    public static Result create(int code, String msg, Object data) {
        return create(code, msg).setData(data);
    }

    public static Result handleSuccess(String msg, Object data) {
        return new Result().setMsg(msg).setCode(ResultCode.CODE_SUCCESS).setData(data);
    }

    public static Result handleFail(String msg, Object data) {
        return new Result().setMsg(msg).setCode(ResultCode.CODE_ERROR).setData(data);
    }

    public Result handleNoPm(String msg, Object data) {
        return new Result().setMsg("没有权限").setCode(ResultCode.CODE_NO_PM).setData(data);
    }

    public static Result success() {
        return new Result().setCode(200).setMsg("成功").setData(true);
    }

    public static Result success(int code, String msg) {
        return new Result().setCode(code).setMsg(msg);
    }

    public static Result success(Object data) {
        return success().setData(data);
    }
}
