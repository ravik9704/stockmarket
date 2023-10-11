package com.example.demo.exception;

public class InvalidSuperStockException extends Throwable{


    private SuperStackErrorCode superStackErrorCode;
    public InvalidSuperStockException(SuperStackErrorCode superStackErrorCode){
            super();
     this.superStackErrorCode = superStackErrorCode;

    }

    public SuperStackErrorCode getSuperStackErrorCode() {
        return superStackErrorCode;
    }

    public void setSuperStackErrorCode(SuperStackErrorCode superStackErrorCode) {
        this.superStackErrorCode = superStackErrorCode;
    }
}
