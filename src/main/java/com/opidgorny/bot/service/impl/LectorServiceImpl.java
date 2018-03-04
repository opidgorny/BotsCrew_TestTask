package com.opidgorny.bot.service.impl;

import com.opidgorny.bot.entity.Lector;
import com.opidgorny.bot.repository.LectorRepository;
import com.opidgorny.bot.service.LectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LectorServiceImpl implements LectorService {

    private static LectorRepository lectorRepository;

    @Autowired
    public void setLectorRepo(LectorRepository lectorRepository) {
        LectorServiceImpl.lectorRepository = lectorRepository;
    }

    @Override
    public String getLectorByFirstNameContainingOrLastNameContaining(String name) {

        List<Lector> lectors = lectorRepository.getLectorByFirstNameContaining(name);

        if(lectors.isEmpty()) {
            lectors = lectorRepository.getLectorByLastNameContaining(name);
        }
        String result = "Lectors: ";
        for(Lector lector : lectors) {
            result += String.format("[name=%s %s]%n", lector.getFirstName(), lector.getLastName());
        }
        return result;
    }

//    @Override
//    public String getFullLectorByFirstNameContainingOrLastNameContaining(String name) {
//
//        List<Lector> lectors = lectorRepository.getLectorByFirstNameContaining(name);
//
//        if(lectors.isEmpty()) {
//            lectors = lectorRepository.getLectorByLastNameContaining(name);
//        }
//        return String.valueOf(lectors);
//    }

}
