package com.kutay.MANPORT.ws.error;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JobDoesntExistInServerException extends RuntimeException {
    private final MessageSource messageSource;

    public JobDoesntExistInServerException(MessageSource messageSource) {
        this.messageSource=messageSource;
    }

    @Override
    public String getMessage() {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("manportapp.warning.issueUpdate.jobDoesNotExistInServer",null, locale);
        return message;
    }
}
