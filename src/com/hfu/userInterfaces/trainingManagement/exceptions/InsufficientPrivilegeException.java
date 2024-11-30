package com.hfu.userInterfaces.trainingManagement.exceptions;

public class InsufficientPrivilegeException extends RuntimeException{
    public InsufficientPrivilegeException(String message) {
        super(message);
    }
}
