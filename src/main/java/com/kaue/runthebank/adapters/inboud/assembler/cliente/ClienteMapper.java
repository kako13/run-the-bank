package com.kaue.runthebank.adapters.inboud.assembler.cliente;

import com.kaue.runthebank.adapters.inboud.assembler.conta.ContaMapper;
import com.kaue.runthebank.adapters.inboud.controller.request.cliente.ClienteInput;
import com.kaue.runthebank.adapters.inboud.controller.request.cliente.ClienteModel;
import com.kaue.runthebank.adapters.inboud.entity.ClienteEntity;
import com.kaue.runthebank.application.core.domain.Cliente;
import com.kaue.runthebank.application.core.domain.TipoDocumento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = ContaMapper.class)
public interface ClienteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "tipoDocumento", target = "tipoDocumento", qualifiedByName = "toTipoDocumento")
    Cliente toDomainObject(ClienteInput clienteInput);

    @Named("toTipoDocumento")
    default TipoDocumento toTipoDocumento(String tipoDocumentoInput) {
        return TipoDocumento.valueOf(tipoDocumentoInput.toUpperCase());
    }

    ClienteModel toModel(Cliente cliente);

    ClienteEntity toEntity(Cliente cliente);
    @Mapping(target = "contas.cliente", ignore = true)
    Cliente toDomainObject(ClienteEntity clienteEntity);
}
