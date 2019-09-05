package com.wzy.common;

/**
 * 系统模块常量
 */
public class SystemConst {

    public static final String HTTP_HEADER_JWT_KEY = "x-access-token";
    public static final String CACHED_USER_INFO = "cached_user_info";
    public static final long DEFAULT_JWT_EXPIRE_TIME = 7200L;
    public static final long SMS_JWT_EXPIRE_TIME = 300L;
    public static final long SMS_EMAIL_JWT_EXPIRE_TIME = 1800L;
    public static final long PHONE_CODE_EXPIRE_TIME = 60L;

    public static final String SMS = "sms";
    public static final String WEIXIN = "weixin";
    public static final String EMAIL = "email";
    public static final String SYS = "sys";

    /**
     * msg_type
     */
    public static final String MSG_TYPE_PROJECT = "project";
    public static final String MSG_TYPE_ORG = "org";
    public static final String MSG_TYPE_CUSTOM = "custom";

    /**
     * is has email\sms\weixin\sys
     */
    public  static final Integer YES = 1;
    public  static final Integer NO = 0;


    /**
     * msg_status
     */
    public static final Integer MSG_STATUS_ALL_SUCCESS = 9;
    public static final Integer MSG_STATUS_SENDING= 8;
    public static final Integer MSG_STATUS_UNSENT = -1;
    public static final Integer MSG_STATUS_SEND_FAILED = 0;
    public static final Integer MSG_STATUS_NOT_ALL_SUCCESS = 1;

    /**
     * msg_receiver_status
     */
    public static final Integer MSG_RECEIVER_STATUS_UNSENT = 0;
    public static final Integer MSG_RECEIVER_STATUS_SEND_FAILED = -1;
    public static final Integer MSG_RECEIVER_STATUS_SEND_SUCCESS = 2;
    public static final Integer MSG_RECEIVER_STATUS_READ = 9;

    /**
     * 流程执行类型
     */

    public enum ProcessType {
        /**
         * 普通
         */
        Normal,

        /**
         * 修改
         */
        Modify,

        /**
         * 弃用
         */
        Deprecated
    }

    /**
     *
     */
    public static final String POSTTYPE = "post_type";

    public static String ACCESS_TOKEN = "x-access-token";
}
