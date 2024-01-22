package com.kaue.runthebank.application.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
@AllArgsConstructor
public class ValidacaoException extends RuntimeException {
    private final BindingResult bindingResult;
}