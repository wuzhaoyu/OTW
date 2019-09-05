package com.wzy.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 **/
 @Data
 @AllArgsConstructor
 @NoArgsConstructor
 @Accessors(chain = true)
 @TableName("biz_article")
public class BizArticle extends Model<BizArticle> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 文章封面图片
     */
    @TableField("cover_image")
    private String coverImage;
    /**
     * 文章专属二维码地址
     */
    @TableField("qrcode_path")
    private String qrcodePath;
    @TableField("is_markdown")
    private Integer isMarkdown;
    /**
     * 文章内容
     */
    private String content;
    /**
     * markdown版的文章内容
     */
    @TableField("content_md")
    private String contentMd;
    /**
     * 是否置顶
     */
    private Integer top;
    /**
     * 类型
     */
    @TableField("type_id")
    private Long typeId;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 是否推荐
     */
    private Integer recommended;
    /**
     * 是否原创
     */
    private Integer original;
    /**
     * 文章简介，最多200字
     */
    private String description;
    /**
     * 文章关键字，优化搜索
     */
    private String keywords;
    /**
     * 是否开启评论
     */
    private Integer comment;
    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @TableField("update_time")
    private Date updateTime;

    /**
     * type 文章类型
     */
    @TableField(exist = false)
    private String type;

    /**
     * type 文章标签
     */
    @TableField(exist = false)
    private String tagName;

    /**
     * 开始时间
     */
    @TableField(exist = false)
    private String startDate;
    /**
     * 结束时间
     */
    @TableField(exist = false)
    private String endDate;

    /**
     * 原文id
     */
    @TableField("source_id")
    private String sourceId;
    /**
     * 查看次数
     */
    private Long views;
    /**
     * 标签
     */
    private String tags;
    /**
     * 名称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 策略
     */
    private String strategy;
    /**
     * 子标题
     */
    @TableField("sub_title")
    private String subTitle;

    /**
     * 偏移量
     * */
    private int offset;
    /**
     * 条数
     */
    private int size;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }



}
