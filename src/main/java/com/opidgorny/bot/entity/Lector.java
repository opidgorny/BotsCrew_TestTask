package com.opidgorny.bot.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "lectors")
@Setter
@Getter
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "salary")
    private int salary;

    @ManyToOne
    @JoinColumn(name = "lector_degree_id")
    private Degree degree;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "lectors_departments", joinColumns = @JoinColumn(name = "lector_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "department_id", referencedColumnName = "id"))
    private Set<Department> departments;

    @OneToOne(mappedBy = "headOfDepartment")
    private Department department;

    public Lector() {

    }

    public Lector(String firstName, String lastName, int salary, Degree degree) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.degree = degree;
    }

    @Override
    public String toString() {
        String result = String.format("Lector[id=%d, firstName='%s', " +
                "lastName='%s', salary=%d, degree='%s'",
                id, firstName, lastName, salary, degree.getDegreeName());
        if(departments != null) {
            for(Department department : departments) {
                result += String.format(
                        ", department[id=%d, name='%s']%n",
                        department.getId(), department.getDepartmentName());
            }
        }
        result += "\n";
        return result;
    }
}
