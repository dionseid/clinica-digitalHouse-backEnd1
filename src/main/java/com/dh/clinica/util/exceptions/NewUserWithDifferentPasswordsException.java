package com.dh.clinica.util.exceptions;

public class NewUserWithDifferentPasswordsException extends RuntimeException {
    private static final Long serialVersionUid = -7978601526802035152L;

    public NewUserWithDifferentPasswordsException() {
        super("Las contraseñas no coinciden");
    }
}
