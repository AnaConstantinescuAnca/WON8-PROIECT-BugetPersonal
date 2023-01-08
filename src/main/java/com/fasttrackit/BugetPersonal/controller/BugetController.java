package com.fasttrackit.BugetPersonal.controller;

import com.fasttrackit.BugetPersonal.model.Cheltuiala;
import com.fasttrackit.BugetPersonal.model.Venit;
import com.fasttrackit.BugetPersonal.service.BugetService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "buget")  //http://localhost:8080/buget;

public class BugetController {
    private final BugetService bugetService;

    @GetMapping(value = "/venituri")
    public List<Venit> getAllVenituri(@RequestParam(required = false) Double valoare,
                                      @RequestParam(required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date data) {
        return bugetService.getVenituriFiltered(valoare, data).stream().toList();
        //return bugetService.getAllVenituri().stream().toList();

    }

    @GetMapping(value = "/cheltuieli")
    public List<Cheltuiala> getAllCheltuieli(@RequestParam(required = false) Double valoare,
                                             @RequestParam(required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date data) {
        return bugetService.getCheltuieliFiltered(valoare, data).stream().toList();

    }

    @GetMapping("/venituri/{id}") // GET http://host:port/buget/venituri/3
    public Venit getById(@PathVariable Integer id) {
        Venit venit = bugetService.getByIdVenit(id);
        venit.getCheltuieli().stream().count();
        return venit;
    }

    @GetMapping("/cheltuieli/{id}") // GET http://host:port/buget/cheltuieli/3
    public Cheltuiala getByIdCheltuiala(@PathVariable Integer id) {
        Cheltuiala cheltuiala = bugetService.getByIdCheltuiala(id);
        cheltuiala.getId();
        return cheltuiala;
    }

    @DeleteMapping("/venituri/{id}") // DELETE http://host:port/buget/venituri/3
    public Venit deleteVenitById(@PathVariable int id) {
        return bugetService.deleteVenitById(id);

    }

    @DeleteMapping("/cheltuieli/{id}") // DELETE http://host:port/buget/cheltuieli/3
    public Cheltuiala deleteCheltuialaById(@PathVariable int id) {
        return bugetService.deleteCheltuialaById(id);

    }

    @PostMapping(value = "/venituri") // POST http://host:port/buget/venituri
    public Venit add(@RequestBody Venit venit) {
        return bugetService.add(venit);
    }

    @PostMapping(value = "/cheltuieli") // POST http://host:port/buget/cheltuieli
    public Cheltuiala add(@RequestBody Cheltuiala cheltuiala) {
        return bugetService.add(cheltuiala);
    }
    //insert into cheltuiala (id,  data, tip, valoare, venit_id) values(104, '2023-01-01 00:00:00', 1, 101, 4)


    @PostMapping("/venituri/{id}/cheltuieli")
    Venit addCheltuialaToVenit(@PathVariable int id, @RequestBody Cheltuiala cheltuiala) {
        return bugetService.addCheltuialaToVenit(id, cheltuiala);
    }

    @PutMapping("/venituri/{id}")
    Venit updateVenit(@PathVariable int id, @RequestBody Venit venit){
        return bugetService.updateVenit(id, venit);
    }
    @PutMapping("/cheltuieli/{id}")
    Cheltuiala updateCheltuiala(@PathVariable int id, @RequestBody Cheltuiala cheltuiala){
        return bugetService.updateCheltuiala(id, cheltuiala);
    }


}
