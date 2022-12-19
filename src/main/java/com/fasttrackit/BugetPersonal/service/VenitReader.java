package com.fasttrackit.BugetPersonal.service;

import com.fasttrackit.BugetPersonal.model.TipVenit;
import com.fasttrackit.BugetPersonal.model.Venit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VenitReader {
    @Value("{file.venituri}")
    private String fileVenituriPath;

    @Value("{file.cheltuieli")
    private String fileCheltuieliPath;

    public List<Venit> getVenituri() {
        try {
            return Files.lines(Path.of(fileVenituriPath))
                    .map(this::lineToVenituri)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Venit lineToVenituri(String line)  {
        String[] venitParts = line.split("\\|");
        String[] splitLine = line.split("\\|");
        String dataParts = splitLine[1];
        Date date1 = null;

        try {
            date1 = new SimpleDateFormat("dd-MM-yyyy").parse(dataParts);
        }
        catch (ParseException exception){

        }

        return new Venit(Long.parseLong(venitParts[0]), Double.parseDouble(venitParts[2]),
                date1, TipVenit.valueOf(venitParts[3]));

    }

}
