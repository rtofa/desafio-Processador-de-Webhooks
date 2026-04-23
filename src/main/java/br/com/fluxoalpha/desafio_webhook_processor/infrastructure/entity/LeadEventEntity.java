package br.com.fluxoalpha.desafio_webhook_processor.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "lead_events")
@Getter
@Setter
public class LeadEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id")
    private LeadEntity lead;

    @Column(name = "event_type", nullable = false)
    private String eventType; // qual o tipo de evento: "WEBHOOK_RECEIVED", "PROCESSED", "ERROR"

    @Column(name = "raw_payload", columnDefinition = "jsonb")
    private String rawPayload; // Armazena o JSON bruto para auditoria

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

}
