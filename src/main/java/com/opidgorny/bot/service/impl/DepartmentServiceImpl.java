package com.opidgorny.bot.service.impl;

import com.opidgorny.bot.entity.Department;
import com.opidgorny.bot.entity.Lector;
import com.opidgorny.bot.repository.DepartmentRepository;
import com.opidgorny.bot.repository.LectorRepository;
import com.opidgorny.bot.service.DepartmentService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private static DepartmentRepository departmentRepository;

    @Autowired
    public void setUserRepo(DepartmentRepository departmentRepository) {
        DepartmentServiceImpl.departmentRepository = departmentRepository;
    }

    @Override
    public String getDepartmentHead(String name) {
        Department department = departmentRepository.findDepartmentByDepartmentName(name);
        String lectorName = String.valueOf(department.getHeadOfDepartment().getFirstName() + " " + department.getHeadOfDepartment().getLastName());

        return lectorName;
    }

    public String getDepartmentEmployees(String name) {
        Department department = departmentRepository.findDepartmentByDepartmentName(name);
        Set<Lector> lectors = department.getLectors();
        System.out.println(lectors.size());
        Long professors = 0L;
        Long associateProfessors = 0L;
        Long assistants = 0L;

        for (Lector lector : lectors) {
            switch (lector.getDegree().getDegreeName()) {
                case "chancellor":
                    professors++;
                    break;
                case "professor":
                    professors++;
                    break;
                case "associate professor":
                    associateProfessors++;
                    break;
                case "assistant":
                    assistants++;
                    break;
                default:
                    break;
            }
        }
        return "assistants: " + assistants + "\n" +
                "associate professors: " + associateProfessors + "\n" +
                "professors: " + professors + "\n";
    }

    @Override
    public String getDepartmentNameByNameContaining(String name) {

        List<Department> departments = departmentRepository.findDepartmentByDepartmentNameContaining(name);
        String result = "Departments: ";

        for(Department department : departments) {
            result += String.format("[name='%s']%n", department.getDepartmentName());
        }

        return result;
    }

    @Override
    public Integer getDepartmentEmployeeCount(String name) {
        Department department = departmentRepository.findDepartmentByDepartmentName(name);
        return department.getLectors().size();
    }

    @Override
    public Integer getDepartmentAverageSalary(String name) {
        Department department = departmentRepository.findDepartmentByDepartmentName(name);
        Set<Lector> lectors = department.getLectors();
        Integer salary = 0;

        for(Lector lector : lectors) {
            salary += lector.getSalary();
        }
        Integer result = salary / lectors.size();
        return result;
    }

    //    @Override
//    public String getDepartmentName(Long id) {
//        Department department = departmentRepository.findDepartmentById(id);
//        String departmentName = department.getDepartmentName();
//        return departmentName;
//    }

    //    @Override
//    public Long getDepartmentId(String name) {
//        Department department = departmentRepository.findDepartmentByDepartmentName(name);
//        Long id = department.getId();
//        return id;
//    }

    //    @Override
//    public String getFullDepartmentNameByNameContaining(String name) {
//
//        List<Department> departments = departmentRepository.findDepartmentByDepartmentNameContaining(name);
//
//        return String.valueOf(departments);
//    }
}
