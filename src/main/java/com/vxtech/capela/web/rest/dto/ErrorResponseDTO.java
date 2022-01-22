package com.vxtech.capela.web.rest.dto;

public class ErrorResponseDTO {
    private final String field;
    private final String error;

    public ErrorResponseDTO(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }
}
