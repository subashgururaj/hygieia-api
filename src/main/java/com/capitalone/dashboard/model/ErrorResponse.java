package com.capitalone.dashboard.model;

import org.springframework.validation.BindException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class ErrorResponse {
    private final Map<String, List<String>> globalErrors = new HashMap<>();
    private final Map<String, List<String>> fieldErrors = new HashMap<>();

    public Map<String, List<String>> getGlobalErrors() {
        return globalErrors;
    }

    public Map<String, List<String>> getFieldErrors() {
        return fieldErrors;
    }

    public void addFieldError(String field, String error) {
        List<String> errors = fieldErrors.computeIfAbsent(field, k -> new ArrayList<>());
        errors.add(error);
    }

    public static ErrorResponse fromBindException(BindException bindException) {
        ErrorResponse errorResponse = new ErrorResponse();

        bindException.getGlobalErrors().forEach(objectError -> {
            List<String> errors = errorResponse.globalErrors.computeIfAbsent(objectError.getObjectName(), k -> new ArrayList<>());
            errors.add(objectError.getDefaultMessage());
        });

        bindException.getFieldErrors().forEach(fieldError -> {
            List<String> errors = errorResponse.fieldErrors.computeIfAbsent(fieldError.getField(), k -> new ArrayList<>());
            errors.add(fieldError.getDefaultMessage());
        });

        return errorResponse;
    }
}
