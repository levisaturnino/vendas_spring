package br.com.levisaturnino.exception;

public class PasswordInvalidException extends RuntimeException {

    public PasswordInvalidException() {
        super("Senha inválida!");
    }
}
