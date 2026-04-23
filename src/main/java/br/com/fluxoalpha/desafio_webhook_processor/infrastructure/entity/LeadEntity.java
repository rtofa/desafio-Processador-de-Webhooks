package br.com.fluxoalpha.desafio_webhook_processor.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "leads")
@Getter
@Setter
public class LeadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID leadId;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "campaign")
    private String campaign;

    @Column(name = "status")
    private String status;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

}
