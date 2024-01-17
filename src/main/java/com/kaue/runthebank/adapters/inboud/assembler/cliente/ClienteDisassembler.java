package com.kaue.runthebank.adapters.inboud.assembler.cliente;

import com.kaue.runthebank.adapters.inboud.controller.request.cliente.ClienteInput;
import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import com.kaue.runthebank.application.core.domain.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cliente toDomainObject(ClienteInput clienteInput) {
        formatDocument(clienteInput);
        return modelMapper.map(clienteInput, Cliente.class);
    }

    public Cliente toDomainObject(ClienteEntity clienteEntity) {
        return modelMapper.map(clienteEntity, Cliente.class);
    }

    private void formatDocument(ClienteInput clienteInput) {
        if (clienteInput != null && clienteInput.getDocumento() != null) {
            clienteInput.setTipoDocumento(clienteInput.getTipoDocumento().toUpperCase());
            String document = clienteInput.getDocumento();
            String documentoSemCaracteres = document.replaceAll("[^\\d]", "");
            // Aplica a formatação de CPF ou CNPJ com base no tamanho do documento
            if (documentoSemCaracteres.length() == 11) {
                clienteInput.setDocumento(formatCPF(documentoSemCaracteres));
            } else if (documentoSemCaracteres.length() == 14) {
                clienteInput.setDocumento(formatCNPJ(documentoSemCaracteres));
            }
        }
    }

    private String formatCPF(String cpf) {
        // Formatação de CPF: 123.456.789-09
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    private String formatCNPJ(String cnpj) {
        // Formatação de CNPJ: 12.345.678/0001-90
        return cnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }
}
