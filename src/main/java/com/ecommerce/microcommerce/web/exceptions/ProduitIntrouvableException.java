package com.ecommerce.microcommerce.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such order")
public class ProduitIntrouvableException extends RuntimeException {
    public ProduitIntrouvableException(String s) {
        super(s);
    }
}
