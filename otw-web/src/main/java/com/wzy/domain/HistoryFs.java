package com.wzy.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuzhaoyu
 * @since 2019-08-25
 */
@TableName("history_fs")
public class HistoryFs extends Model<HistoryFs> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 代码
     */
    private String code;
    /**
     * 成交价
     */
    private String cjj;
    /**
     * 价格变动
     */
    private String jgbd;
    /**
     * 成交量
     */
    private String cjl;
    /**
     * 成交额
     */
    private String cje;
    /**
     * 性质
     */
    private String xz;
    /**
     * 添加日期
     */
    @TableField("date_time")
    private String dateTime;
    /**
     * 成交时间
     */
    private String time;
    private Date codetime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCjj() {
        return cjj;
    }

    public void setCjj(String cjj) {
        this.cjj = cjj;
    }

    public String getJgbd() {
        return jgbd;
    }

    public void setJgbd(String jgbd) {
        this.jgbd = jgbd;
    }

    public String getCjl() {
        return cjl;
    }

    public void setCjl(String cjl) {
        this.cjl = cjl;
    }

    public String getCje() {
        return cje;
    }

    public void setCje(String cje) {
        this.cje = cje;
    }

    public String getXz() {
        return xz;
    }

    public void setXz(String xz) {
        this.xz = xz;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getCodetime() {
        return codetime;
    }

    public void setCodetime(Date codetime) {
        this.codetime = codetime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "HistoryFs{" +
        "id=" + id +
        ", code=" + code +
        ", cjj=" + cjj +
        ", jgbd=" + jgbd +
        ", cjl=" + cjl +
        ", cje=" + cje +
        ", xz=" + xz +
        ", dateTime=" + dateTime +
        ", time=" + time +
        ", codetime=" + codetime +
        "}";
    }
}
