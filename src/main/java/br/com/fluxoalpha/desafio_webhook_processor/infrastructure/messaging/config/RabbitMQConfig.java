package br.com.fluxoalpha.desafio_webhook_processor.infrastructure.messaging.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FILA_LEADS = "fluxoalpha.leads.recebidos";
    public static final String FILA_LEADS_DLQ = FILA_LEADS + ".dlq";
    public static final String EXCHANGE_DLX = "fluxoalpha.leads.dlx";

    // Cria a Exchange (A ponte que redireciona o erro)
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(EXCHANGE_DLX);
    }

    // Cria a DLQ (A fila hospital/cemitério)
    @Bean
    public Queue filaLeadsDlq() {
        return QueueBuilder.durable(FILA_LEADS_DLQ).build();
    }

    // Liga a DLQ na Exchange
    @Bean
    public Binding dlqBinding() {
        return BindingBuilder.bind(filaLeadsDlq()).to(dlxExchange()).with(FILA_LEADS_DLQ);
    }

    // Cria a Fila Principal avisando que, se der erro, o lixo vai para a DLX
    @Bean
    public Queue filaLeads() {
        return QueueBuilder.durable(FILA_LEADS)
                .withArgument("x-dead-letter-exchange", EXCHANGE_DLX)
                .withArgument("x-dead-letter-routing-key", FILA_LEADS_DLQ)
                .build();
    }
}