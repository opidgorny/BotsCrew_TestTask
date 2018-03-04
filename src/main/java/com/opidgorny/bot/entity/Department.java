package com.opidgorny.bot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Set;

@Entity(name = "departments")
@Setter
@Getter
public class Department {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "department_name")
    private String departmentName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "head_of_department_id")
    private Lector headOfDepartment;

    @ManyToMany(mappedBy = "departments", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Lector> lectors;

    public Department() {
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;

    }

    @Override
    public String toString() {
        String result = String.format("Department[id=%d, name='%s', "
                , id, departmentName);
        if(headOfDepartment != null) {
            result += String.format("head of department=%s %s, ", headOfDepartment.getFirstName(), headOfDepartment.getLastName());
            if(lectors != null) {
                result += "lectors: " + "\n";
                for(Lector lector : lectors) {

                    result += String.format("[first name='%s', last name='%s']%n", lector.getFirstName(), lector.getLastName());
                }
            }
        }
        result += "\n";
        return result;
    }


}
