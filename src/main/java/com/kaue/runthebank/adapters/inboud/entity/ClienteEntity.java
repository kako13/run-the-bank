package com.kaue.runthebank.adapters.inboud.entity;

import com.kaue.runthebank.application.core.domain.TipoDocumento;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity(name = "cliente")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class ClienteEntity {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String endereco;
    private String senha;
    private String documento;
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;
    @CreationTimestamp
    private OffsetDateTime dataCadastro;
}
