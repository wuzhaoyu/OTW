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
@TableName("biz_article_tags")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BizArticleTags extends Model<BizArticleTags> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 标签表主键
     */
    @TableField("tag_id")
    private Long tagId;
    /**
     * 文章ID
     */
    @TableField("article_id")
    private Long articleId;
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
        return "BizArticleTags{" +
        "id=" + id +
        ", tagId=" + tagId +
        ", articleId=" + articleId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
