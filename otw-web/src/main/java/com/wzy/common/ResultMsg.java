package com.wzy.common;

/**
 * 类功能说明:
 * 类修改者	创建日期2019/3/6
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 **/
public class ResultMsg {
    int code;
    String msg;
    Object data;

    public static ResultMsg create(int code, String msg) {
        return (new ResultMsg()).setCode(code).setMsg(msg);
    }

    public static ResultMsg create(int code, String msg, Object data) {
        return create(code, msg).setData(data);
    }

    public static ResultMsg success() {
        return (new ResultMsg()).setCode(200).setMsg("成功");
    }

    public static ResultMsg success(Object data) {
        return success().setData(data);
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public Object getData() {
        return this.data;
    }

    public ResultMsg setCode(int code) {
        this.code = code;
        return this;
    }

    public ResultMsg setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResultMsg setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResultMsg)) {
            return false;
        } else {
            ResultMsg other = (ResultMsg)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getCode() != other.getCode()) {
                return false;
            } else {
                Object this$msg = this.getMsg();
                Object other$msg = other.getMsg();
                if (this$msg == null) {
                    if (other$msg != null) {
                        return false;
                    }
                } else if (!this$msg.equals(other$msg)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ResultMsg;
    }
    @Override
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
         result = result * 59 + this.getCode();
        Object $msg = this.getMsg();
        result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }
    @Override
    public String toString() {
        return "ResultMsg(code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }

    public ResultMsg(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultMsg() {
    }
}
