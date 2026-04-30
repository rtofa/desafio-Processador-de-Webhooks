package br.com.fluxoalpha.desafio_webhook_processor.presentation.exception;

import br.com.fluxoalpha.desafio_webhook_processor.domain.exception.LeadInvalidException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice //Se der erro em qualquer Controller, o Spring chama essa classe
public class GlobalExceptionHandler {

    // Tratamento para erros de Regra de Negócio
    @ExceptionHandler(LeadInvalidException.class)
    public ResponseEntity<StandardError> handleLeadInvalidException(LeadInvalidException ex, HttpServletRequest request) {

        StandardError error = new StandardError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), // 400 - O cliente mandou algo errado
                ex.getMessage(), // Ex: "O formato do e-mail é inválido"
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Se der um erro fudido do nada (ex: o Banco de Dados explodiu)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleGenericException(Exception ex, HttpServletRequest request) {

        // Para ver log interno para debugar
        ex.printStackTrace();

        StandardError error = new StandardError(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), // 500 - A culpa é do nosso servidor
                "Ocorreu um erro interno inesperado. Nossa equipe técnica já foi notificada.",
                request.getRequestURI()

        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
