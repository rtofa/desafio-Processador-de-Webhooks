package br.com.fluxoalpha.desafio_webhook_processor.presentation.controller;

import br.com.fluxoalpha.desafio_webhook_processor.application.dto.LeadPayloadDto;
import br.com.fluxoalpha.desafio_webhook_processor.application.usecase.ReceiveWebhookUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/webhooks")
public class WebhookController {

    private final ReceiveWebhookUseCase receiveWebhookUseCase;

    public WebhookController(ReceiveWebhookUseCase receiveWebhookUseCase){
        this.receiveWebhookUseCase = receiveWebhookUseCase;
    }

    @PostMapping("/leads")
    public ResponseEntity<Void> receberLead(@RequestBody LeadPayloadDto payloadDto){

        receiveWebhookUseCase.executar(payloadDto);

        return ResponseEntity.accepted().build();
    }
}
