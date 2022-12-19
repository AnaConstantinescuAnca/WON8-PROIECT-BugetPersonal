package com.fasttrackit.BugetPersonal.controller;

import com.fasttrackit.BugetPersonal.service.BugetService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("buget")  //http://localhost:8080/buget
public class BugetController {
    private final BugetService bugetService;


}
