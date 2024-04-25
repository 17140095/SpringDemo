package com.springcrud.utils.enums;

public enum ApiResponse {
    SUCCESS("00", "Success"),
    UNEXPECTED("UXTD", "Unexpected error occurred"),
    ALREADY_EXIST("ARE", "Record already exists"),
    NO_RECORD("NRF", "Record not found"),
    NONE(null, null)
    ;

    private final String value;
    private String desc;
    ApiResponse(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    public ApiResponse setMsg(String message) {
        this.desc = message;
        return this;
    }
    public String getValue() {
        return value;
    }
    public String getDesc() {
        return desc;
    }
}


