package com.heyuhuan.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 * 命令 DTO
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
public class Command {

    private String phone;

    private Integer type;

    private String content;

    private Boolean assertTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Timestamp time;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getAssertTime() {
        return assertTime;
    }

    public void setAssertTime(Boolean assertTime) {
        this.assertTime = assertTime;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

}