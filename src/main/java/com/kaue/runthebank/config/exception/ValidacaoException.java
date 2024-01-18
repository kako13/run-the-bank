package com.kaue.runthebank.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
@AllArgsConstructor
public class ValidacaoException extends RuntimeException {
    private final BindingResult bindingResult;
}