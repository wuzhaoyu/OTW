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
@TableName("biz_article_look")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BizArticleLook extends Model<BizArticleLook> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 文章ID
     */
    @TableField("article_id")
    private Long articleId;
    /**
     * 已登录用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 用户IP
     */
    @TableField("user_ip")
    private String userIp;
    /**
     * 浏览时间
     */
    @TableField("look_time")
    private Date lookTime;
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
        return "BizArticleLook{" +
        "id=" + id +
        ", articleId=" + articleId +
        ", userId=" + userId +
        ", userIp=" + userIp +
        ", lookTime=" + lookTime +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
