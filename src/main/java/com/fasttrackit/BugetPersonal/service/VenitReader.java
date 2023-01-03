package com.fasttrackit.BugetPersonal.service;

import com.fasttrackit.BugetPersonal.model.TipVenit;
import com.fasttrackit.BugetPersonal.model.Venit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VenitReader {
    @Value("${file.venituri}")
    private String fileVenituriPath;


    public List<Venit> getVenituri() {
        try {
            return Files.lines(Path.of(fileVenituriPath))
                    .map(this::lineToVenituri)
                    .collect(Collectors.toList());
        } catch (IOException e){
            throw new RuntimeException(e);}
    }

    private Venit lineToVenituri(String line)  {
        String[] splitLine = line.split("\\|");
        String dataParts = splitLine[1];
        Date data = null;

        try {
            data = new SimpleDateFormat("dd-MM-yyyy").parse(dataParts);
        }
        catch (ParseException exception){
            exception.printStackTrace();
        }

        return new Venit(Integer.parseInt(splitLine[0]), Double.parseDouble(splitLine[2]),
                 data,TipVenit.valueOf(splitLine[3]));

    }

}
