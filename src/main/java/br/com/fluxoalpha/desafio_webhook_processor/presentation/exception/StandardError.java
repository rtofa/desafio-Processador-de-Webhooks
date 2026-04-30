package br.com.fluxoalpha.desafio_webhook_processor.presentation.exception;

import java.time.LocalDateTime;

public record StandardError(
    LocalDateTime timestamp,
    Integer status,
    String error,
    String path
) {

}
