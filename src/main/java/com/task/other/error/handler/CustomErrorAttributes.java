package com.task.other.error.handler;

import com.task.other.localization.LocalizationService;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * CustomErrorAttributes is used to customise error attributes
 */
public class CustomErrorAttributes extends DefaultErrorAttributes {

    private LocalizationService localizationService;

    CustomErrorAttributes(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> attributes = super.getErrorAttributes(webRequest, options);
        attributes.remove("exception");
        String error = (String) attributes.get("error");
        attributes.put("message", localizationService.getMessage(error));
        return attributes;
    }
}
