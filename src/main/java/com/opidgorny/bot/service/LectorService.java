package com.opidgorny.bot.service;

import com.opidgorny.bot.entity.Lector;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LectorService {

    String getLectorByFirstNameContainingOrLastNameContaining(String name);

//    String getFullLectorByFirstNameContainingOrLastNameContaining(String name);


}
