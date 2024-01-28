package com.kaue.runthebank.adapters.inboud.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity(name = "estorno")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EstornoEntity {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_remetente_id")
    private ContaEntity contaRemetente;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_destinatario_id")
    private ContaEntity contaDestinatario;
    @CreationTimestamp
    private OffsetDateTime dataEstorno;
    private String codigoEstorno;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pagamento_id")
    private PagamentoEntity pagamento;

    @PrePersist
    private void gerarCodigo() {
        setCodigoEstorno(UUID.randomUUID().toString());
        pagamento.setEstornado(true);
    }
}
