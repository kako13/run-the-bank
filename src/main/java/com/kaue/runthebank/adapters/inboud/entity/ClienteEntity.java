package com.kaue.runthebank.adapters.inboud.entity;

import com.kaue.runthebank.application.core.domain.TipoDocumento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity(name = "cliente")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteEntity {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    String endereco;
    String senha;
    String documento;
    @Enumerated(EnumType.STRING)
    TipoDocumento tipoDocumento;
    @CreationTimestamp
    private OffsetDateTime dataCadastro;
}
