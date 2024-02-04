package com.kaue.runthebank.adapters.inboud.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;
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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_remetente_id")
    private ContaEntity contaRemetente;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_destinatario_id")
    private ContaEntity contaDestinatario;
    @UpdateTimestamp
    private OffsetDateTime dataEstorno;
    @CreationTimestamp
    private OffsetDateTime dataPagamento;
    private Boolean estornado = Boolean.FALSE;
    private String codigoPagamento;

    @OneToMany(mappedBy = "pagamento")
    private Set<MovimentoEntity> movimentos;

    @PrePersist
    private void gerarCodigo() {
        setCodigoPagamento(UUID.randomUUID().toString());
    }
}
