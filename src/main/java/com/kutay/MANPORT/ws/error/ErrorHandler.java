package com.kutay.MANPORT.ws.error;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
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
        String message = (String) attributes.get("message");
        String path = (String) attributes.get("path");
        int status = (Integer) attributes.get("status");
        ApiError apiError = new ApiError(status, message, path);

        if (attributes.containsKey("errors")) {
            Map<String, String> validationErrors = new HashMap<>();
            List<FieldError> fieldErrors = (List<FieldError>) attributes.get("errors");
            for (FieldError fieldError : fieldErrors) {
                validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            apiError.setValidationErrors(validationErrors);
        }

        return apiError;

    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
