package br.com.fluxoalpha.desafio_webhook_processor.infrastructure.messaging.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FILA_LEADS = "fluxoalpha.leads.recebidos";

    @Bean
    public Queue filaLeads() {
        // Cria a fila e diz que ela é "durable" (não perde os dados se o RabbitMQ reiniciar)
        return new Queue(FILA_LEADS, true);
    }
}