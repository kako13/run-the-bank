package com.kaue.runthebank.adapters.inboud.controller.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {CPFouCNPJValidator.class})
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CPFouCNPJ {
    String message() default "Documento não corresponde a CPF ou CNPJ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String campoDocumento();
    String campoTipoDocumento();
}