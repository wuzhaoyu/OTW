package com.wzy.common;

/**
 * ClassName ResultCode
 * Description
 * Author lixun
 * Date 2019/1/9 15:38
 * Version 1.0
 **/

public class ResultCode {
    static final int CODE_SUCCESS = 200;
    static final int CODE_ERROR = 2100;
    static final int CODE_NO_PM = 3100;
    public static int ERROR_VALI_PERMISSION = 401;
    public static String ERROR_VALI_PERMISSION_MSG = "资源需要授权";
    public static int ERROR_MODIFICATION_PERMISSION = 1000002;
    public static String ERROR_MODIFICATION_PERMISSION_MSG = "没有修改权限";
    public static int ERROR_SERVICE_CODE = 500;
    public static int ERROR_COMMON_LOGIN_NOT_CORRECT = 1000001;
    public static String ERROR_COMMON_LOGIN_NOT_CORRECT_MSG = "用户名密码错误";
    public static String ERROR_COMMON_LOGIN_PHONE_NOT_CORRECT_MSG = "手机验证码错误";
    public static String ERROR_COMMON_LOGIN_EMAIL_NOT_CORRECT_MSG = "手机验证码错误";
    public static int ERROR_PARAMETER_NOT_CORRECT = 1001001;
    public static String ERROR_PARAMETER_NOT_CORRECT_MSG = "参数错误";
    public static int ERROR_QUERY_FAILED = 1001002;
    public static String ERROR_QUERY_FAILED_MSG = "查询失败";
    public static int ERROR_SAVE_FAILED = 1001003;
    public static String ERROR_SAVE_FAILED_MSG = "保存失败";
    public static int ERROR_DELETE_FAILED = 1001004;
    public static String ERROR_DELETE_FAILED_MSG = "删除失败";
    public static int ERROR_OPRATION_FAILED = 1001005;
    public static String ERROR_OPRATION_FAILED_MSG = "操作失败";
    public static int ERROR_UPLOAD_FAILED = 1000009;
    public static String ERROR_UPLOAD_FAILED_MSG = "文件上传失败";
    public static int ERROR_PHONE_FAILED = 1010001;
    public static String ERROR_PHONE_FAILED_MSG = "验证码发送失败";
    public static final int ERROR_PHONE_LOSE_FAILED = 1010002;
    public static final String ERROR_PHONE_LOSE_FAILED_MSG = "验证码失效";
    public static final int ERROR_PHONE_LOGIN_FAILED = 1010003;
    public static final String ERROR_PHONE_LOGIN_FAILED_MSG = "手机号被占用";
    public static final int ERROR_PHONE_VALIDATOR_FAILED = 1010005;
    public static final String ERROR_PHONE_VALIDATOR_FAILED_MSG = "手机号验证失败";
    public static final int ERROR_EMAIL_VALIDATOR_FAILED = 1010004;
    public static final String ERROR_EMAIL_VALIDATOR_FAILED_MSG = "邮箱验证失败";

}
