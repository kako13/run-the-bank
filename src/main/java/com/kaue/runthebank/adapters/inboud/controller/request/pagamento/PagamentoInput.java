package com.kaue.runthebank.adapters.inboud.controller.request.pagamento;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@JsonTypeName("pagamento")
public class PagamentoInput {
    @NotNull
    private BigDecimal valor;
    @NotNull
    private Long contaRemetenteId;
    @NotNull
    private Long contaDestinatarioId;
}
