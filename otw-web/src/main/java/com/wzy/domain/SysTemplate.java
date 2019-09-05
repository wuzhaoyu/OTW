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
@TableName("sys_template")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysTemplate extends Model<SysTemplate> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 键
     */
    @TableField("ref_key")
    private String refKey;
    /**
     * 模板内容
     */
    @TableField("ref_value")
    private String refValue;
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
        return "SysTemplate{" +
        "id=" + id +
        ", refKey=" + refKey +
        ", refValue=" + refValue +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
