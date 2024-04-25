package com.springcrud.model;

import com.springcrud.utils.enums.ApiResponse;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseController {
    public ApiResponse apiResponse;
    public Object paylaod;

    private static final String RESPONSE_CODE_KEY = "responseCode";
    private static final String RESPONSE_DESC_KEY = "responseDesc";
    private static final String PAYLAOD_KEY = "paylaod";

    public BaseController() {
        reset();
    }
    public Map getResponse() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(RESPONSE_CODE_KEY, apiResponse.getValue());
        response.put(RESPONSE_DESC_KEY, apiResponse.getDesc());
        response.put(PAYLAOD_KEY, paylaod);
        reset();    //Reset after send
        return response;
    }

    private void reset() {
        apiResponse = ApiResponse.NONE;
        paylaod = null;
    }

}
