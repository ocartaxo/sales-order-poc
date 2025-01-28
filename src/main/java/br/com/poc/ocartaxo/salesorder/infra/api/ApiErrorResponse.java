package br.com.poc.ocartaxo.salesorder.infra.api;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        String timestamp,
        int status,
        String path,
        String message,
        String cause
) {
}
