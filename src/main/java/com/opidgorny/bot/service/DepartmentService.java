package com.opidgorny.bot.service;

import com.opidgorny.bot.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    String getDepartmentHead(String name);
    Integer getDepartmentEmployeeCount(String name);
    Integer getDepartmentAverageSalary(String name);
    String getDepartmentNameByNameContaining(String name);

//    String getDepartmentName(Long id);
//    Long getDepartmentId(String name);
//    String getFullDepartmentNameByNameContaining(String name);
}
