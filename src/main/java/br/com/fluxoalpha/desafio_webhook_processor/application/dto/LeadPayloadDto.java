package br.com.fluxoalpha.desafio_webhook_processor.application.dto;

public record LeadPayloadDto(
        String name,
        String phoneNumber,
        String email,
        String campaignOrigin
) {

}
