package com.wzy.common;

import javax.servlet.http.HttpServletRequest;

/**
 * 类功能说明: 在请求头中获取token
 * 类修改者	创建日期2019/2/27
 * 修改说明
 * <p>Title: TokenUtil.java</p>
 *
 * @author Wzy
 * @version V1.0
 **/
public class TokenUtil {
    public TokenUtil() {
    }

    public static String getToken(HttpServletRequest req) { return   req.getHeader(SystemConst.HTTP_HEADER_JWT_KEY); }
}
