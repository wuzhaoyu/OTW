package com.wzy.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuzhaoyu
 * @since 2019-08-25
 */
@TableName("history_fs_new")
@Data
@Accessors(chain = true)
public class HistoryFsNew extends Model<HistoryFsNew> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 代码
     */
    private String code;
    /**
     * 开盘价
     */
    @TableField(value = "openPrice")
    private String openPrice;
    /**
     * 收盘价
     */
    @TableField(value = "clsoePirce")
    private String clsoePirce;
    /**
     * 成交量
     */
    @TableField(value = "volumn")
    private String volumn;
    /**
     * 最高价
     */
    @TableField(value = "hignPrice")
    private String hignPrice;
    /**
     * 最低价
     */
    @TableField(value = "lowPrice")
    private String lowPrice;
    /**
     * 添加日期
     */
    @TableField(value = "dateTime")
    private String dateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "HistoryFsNew{" +
        "id=" + id +
        ", code=" + code +
        ", openPrice=" + openPrice +
        ", clsoePirce=" + clsoePirce +
        ", volumn=" + volumn +
        ", hignPrice=" + hignPrice +
        ", lowPrice=" + lowPrice +
        ", dateTime=" + dateTime +
        "}";
    }
}
