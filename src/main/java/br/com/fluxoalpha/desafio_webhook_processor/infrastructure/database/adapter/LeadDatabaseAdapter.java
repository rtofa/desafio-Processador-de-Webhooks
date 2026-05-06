package br.com.fluxoalpha.desafio_webhook_processor.infrastructure.database.adapter;

import br.com.fluxoalpha.desafio_webhook_processor.application.ports.LeadRepository;
import br.com.fluxoalpha.desafio_webhook_processor.domain.model.Lead;
import br.com.fluxoalpha.desafio_webhook_processor.infrastructure.database.repository.LeadJpaRepository;
import br.com.fluxoalpha.desafio_webhook_processor.infrastructure.entity.LeadEntity;
import org.springframework.stereotype.Component;

@Component
public class LeadDatabaseAdapter implements LeadRepository {

    private final LeadJpaRepository jpaRepository;

    public LeadDatabaseAdapter(LeadJpaRepository jpaRepository){
        this.jpaRepository = jpaRepository;
    }

    public void salvar(Lead lead){
        // Converte o Domínio (Lead) isolado para a Entidade de Banco (LeadEntity)
        LeadEntity entity = new LeadEntity();

        entity.setName(lead.getName());
        entity.setPhone_number(lead.getPhoneNumber());
        entity.setEmail(lead.getEmail());
        entity.setCampaign(lead.getCampaign());
        entity.setStatus(lead.getStatus());

        // Salva no banco de dados usando o JPA
        jpaRepository.save(entity);

        System.out.println("💾 [POSTGRES] - Lead salvo com sucesso no banco de dados!");
    }
}
