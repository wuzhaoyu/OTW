package com.wzy.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuzhaoyu
 * @since 2019-07-04
 */
@TableName("sys_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 已登录用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 日志类型（系统操作日志，用户访问日志，异常记录日志）
     */
    private String type;
    /**
     * 日志级别
     */
    @TableField("log_level")
    private String logLevel;
    /**
     * 日志内容（业务操作）
     */
    private String content;
    /**
     * 请求参数（业务操作）
     */
    private String params;
    /**
     * 爬虫类型（当访问者被鉴定为爬虫时该字段表示爬虫的类型）
     */
    @TableField("spider_type")
    private String spiderType;
    /**
     * 操作用户的ip
     */
    private String ip;
    /**
     * 操作用户的user_agent
     */
    private String ua;
    /**
     * 评论时的系统类型
     */
    private String os;
    /**
     * 评论时的浏览器类型
     */
    private String browser;
    /**
     * 请求的路径
     */
    @TableField("request_url")
    private String requestUrl;
    /**
     * 请求来源地址
     */
    private String referer;
    /**
     * 添加时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;




    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysLog{" +
        "id=" + id +
        ", userId=" + userId +
        ", type=" + type +
        ", logLevel=" + logLevel +
        ", content=" + content +
        ", params=" + params +
        ", spiderType=" + spiderType +
        ", ip=" + ip +
        ", ua=" + ua +
        ", os=" + os +
        ", browser=" + browser +
        ", requestUrl=" + requestUrl +
        ", referer=" + referer +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
