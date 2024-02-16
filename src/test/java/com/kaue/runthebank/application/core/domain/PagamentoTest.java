package com.kaue.runthebank.application.core.domain;

import com.kaue.runthebank.application.core.utils.data.PagamentoTestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PagamentoTest {

    @Test
    void foiEstornado() {
        Pagamento pagamento = PagamentoTestData.umPagamentoNaoEstornadoNovo().build();
        Assertions.assertThat(pagamento.foiEstornado()).isFalse();
    }
}