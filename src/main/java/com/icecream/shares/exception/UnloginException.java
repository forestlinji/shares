package com.icecream.shares.exception;


public class UnloginException extends Exception{
    public UnloginException(String message) {
        super(message);
    }

    public UnloginException() {
        super();
    }
}
