package br.com.fluxoalpha.desafio_webhook_processor.application.usecase;

import br.com.fluxoalpha.desafio_webhook_processor.application.dto.LeadPayloadDto;
import br.com.fluxoalpha.desafio_webhook_processor.application.ports.LeadPublisher;
import br.com.fluxoalpha.desafio_webhook_processor.domain.model.Lead;
import org.springframework.stereotype.Service;

@Service
public class ReceiveWebhookUseCase {

    private final LeadPublisher leadPublisher;

    public ReceiveWebhookUseCase(LeadPublisher leadPublisher) {
        this.leadPublisher = leadPublisher;
    }

    public void executar(LeadPayloadDto payloadDto){
        Lead novoLead = new Lead(
                payloadDto.name(),
                payloadDto.phoneNumber(),
                payloadDto.email(),
                payloadDto.campaignOrigin()
        );

        leadPublisher.enviarParaFila(novoLead);

    }
}
