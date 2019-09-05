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
@TableName("sys_link")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysLink extends Model<SysLink> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 链接地址
     */
    private String url;
    /**
     * 链接名
     */
    private String name;
    /**
     * 链接介绍
     */
    private String description;
    /**
     * 友链站长邮箱
     */
    private String email;
    /**
     * 友链站长QQ
     */
    private String qq;
    private String favicon;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 是否首页显示
     */
    @TableField("home_page_display")
    private Integer homePageDisplay;
    /**
     * 备注
     */
    private String remark;
    /**
     * 来源：管理员添加、自动申请
     */
    private String source;
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
        return "SysLink{" +
        "id=" + id +
        ", url=" + url +
        ", name=" + name +
        ", description=" + description +
        ", email=" + email +
        ", qq=" + qq +
        ", favicon=" + favicon +
        ", status=" + status +
        ", homePageDisplay=" + homePageDisplay +
        ", remark=" + remark +
        ", source=" + source +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
