package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.application.core.domain.*;
import com.kaue.runthebank.application.core.exception.ContaInativaException;
import com.kaue.runthebank.application.core.exception.NegocioException;
import com.kaue.runthebank.application.ports.in.estorno.EstornoPagamentoContaServicePort;
import com.kaue.runthebank.application.ports.in.movimento.MovimentoContaServicePort;
import com.kaue.runthebank.application.ports.out.estorno.EstornoPagamentoPort;

public class EstornoPagamentoContaService implements EstornoPagamentoContaServicePort {
    private final EstornoPagamentoPort estornoPagamentoPort;
    private final MovimentoContaServicePort movimentoContaServicePort;

    public EstornoPagamentoContaService(EstornoPagamentoPort estornoPagamentoPort,
                                        MovimentoContaServicePort movimentoContaServicePort) {
        this.estornoPagamentoPort = estornoPagamentoPort;
        this.movimentoContaServicePort = movimentoContaServicePort;
    }

    private static void validarStatusContas(Conta contaRemetente, Conta contaDestinatario) {
        if (contaRemetente.estaInativo())
            throw new ContaInativaException(contaRemetente.getId());
        if (contaDestinatario.estaInativo())
            throw new ContaInativaException(contaDestinatario.getId());
    }

    @Override
    public Estorno estornarPagamento(Pagamento pagamento) {
        validaCondicaoParaEstorno(pagamento);

        Conta contaRemetente = pagamento.getContaRemetente();
        Conta contaDestinatario = pagamento.getContaDestinatario();

        validarStatusContas(contaRemetente, contaDestinatario);

        contaDestinatario.debitar(pagamento.getValor());
        contaRemetente.creditar(pagamento.getValor());

        Estorno estornoRegistrado = estornoPagamentoPort.registrarEstorno(Estorno.builder()
                .valor(pagamento.getValor())
                .contaDestinatario(pagamento.getContaDestinatario())
                .contaRemetente(pagamento.getContaRemetente())
                .pagamento(pagamento)
                .build());

        movimentoContaServicePort.gerarMovimentoDebito(estornoRegistrado.getPagamento(), contaRemetente);
        movimentoContaServicePort.gerarMovimentoCredito(estornoRegistrado.getPagamento(), contaDestinatario);

        return estornoRegistrado;
    }

    private void validaCondicaoParaEstorno(Pagamento pagamento) {
        if (Boolean.TRUE.equals(pagamento.foiEstornado()))
            throw new NegocioException("Já foi realizado um estorno para este pagamento.");
    }
}
