package com.fasttrackit.BugetPersonal.controller;

import com.fasttrackit.BugetPersonal.model.Cheltuiala;
import com.fasttrackit.BugetPersonal.model.Venit;
import com.fasttrackit.BugetPersonal.service.BugetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
@RestController
//@RequestMapping(value = {"venituri", "cheltuieli"})  //http://localhost:8080/venituri; //http://localhost:8080/cheltuieli
@RequestMapping(value = "buget")  //http://localhost:8080/buget;

 public class BugetController {
    private final BugetService bugetService;

    @GetMapping(value="/venituri")
      public List<Venit> getAllVenituri(@RequestParam(required = false) Double valoare,
                                @RequestParam(required = false) Date dataVenit) {
        return bugetService.getAllVenituri().stream().toList();

    }

    @GetMapping(value="/cheltuieli")
    public List<Cheltuiala> getAllCheltuieli(@RequestParam(required = false) Double valoare,
                                   @RequestParam(required = false) Date dataVenit) {
        return bugetService.getAllCheltuieli().stream().toList();

    }

    @GetMapping("/venituri/{id}") // GET http://host:port/buget/venituri/3
    public Venit getById(@PathVariable int id) {
        Venit venit = bugetService.getById(id);
        venit.getCheltuieli().stream().count();
        return venit;
    }

    @PostMapping(value = "/venituri") // POST http://host:port/buget/venituri
    public Venit add(@RequestBody Venit venit) {
        return bugetService.add(venit);
    }

    @PostMapping(value="/cheltuieli") // POST http://host:port/buget/cheltuieli
    public Cheltuiala add(@RequestBody Cheltuiala cheltuiala) {
        return bugetService.add(cheltuiala);
    }

}
