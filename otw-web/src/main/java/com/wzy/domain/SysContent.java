package com.wzy.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *  资源内容
 * </p>
 * @author wuzhaoyu
 * @since 2019-03-06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_content")
public class SysContent extends Model<SysContent> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "tid", type = IdType.AUTO)
    private Integer tid;
    private String name;
    private String title;
    /**
     * 描述
     */
    private String description;
    @TableField("created_at")
    private Date createdAt;
    @TableField("updated_at")
    private Date updatedAt;
    @TableField("deleted_at")
    private Date deletedAt;
    /**
     * 资源路径
     */
    private String url;
    /**
     * 创建人
     */
    @TableField("created_by")
    private Integer createdBy;
    /**
     * 文件
     */
    @TableField("file_id")
    private Integer fileId;
    /**
     * 1 有效 0 无效
     */
    private Integer status;
    /**
     * 资源类型
     */
    private String type;

    @TableField(exist = false)
    private String fileName;


    @Override
    protected Serializable pkVal() {
        return this.tid;
    }


}
