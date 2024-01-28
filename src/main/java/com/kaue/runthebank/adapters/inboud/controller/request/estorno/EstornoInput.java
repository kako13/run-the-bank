package com.kaue.runthebank.adapters.inboud.controller.request.estorno;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonTypeName("estorno")
public class EstornoInput {
    @NotNull
    private String codigoPagamento;
}
