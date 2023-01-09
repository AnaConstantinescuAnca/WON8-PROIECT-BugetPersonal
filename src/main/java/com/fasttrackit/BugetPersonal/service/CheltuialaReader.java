package com.fasttrackit.BugetPersonal.service;

import com.fasttrackit.BugetPersonal.model.Cheltuiala;
import com.fasttrackit.BugetPersonal.model.TipCheltuiala;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CheltuialaReader {

    @Value("${file.cheltuieli}")
    private String fileCheltuieliPath;

    public List<Cheltuiala> getCheltuieli() {
        try {
            return Files.lines(Path.of(fileCheltuieliPath))
                    .map(this::lineToCheltuieli)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Cheltuiala lineToCheltuieli(String line)  {
        String[] splitLine = line.split("\\|");
        String dataParts = splitLine[1];
        Date dataCheltuiala = null;

        try {
            dataCheltuiala = new SimpleDateFormat("dd-MM-yyyy").parse(dataParts);
        }
        catch (ParseException exception){
                exception.printStackTrace();
        }

        return new Cheltuiala(Integer.parseInt(splitLine[0]), Double.parseDouble(splitLine[2]),
                dataCheltuiala, TipCheltuiala.valueOf(splitLine[3]));

    }

}
