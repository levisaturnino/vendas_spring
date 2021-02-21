package br.com.levisaturnino.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException() {
        super("Order not found.");
    }
}
