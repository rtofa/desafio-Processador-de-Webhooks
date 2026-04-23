package br.com.fluxoalpha.desafio_webhook_processor.application.ports;

import br.com.fluxoalpha.desafio_webhook_processor.domain.model.Lead;

public interface LeadPublisher {

    void enviarParaFila(Lead lead);
}
