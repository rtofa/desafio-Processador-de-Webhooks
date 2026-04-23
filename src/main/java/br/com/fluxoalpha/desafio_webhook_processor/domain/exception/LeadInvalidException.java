package br.com.fluxoalpha.desafio_webhook_processor.domain.exception;

public class LeadInvalidException extends RuntimeException{

    public LeadInvalidException(String message){
        super(message);
    }
}
