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
@TableName("biz_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BizComment extends Model<BizComment> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 被评论的文章或者页面的ID
     */
    private Long sid;
    /**
     * 评论人的ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 父级评论的id
     */
    private Long pid;
    /**
     * 评论人的QQ（未登录用户）
     */
    private String qq;
    /**
     * 评论人的昵称（未登录用户）
     */
    private String nickname;
    /**
     * 评论人的头像地址
     */
    private String avatar;
    /**
     * 评论人的邮箱地址（未登录用户）
     */
    private String email;
    /**
     * 评论人的网站地址（未登录用户）
     */
    private String url;
    /**
     * 评论的状态
     */
    private String status;
    /**
     * 评论时的ip
     */
    private String ip;
    /**
     * 经度
     */
    private String lng;
    /**
     * 纬度
     */
    private String lat;
    /**
     * 评论时的地址
     */
    private String address;
    /**
     * 评论时的系统类型
     */
    private String os;
    /**
     * 评论时的系统的简称
     */
    @TableField("os_short_name")
    private String osShortName;
    /**
     * 评论时的浏览器类型
     */
    private String browser;
    /**
     * 评论时的浏览器的简称
     */
    @TableField("browser_short_name")
    private String browserShortName;
    /**
     * 评论的内容
     */
    private String content;
    /**
     * 备注（审核不通过时添加）
     */
    private String remark;
    /**
     * 支持（赞）
     */
    private Integer support;
    /**
     * 反对（踩）
     */
    private Integer oppose;
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
        return "BizComment{" +
        "id=" + id +
        ", sid=" + sid +
        ", userId=" + userId +
        ", pid=" + pid +
        ", qq=" + qq +
        ", nickname=" + nickname +
        ", avatar=" + avatar +
        ", email=" + email +
        ", url=" + url +
        ", status=" + status +
        ", ip=" + ip +
        ", lng=" + lng +
        ", lat=" + lat +
        ", address=" + address +
        ", os=" + os +
        ", osShortName=" + osShortName +
        ", browser=" + browser +
        ", browserShortName=" + browserShortName +
        ", content=" + content +
        ", remark=" + remark +
        ", support=" + support +
        ", oppose=" + oppose +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
