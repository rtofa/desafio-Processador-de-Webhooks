package br.com.fluxoalpha.desafio_webhook_processor.infrastructure.messaging.producer;

import br.com.fluxoalpha.desafio_webhook_processor.application.ports.LeadPublisher;
import br.com.fluxoalpha.desafio_webhook_processor.domain.model.Lead;
import br.com.fluxoalpha.desafio_webhook_processor.infrastructure.messaging.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class RabbitMQLeadPublisher implements LeadPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public RabbitMQLeadPublisher(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void enviarParaFila(Lead lead) {
        try {
            // 1. Transforma o objeto de Domínio em um texto JSON
            String jsonMessage = objectMapper.writeValueAsString(lead);

            // 2. Dispara para o RabbitMQ
            rabbitTemplate.convertAndSend(RabbitMQConfig.FILA_LEADS, jsonMessage);

            System.out.println("✅ [RABBITMQ REAL] - Mensagem enviada para a fila: " + RabbitMQConfig.FILA_LEADS);

        } catch (Exception e) {
            throw new RuntimeException("Falha ao enviar mensagem para a fila", e);
        }
    }
}
