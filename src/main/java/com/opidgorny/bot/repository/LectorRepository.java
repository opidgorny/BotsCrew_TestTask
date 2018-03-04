package com.opidgorny.bot.repository;

import com.opidgorny.bot.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {

    List<Lector> getLectorByFirstNameContaining(String firstName);
    List<Lector> getLectorByLastNameContaining(String lastName);

//    Lector getLectorByFirstName(String name);
}
