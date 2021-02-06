package com.kutay.MANPORT.ws.error;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RestController
public class ErrorHandler implements ErrorController {//Eger bizim yakalamadigimiz bir error olursa bunu default olarak Spring kendisi hallediyordu artik oyle bir durum oldugunda buraya gelecek

    private final ErrorAttributes errorAttributes;

    public ErrorHandler(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    ApiError handleError(WebRequest webRequest) {
        Map<String, Object> attributes = this.errorAttributes.getErrorAttributes(webRequest, true);
        String message = (String)attributes.get("message");
        String path = (String)attributes.get("path");
        int status = (Integer) attributes.get("status");
        return new ApiError(status,message,path);

    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
