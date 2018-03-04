package com.opidgorny.bot.repository;

import com.opidgorny.bot.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findDepartmentByDepartmentName(String name);

    List<Department> findDepartmentByDepartmentNameContaining(String name);



}
