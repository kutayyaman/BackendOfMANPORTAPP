package com.kutay.MANPORT.ws.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // yani cevap olarak ApiError dondugumuz zaman diyelimki validationErrors null ozaman bunu geriye donme yani validationErrorsu cikart bunun icinden demis oluyoruz
public class ApiError {
    private int status;
    private String message;
    private String path;
    private long timestamp = new Date().getTime();
    private Map<String, String> validationErrors;

    public ApiError(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }
}
