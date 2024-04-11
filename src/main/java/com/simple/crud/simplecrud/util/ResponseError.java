package com.simple.crud.simplecrud.util;

import org.springframework.util.ObjectUtils;

public class ResponseError {
    private Integer code;
    private String message;
    private String description;

    public ResponseError(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.description = message;
    }

    public ResponseError(Integer code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return ObjectUtils.isEmpty(description) ? message : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
