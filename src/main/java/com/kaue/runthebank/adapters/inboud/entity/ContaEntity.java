package com.kaue.runthebank.adapters.inboud.entity;

import com.kaue.runthebank.application.core.domain.StatusConta;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Entity(name = "conta")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ContaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private Long id;
    @EqualsAndHashCode.Include
    private String agencia;
    private BigDecimal saldo;
    @Enumerated(EnumType.STRING)
    private StatusConta status;
    @CreationTimestamp
    private OffsetDateTime dataCadastro;

    @JoinColumn(name = "cliente_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "contaRemetente")
    private Set<PagamentoEntity> pagamentosEnviadas;

    @OneToMany(mappedBy = "contaDestinatario")
    private Set<PagamentoEntity> pagamentosRecebidas;

    @OneToMany(mappedBy = "contaRemetente")
    private Set<EstornoEntity> estornos;

    @OneToMany(mappedBy = "conta")
    private Set<MovimentoEntity> movimentos;
}