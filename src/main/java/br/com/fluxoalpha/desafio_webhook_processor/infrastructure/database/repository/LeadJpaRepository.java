package br.com.fluxoalpha.desafio_webhook_processor.infrastructure.database.repository;

import br.com.fluxoalpha.desafio_webhook_processor.infrastructure.entity.LeadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LeadJpaRepository extends JpaRepository<LeadEntity, UUID> {

}
