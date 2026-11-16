package com.hardy.leavetracking.leaverequest.domain.entity;

import com.hardy.leavetracking.employee.domain.entity.Employee;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Representa um pedido de férias.
 */
public class VacationLeaveRequest extends LeaveRequest {

    public VacationLeaveRequest(int id, Employee employee, LocalDate startDate, LocalDate endDate, String reason) {
        super(id, employee, startDate, endDate, reason);
    }

    @Override
    public int calculateLeaveDays() {
        return (int) ChronoUnit.DAYS.between(getStartDate(), getEndDate()) + 1;
    }
}