package com.harry.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private String code;              // Business error code (e.g., ORDER_NOT_FOUND)
    private String message;           // User-facing error message
    private String detail;            // Additional detail for debugging (optional)
    private String path;              // Request path where the error occurred
    private OffsetDateTime timestamp; // Timestamp of the error event
}
