package com.hercules.configuration.error;

import lombok.Data;

@Data
public class ErrorResponse {

    private final String status;
    private final String code;
    private final String error;

}
