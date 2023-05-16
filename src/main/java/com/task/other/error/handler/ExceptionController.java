package com.task.other.error.handler;

import com.task.other.error.exception.ApiException;
import com.task.other.localization.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ExceptionController is used for handling exceptions of ApiException class
 */
@ControllerAdvice
public class ExceptionController {

    private LocalizationService localizationService;

    @Autowired
    public ExceptionController(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    @ExceptionHandler(ApiException.class)
    public Object handleRestException(ApiException e, HttpServletRequest request) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("timestamp", new Date());
        errorAttributes.put("status", e.getHttpStatus().value());
        errorAttributes.put("path", request.getServletPath());
        String error;
        if (e.isCustomMessage()) {
            error = e.getMessage();
        } else {
            error = e.getHttpStatus().getReasonPhrase();
        }
        errorAttributes.put("error", error);
        String message = localizationService.getMessage(error);
        errorAttributes.put("message", message);
        if (error.equals(message)) {
            errorAttributes.put("error", "error");
        }
        return new ResponseEntity<>(errorAttributes, e.getHttpStatus());
    }

}
