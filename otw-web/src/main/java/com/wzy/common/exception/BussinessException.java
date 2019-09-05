package com.wzy.common.exception;

/**
 * 类功能说明: 业务关联异常
 * 类修改者	创建日期2019/8/22
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 **/
public class BussinessException extends RuntimeException {

    protected int errorCode;

    protected Object data;

    public BussinessException(int errorCode,String message,Object data,Throwable e){
        super(message,e);
        this.errorCode = errorCode;
        this.data = data;
    }

    public BussinessException(int errorCode,String message,Object data){
        this(errorCode,message,data,null);
    }
    public BussinessException(int errorCode,String message){
        this(errorCode,message,null,null);
    }

    public BussinessException(String message){
        this(0,message,null,null);
    }
    public BussinessException(String message,Throwable e){
        this(0,message,null,e);
    }
    public BussinessException(){

    }
    public BussinessException(Throwable e){
        super(e);
    }
    public int getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;

    }

}
