package com.hardy.leavetracking.infra.exception;

public class LeaveRequestNotFoundException extends RuntimeException {
    public LeaveRequestNotFoundException(String id) {
        super("Leave Request not found with ID: " + id);
    }
}
