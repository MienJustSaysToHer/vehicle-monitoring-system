package com.heyuhuan.admin.dto;

/**
 * 数据交换 DTO
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
public class Json implements java.io.Serializable {

    private static final long serialVersionUID = 6564267884377049148L;

    private boolean success = false;

    private String msg = "";

    private Object obj = null;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

}