package com.ee.employeesportal.module;


import com.ee.employeesportal.dto.EmployeeDto;
import com.ee.employeesportal.model.Employee;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class EmployeeResult {
    private List<EmployeeDto> data;
    private long totalElements;
    private long totalPages;
    private long pageSize;
    private long currentPage;
    private boolean hasNext;
    private boolean hasPrevious;

    public EmployeeResult(Page<EmployeeDto> employeePage) {
        this.setData(employeePage.getContent());
        this.setTotalElements(employeePage.getTotalElements());
        this.setTotalPages(employeePage.getTotalPages());
        this.setCurrentPage(employeePage.getNumber()+1);
        this.setPageSize(employeePage.getSize());
        this.setHasNext(employeePage.hasNext());
        this.setHasPrevious(employeePage.hasPrevious());
    }
}
