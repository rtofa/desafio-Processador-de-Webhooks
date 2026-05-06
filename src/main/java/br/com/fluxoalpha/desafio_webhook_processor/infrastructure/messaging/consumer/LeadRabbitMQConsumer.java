package br.com.fluxoalpha.desafio_webhook_processor.infrastructure.messaging.consumer;

import br.com.fluxoalpha.desafio_webhook_processor.application.dto.LeadPayloadDto;
import br.com.fluxoalpha.desafio_webhook_processor.application.ports.LeadRepository;
import br.com.fluxoalpha.desafio_webhook_processor.domain.model.Lead;
import br.com.fluxoalpha.desafio_webhook_processor.infrastructure.messaging.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class LeadRabbitMQConsumer {

    private final LeadRepository leadRepository;
    private final ObjectMapper objectMapper; // transforma o texto JSON de volta em objeto Java

    public LeadRabbitMQConsumer(LeadRepository leadRepository, ObjectMapper objectMapper) {
        this.leadRepository = leadRepository;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = RabbitMQConfig.FILA_LEADS)
    public void processarLead(@Payload String mensagemJson) {

            System.out.println("\uD83D\uDCE5 [WORKER] - Nova mensagem recebida da fila!");

            // Em vez de converter para 'Lead.class', converti para o 'LeadPayloadDto.class'
            // O DTO é um record/classe simples que o Jackson consegue lidar perfeitamente
            LeadPayloadDto payload = objectMapper.readValue(mensagemJson, LeadPayloadDto.class);

            // Agora usamos o DTO para criar o objeto de Domínio usando o SEU construtor de regras de negócio
            Lead novoLead = new Lead(
                    payload.name(),
                    payload.phoneNumber(),
                    payload.email(),
                    payload.campaignOrigin()
            );

            leadRepository.salvar(novoLead);

            System.out.println("✅ [WORKER] - Lead processado e finalizado com sucesso!");
            System.out.println("---------------------------------------------------");

    }
}
