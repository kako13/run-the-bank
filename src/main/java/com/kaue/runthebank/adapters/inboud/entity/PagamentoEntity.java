package com.kaue.runthebank.adapters.inboud.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity(name = "pagamento")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PagamentoEntity {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_remetente_id")
    private ContaEntity contaRemetente;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_destinataria_id")
    private ContaEntity contaDestinataria;
    @CreationTimestamp
    private OffsetDateTime dataPagamento;
    private String codigoPagamento;

    @PrePersist
    private void gerarCodigo() {
        setCodigoPagamento(UUID.randomUUID().toString());
    }
}
