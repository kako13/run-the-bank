package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.core.domain.Movimento;
import com.kaue.runthebank.application.core.domain.Pagamento;
import com.kaue.runthebank.application.core.domain.TipoMovimento;
import com.kaue.runthebank.application.ports.in.movimento.MovimentoContaServicePort;
import com.kaue.runthebank.application.ports.out.MovimentoContaPort;

public class MovimentoContaContaService implements MovimentoContaServicePort {
    private final MovimentoContaPort movimentoContaPort;

    public MovimentoContaContaService(MovimentoContaPort movimentoContaPort) {
        this.movimentoContaPort = movimentoContaPort;
    }

    @Override
    public void gerarMovimentoDebito(Pagamento pagamento, Conta contaRemetente) {
        Movimento movimento = Movimento.builder()
                .valor(pagamento.getValor().negate())
                .tipoMovimento(TipoMovimento.DEBITO)
                .conta(contaRemetente)
                .pagamento(pagamento)
                .build();
        movimentoContaPort.registrarMovimento(movimento);
    }

    @Override
    public void gerarMovimentoCredito(Pagamento pagamento, Conta contaRemetente) {
        Movimento movimento = Movimento.builder()
                .valor(pagamento.getValor())
                .tipoMovimento(TipoMovimento.CREDITO)
                .conta(contaRemetente)
                .pagamento(pagamento)
                .build();
        movimentoContaPort.registrarMovimento(movimento);
    }
}
