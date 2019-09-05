package com.wzy.domain;

import com.alibaba.fastjson.annotation.JSONField;
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
 * 文件表
 * </p>
 *
 * @author zzw
 * @since 2018-08-01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_file")
public class File extends Model<File> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "tid", type = IdType.AUTO)
    private Integer tid;
    /**
     * 文件实际名称
     */
    private String name;
    /**
     * 类型
     */
    private String type;
    /**
     * MIME类型
     */
    private String mime;
    /**
     * 路径
     */
    private String path;

    /**
     * url
     */
    @TableField(exist = false)
    private String url;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField("created_at")
    private Date createdAt;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField("updated_at")
    private Date updatedAt;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField("deleted_at")
    private Date deletedAt;

    @Override
    protected Serializable pkVal() {
        return this.tid;
    }
}
