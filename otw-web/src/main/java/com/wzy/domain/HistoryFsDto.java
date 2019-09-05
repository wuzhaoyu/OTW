package com.wzy.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuzhaoyu
 * @since 2019-08-25
 */
@Data
@Accessors(chain = true)
public class HistoryFsDto  {

    private static final long serialVersionUID = 1L;


    private Long id;
    /**
     * 代码
     */
    private String code;
    /**
     * 成交价
     */
    private String highPrice;
    /**
     * 价格变动
     */
    private String lowPrice;
    /**
     * 开盘价
     */
    private String openPrice;
    /**
     * 收盘价
     */
    private String closePrice;
    /**
     * 成交量
     */
    private String volumn;
    /**
     * 添加日期
     */
    private String dateTime;



}
