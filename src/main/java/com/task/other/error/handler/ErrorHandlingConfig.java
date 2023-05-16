package com.task.other.error.handler;

import com.task.other.localization.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorHandlingConfig {

    private LocalizationService localizationService;

    @Autowired
    public ErrorHandlingConfig(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    @Bean
    public ErrorAttributes errorAttributes() {
        return new CustomErrorAttributes(localizationService);
    }
}
