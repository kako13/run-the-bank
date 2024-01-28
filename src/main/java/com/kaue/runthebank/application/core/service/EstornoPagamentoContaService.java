package com.kaue.runthebank.application.core.service;

import com.kaue.runthebank.application.core.domain.Conta;
import com.kaue.runthebank.application.core.domain.Estorno;
import com.kaue.runthebank.application.core.domain.Pagamento;
import com.kaue.runthebank.application.core.exception.ContaInativaException;
import com.kaue.runthebank.application.core.exception.NegocioException;
import com.kaue.runthebank.application.ports.in.estorno.EstornoPagamentoContaServicePort;
import com.kaue.runthebank.application.ports.out.estorno.ConsultaEstornoPagamentoPort;
import com.kaue.runthebank.application.ports.out.estorno.EstornoPagamentoPort;

import java.util.Optional;

public class EstornoPagamentoContaService implements EstornoPagamentoContaServicePort {
    private final EstornoPagamentoPort estornoPagamentoPort;
    private final ConsultaEstornoPagamentoPort consultaEstornoPagamentoPort;

    public EstornoPagamentoContaService(EstornoPagamentoPort estornoPagamentoPort,
                                        ConsultaEstornoPagamentoPort consultaEstornoPagamentoPort) {
        this.estornoPagamentoPort = estornoPagamentoPort;
        this.consultaEstornoPagamentoPort = consultaEstornoPagamentoPort;
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

        // Devolver o valor do REMETENTE e adicionar saldo ao DESTINATARIO
        contaDestinatario.debitar(pagamento.getValor());
        contaRemetente.creditar(pagamento.getValor());

        return estornoPagamentoPort.registrarEstorno(Estorno.builder()
                .valor(pagamento.getValor())
                .contaDestinatario(pagamento.getContaDestinatario())
                .contaRemetente(pagamento.getContaRemetente())
                .pagamento(pagamento)
                .build());
    }

    private void validaCondicaoParaEstorno(Pagamento pagamento) {
        Optional<Estorno> estorno = consultaEstornoPagamentoPort.buscar(pagamento.getId());
        if (estorno.isPresent())
            throw new NegocioException("Já foi realizado um estorno para este pagamento.");
    }
}
