package com.opidgorny.bot.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Set;

@Entity(name = "degrees")
@Setter
@Getter
public class Degree {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "degree_name")
    private String degreeName;

    @OneToMany(mappedBy = "degree", cascade = CascadeType.ALL)
    private Set<Lector> lectors;

    public Degree() {
    }

    public Degree(String degreeName) {
        this.degreeName = degreeName;
    }

    @Override
    public String toString() {
        return "Degree{" +
                "id=" + id +
                ", degreeName='" + degreeName + '\'' +
                '}';
    }
}
