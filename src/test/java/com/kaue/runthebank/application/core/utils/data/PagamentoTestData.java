package com.kaue.runthebank.application.core.utils.data;

import com.kaue.runthebank.application.core.domain.Pagamento;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class PagamentoTestData {

    public static Pagamento.PagamentoBuilder umPagamentoNaoEstornadoNovo() {

        return Pagamento.builder()
                .valor(BigDecimal.valueOf(100.50))
                .dataPagamento(OffsetDateTime.now())
                .codigoPagamento(UUID.randomUUID().toString())
                .estornado(Boolean.FALSE)
                .contaRemetente(ContaTestData.umaContaAtivaExistente().build())
                .contaDestinatario(ContaTestData.umaContaAtivaExistente().id(2L)
                        .build())
                ;
    }
}
