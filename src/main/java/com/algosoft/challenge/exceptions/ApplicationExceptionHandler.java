package com.algosoft.challenge.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ApplicationExceptionHandler implements Serializable {

    private LocalDateTime timestamp;
    private String message;
    private List<String> details;

    public ApplicationExceptionHandler(LocalDateTime timestamp, String message, List<String> details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getDetails() {
        return details;
    }


}
