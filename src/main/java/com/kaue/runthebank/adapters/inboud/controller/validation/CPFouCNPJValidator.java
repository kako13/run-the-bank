package com.kaue.runthebank.adapters.inboud.controller.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;


public class CPFouCNPJValidator implements ConstraintValidator<CPFouCNPJ, Object> {
    private String campoTipoDocumento;
    private String campoDocumento;

    @Override
    public void initialize(CPFouCNPJ annotation) {
        campoDocumento = annotation.campoDocumento();
        campoTipoDocumento = annotation.campoTipoDocumento();
        // Neste caso não temos parametros nem dados do campo anotado
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null)
            return true; // Valor inválido, a validação será tratada por outras anotações

        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        String documento = (String) beanWrapper.getPropertyValue(campoDocumento);
        String tipoDocumento = (String) beanWrapper.getPropertyValue(campoTipoDocumento);


        if (documento != null && tipoDocumento != null) {
            // Verifica se o valor parece um CPF
            if (documento.matches("\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}")) {
                return tipoDocumento.equalsIgnoreCase("CPF") && isValidCPF(documento);
            }
            // Verifica se o valor parece um CNPJ
            if (documento.matches("\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2}")) {
                return tipoDocumento.equalsIgnoreCase("CNPJ") && isValidCNPJ(documento);
            }
        }
        return false; // Valor inválido, não parece ser CPF nem CNPJ
    }

    private boolean isValidCPF(String value) {
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.initialize(null);
            return cpfValidator.isValid(value.replaceAll("[^\\d]", ""), null);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isValidCNPJ(String value) {
        try {
            CNPJValidator cnpjValidator = new CNPJValidator();
            cnpjValidator.initialize(null);
            return cnpjValidator.isValid(value.replaceAll("[^\\d]", ""), null);
        } catch (Exception e) {
            return false;
        }
    }
}