package com.hardy.leavetracking.infra.exception;


public class InvalidLeaveBalanceException extends  RuntimeException{
    public InvalidLeaveBalanceException(String message) {
        super(message);
    }
}
