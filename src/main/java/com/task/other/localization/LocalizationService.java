package com.task.other.localization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * LocalizationService is used to localize error messages
 */
@Service
public class LocalizationService {

    private final MessageSource messageSource;

    @Autowired
    public LocalizationService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String code, String... args) {
        Locale newLocale = new Locale("en");
        return messageSource.getMessage(code, args, code, newLocale);
    }
}

