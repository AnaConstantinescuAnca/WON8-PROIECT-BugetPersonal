package com.fasttrackit.BugetPersonal.controller;

import com.fasttrackit.BugetPersonal.model.Cheltuiala;
import com.fasttrackit.BugetPersonal.model.Venit;
import com.fasttrackit.BugetPersonal.service.BugetService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("buget")  //http://localhost:8080/buget
public class BugetController {
    private final BugetService bugetService;

    @GetMapping
      public List<Venit> getAll(@RequestParam(required = false) Double valoare,
                                @RequestParam(required = false) Date dataVenit) {
        return bugetService.getAllVenituri().stream().toList();




//    System.out.println("Requested all countries");
//    return countryService.getCountriesFiltered(continent, minPopulation, maxPopulation).stream()
//            .map(country -> new CountryOverviewDTO(country.getId(), country.getName())).toList();
//){

    }

}
