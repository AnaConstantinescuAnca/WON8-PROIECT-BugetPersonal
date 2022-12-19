package com.fasttrackit.BugetPersonal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//@RequiredArgsConstructor
@Service


public class BugetService {
    private final BugetRepository bugetRepository;


    public BugetService(VenitReader venitReader,BugetRepository bugetRepository) {
        this.bugetRepository = bugetRepository;
        bugetRepository.saveAll(venitReader.getVenituri());
    }

}
