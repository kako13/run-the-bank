package com.kaue.runthebank.adapters.inboud.entity;

import com.kaue.runthebank.application.core.domain.TipoMovimento;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "movimento")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MovimentoEntity {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoMovimento tipoMovimento;
    private BigDecimal valor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id")
    private ContaEntity conta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pagamento_id")
    private PagamentoEntity pagamento;
}