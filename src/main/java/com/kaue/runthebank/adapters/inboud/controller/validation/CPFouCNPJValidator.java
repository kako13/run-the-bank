package com.kaue.runthebank.adapters.inboud.controller.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;


public class CPFouCNPJValidator implements ConstraintValidator<CPFouCNPJ, String> {

    @Override
    public void initialize(CPFouCNPJ annotation) {
        // Neste caso não temos parametros nem dados do campo anotado
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // Valor inválido, a validação será tratada por outras anotações
        }
        // Verifica se o valor parece um CPF
        if (value.matches("\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}")) {
            return isValidCPF(value);
        }
        // Verifica se o valor parece um CNPJ
        if (value.matches("\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2}")) {
            return isValidCNPJ(value);
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