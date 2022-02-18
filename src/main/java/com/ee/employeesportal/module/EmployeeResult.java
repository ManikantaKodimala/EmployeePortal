package com.ee.employeesportal.module;


import com.ee.employeesportal.model.Employee;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class EmployeeResult {
    private List<Employee> data;
    private long totalElements;
    private long totalPages;
    private long pageSize;
    private long currentPage;
    private boolean hasNext;
    private boolean hasPrevious;

    public EmployeeResult(Page<Employee> employeePage){
        System.out.println("***"+employeePage.getContent());
        this.setData(employeePage.getContent());
        this.setTotalElements(employeePage.getTotalElements());
        this.setTotalPages(employeePage.getTotalPages());
        this.setCurrentPage(employeePage.getNumber());
        this.setPageSize(employeePage.getSize());
        this.setHasNext(employeePage.hasNext());
        this.setHasPrevious(employeePage.hasPrevious());
    }
}
