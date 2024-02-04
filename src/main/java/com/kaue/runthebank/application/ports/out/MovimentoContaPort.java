package com.kaue.runthebank.application.ports.out;

import com.kaue.runthebank.application.core.domain.Movimento;

public interface MovimentoContaPort {
    Movimento registrarMovimento(Movimento movimento);
}
