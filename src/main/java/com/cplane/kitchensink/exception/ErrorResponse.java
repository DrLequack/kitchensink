package com.cplane.kitchensink.exception;

public record ErrorResponse(String message,
                            String path,
                            String requestMethod,
                            Integer errorCode) {
}
