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
@TableName("biz_file")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BizFile extends Model<BizFile> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("user_id")
    private Integer userId;
    @TableField("storage_type")
    private String storageType;
    @TableField("original_file_name")
    private String originalFileName;
    private Long size;
    private String suffix;
    private Integer width;
    private Integer height;
    @TableField("file_path")
    private String filePath;
    @TableField("full_file_path")
    private String fullFilePath;
    @TableField("file_hash")
    private String fileHash;
    @TableField("upload_type")
    private String uploadType;
    @TableField("upload_start_time")
    private Date uploadStartTime;
    @TableField("upload_end_time")
    private Date uploadEndTime;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;




    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BizFile{" +
        "id=" + id +
        ", userId=" + userId +
        ", storageType=" + storageType +
        ", originalFileName=" + originalFileName +
        ", size=" + size +
        ", suffix=" + suffix +
        ", width=" + width +
        ", height=" + height +
        ", filePath=" + filePath +
        ", fullFilePath=" + fullFilePath +
        ", fileHash=" + fileHash +
        ", uploadType=" + uploadType +
        ", uploadStartTime=" + uploadStartTime +
        ", uploadEndTime=" + uploadEndTime +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
