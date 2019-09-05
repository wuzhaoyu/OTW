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
@TableName("biz_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BizType extends Model<BizType> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long pid;
    /**
     * 文章类型名
     */
    private String name;
    /**
     * 类型介绍
     */
    private String description;
    /**
     * 类型key
     */
    private String key;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否可用
     */
    private Integer available;
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
        return "BizType{" +
        "id=" + id +
        ", pid=" + pid +
        ", name=" + name +
        ", description=" + description +
        ", sort=" + sort +
        ", icon=" + icon +
        ", available=" + available +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
